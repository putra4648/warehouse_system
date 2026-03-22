<template>
  <UPage>
    <UPageHeader title="Inbound">
      <template #description>
        Received Product
      </template>
    </UPageHeader>

    <UPageBody>


      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        <UCard v-for="(stat, index) in stats" :key="index">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-500 dark:text-gray-400">
                {{ stat.label }}
              </p>
              <h3 class="text-2xl font-bold text-gray-900 dark:text-white mt-1">
                {{ stat.value }}
              </h3>
            </div>
            <UIcon :name="stat.icon"
              class="w-8 h-8 text-primary-500 bg-primary-100 dark:bg-primary-900 rounded-full p-1.5" />
          </div>
          <p :class="['text-xs mt-2 flex items-center', stat.trendPositive ? 'text-green-500' : 'text-gray-500']">
            <UIcon :name="stat.trendPositive ? 'i-heroicons-arrow-trending-up' : 'i-heroicons-minus'"
              class="w-4 h-4 mr-1" />
            {{ stat.trend }}
          </p>
        </UCard>
      </div>

      <div class="flex items-center justify-between">
        <h1 class="text-2xl font-bold text-gray-900 dark:text-white" />
        <UButton icon="i-heroicons-plus" color="primary" label="Create Inbound" @click="isOpen = true" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter inbound..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="purchaseOrders" :loading="status === 'pending'">
          <template #status-cell="{ row }">
            <OrderStatusBadge :status="row.original.status" />
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="items(row.original)">
              <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :items-per-page="size" :total="total" @update:page="(p) => page = p" />
        </div>
      </UCard>

      <!-- Create Modal -->
      <UModal fullscreen v-model:open="isOpen" title="Create Inbound" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4" @submit="savePurchaseOrder">
            <UFormField label="PO Number" name="poNumber">
              <UInput v-model="state.po_number" class="w-full" placeholder="Enter PO number" />
            </UFormField>
            <UFormField label="Supplier" name="supplierId">
              <USelectMenu v-model.number="supplierSearch" class="w-full" value-key="id" label-key="name" clear
                :items="supplierResponse?.data" @update:open="executeSupplier()" />
            </UFormField>
            <UFormField label="Date" name="orderDate">
              <UInput v-model="state.order_date" type="date" class="w-full" />
            </UFormField>
            <UFormField label="Status" name="status">
              <USelect v-model="state.status" class="w-full"
                :items="[OrderStatus.PENDING, OrderStatus.COMPLETED, OrderStatus.CANCELLED]" />
            </UFormField>
            <UFormField label="Items" name="purchaseOrderLines">
              <UButton label="Add Item" icon="i-heroicons-plus" color="primary" @click="addItem" />
              <UTable :data="state.purchase_order_lines" :columns="productColumns">
                <template #product-cell="{ row }">
                  <USelectMenu v-model="row.original.product.id" class="w-full" value-key="id" label-key="name" clear
                    :items="productResponse?.data" @update:open="executeProduct()" />
                </template>
                <template #quantity-cell="{ row }">
                  <UInput v-model="row.original.quantity" type="number" class="w-full" />
                </template>
                <template #price-cell="{ row }">
                  <UInput v-model="row.original.price" type="number" class="w-full" />
                </template>
              </UTable>
            </UFormField>
            <UButton type="submit" block color="primary">Create Inbound PO</UButton>
          </UForm>
        </template>
      </UModal>

      <!-- Detail Modal -->
      <UModal fullscreen ref="detailModal" v-model:open="isShowDetail">
        <template #title>
          {{ detailModal.title }}
        </template>

        <template #body>
          <UCard>
            <template #header>
              <div class="flex items-center justify-between">
                <h2 class="text-lg font-medium">Purchase Order Details</h2>
                <OrderStatusBadge :status="detailModal.data!.status" />
              </div>
            </template>

            <div class="space-y-4">
              <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <p class="text-sm font-medium text-gray-500 dark:text-gray-400">PO Number</p>
                  <p class="text-base font-semibold text-gray-900 dark:text-white">{{ detailModal.data?.po_number }}</p>
                </div>
                <div>
                  <p class="text-sm font-medium text-gray-500 dark:text-gray-400">Order Date</p>
                  <p class="text-base font-semibold text-gray-900 dark:text-white">{{ detailModal.data?.order_date }}
                  </p>
                </div>
              </div>

              <div class="border-t border-gray-200 dark:border-gray-700 pt-4">
                <h3 class="text-lg font-medium mb-4">Products</h3>
                <UTable :data="detailModal.data?.purchase_order_lines || []" :columns="productColumns">
                  <template #product-cell="{ row }">
                    {{ row.original.product?.name }}
                  </template>
                </UTable>
              </div>
            </div>
          </UCard>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { PurchaseOrder, PurchaseOrderLine } from '~~/types/inbound'
import type { PaginationResponse, ResponseData } from '~~/server/utils/response'
import type { TableColumn } from '@nuxt/ui'
import type { Supplier } from '~~/types/supplier';
import type { Product } from '~~/types/product';
import { OrderStatus } from '~~/types/enums/order_enum';

const isOpen = ref(false);
const isShowDetail = ref(false);
const q = ref("");
const supplierSearch = ref();
const productSearch = ref();
const page = ref(1);
const size = ref(10);
const detailModal = ref({
  title: "",
  data: null as PurchaseOrder | null
});

const { data, status, refresh } = await useFetch<PaginationResponse<PurchaseOrder>>("/api/inbound/po", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
});

const { data: supplierResponse, execute: executeSupplier } = await useLazyFetch<PaginationResponse<Supplier>>("/api/suppliers", {
  key: "suppliers",
  immediate: false,
  query: {
    page: 0,
    size: 10,
    search: supplierSearch.value ?? ''
  }
});

const { data: productResponse, execute: executeProduct } = await useLazyFetch<PaginationResponse<Product>>("/api/products", {
  key: "products",
  immediate: false,
  query: {
    page: 0,
    size: 10,
    search: productSearch.value ?? ''
  }
});

const purchaseOrders = computed(() => data.value?.data ?? []);
const total = computed(() => data.value?.meta.total || 0);

const productColumns: TableColumn<PurchaseOrderLine>[] = [
  { header: "No.", cell: ({ row }) => row.index + 1 },
  { accessorKey: "product", header: "Product Name" },
  { accessorKey: "quantity", header: "Quantity" },
  { accessorKey: "price", header: "Price" },
  { header: "Total", cell: ({ row }) => row.original.quantity * row.original.price },
];
const stats = computed(() => [
  {
    label: 'Total PO',
    value: total.value.toString(),
    icon: 'i-heroicons-cube',
    trend: 'Purchase orders',
    trendPositive: true,
  },
  {
    label: 'Pending',
    value: purchaseOrders.value.filter(p => p.status === OrderStatus.PENDING).length.toString(),
    icon: 'i-heroicons-clock',
    trend: 'Requires receiving',
    trendPositive: false,
  },
]);

const columns: TableColumn<PurchaseOrder>[] = [
  { header: "No.", cell: ({ row }) => row.index + 1 },
  { accessorKey: "po_number", header: "PO Number" },
  { accessorKey: "order_date", header: "Order Date" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  id: null as number | null,
  po_number: "",
  order_date: new Date().toISOString().split('T')[0],
  status: OrderStatus.PENDING,
  supplier_id: null as number | null,
  purchase_order_lines: [] as PurchaseOrderLine[]
});

function addItem() {
  state.purchase_order_lines.push({
    id: null,
    product: {
      id: null,
      name: "",
      description: "",
      min_stock: 0,
      max_stock: 0,
      quantity: 0,
      is_active: true,
    },
    quantity: 0,
    price: 0,
    purchase_order_id: null
  });
}


async function handleShowDetail(row: PurchaseOrder) {
  const detail = await $fetch<ResponseData<PurchaseOrder>>(`/api/inbound/po/${row.id}`);
  detailModal.value.data = detail.data;
  detailModal.value.title = `Detail PO ${row.po_number}`;
  isShowDetail.value = true;
}

async function savePurchaseOrder() {
  try {
    const method = state.id ? 'PUT' : 'POST';
    const body = {
      ...state,
      supplier_id: supplierSearch.value
    };

    await $fetch("/api/inbound/po", {
      method,
      body
    });

    isOpen.value = false;
    resetForm();
    refresh();
  } catch (error) {
    console.error("Failed to save Purchase Order", error);
  }
}

function resetForm() {
  state.id = null;
  state.po_number = "";
  state.order_date = new Date().toISOString().split('T')[0];
  state.status = OrderStatus.PENDING;
  state.supplier_id = null;
  state.purchase_order_lines = [];
}

const items = (row: PurchaseOrder) => [
  [
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      onSelect: () => handleShowDetail(row),
    },
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      onSelect: () => Object.assign(state, row),
    },
  ],
];

</script>
