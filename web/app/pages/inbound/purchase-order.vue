<template>
  <UPage>
    <UPageHeader title="Purchase Order" description="Manage Purchase Order" />
    <UPageBody>
      <div class="flex items-center justify-between">
        <UButton icon="i-heroicons-plus" color="primary" label="Create PO" @click="handleCreate" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q.po_number" placeholder="Filter PO..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="purchaseOrders" :loading="status === 'pending'">
          <template #status-cell="{ row }">
            <StatusBadge :status="row.original.status!" />
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="items(row.original)">
              <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :total="total" @update:page="(p) => page = p" />
        </div>
      </UCard>


      <!-- Create Modal -->
      <UModal fullscreen v-model:open="isOpen" :title="state.id ? 'Edit PO' : 'Create PO'" scrollable>
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
            <UFormField label="Items" name="purchaseOrderLines">
              <UButton label="Add Item" icon="i-heroicons-plus" color="primary" @click="addItem" />
              <UTable :data="state.purchase_order_lines" :columns="productColumns">
                <template #product-cell="{ row }">
                  <USelectMenu v-model="row.original.product.id" class="w-full" value-key="id" label-key="name" clear
                    :items="productResponse?.data" @update:open="executeProduct()" />
                </template>
                <template #quantity-cell="{ row }">
                  <UInput v-model="row.original.quantity" type="number" class="w-full" min="1" />
                </template>
                <template #price-cell="{ row }">
                  <UInput v-model="row.original.price" type="number" class="w-full" />
                </template>
                <template #actions-cell="{ row }">
                  <UButton color="error" variant="ghost" icon="i-heroicons-trash" @click="deleteItem(row.original)" />
                </template>
              </UTable>
            </UFormField>
            <UButton type="submit" block color="primary">{{ state.id ? 'Update PO' : 'Create PO' }}</UButton>
          </UForm>
        </template>
      </UModal>

      <!-- Detail Modal -->
      <UModal fullscreen ref="detailModal" v-model:open="isShowDetail">
        <template #title>
          {{ detailModal.title }}
        </template>

        <template #description>
          Detail Purchase Order
        </template>

        <template #body>
          <UCard v-if="detailModal.data">
            <template #header>
              <div class="flex items-center justify-between">
                <h2 class="text-lg font-medium">Purchase Order Details</h2>
                <StatusBadge :status="detailModal.data.status!" />
              </div>
            </template>

            <div class="space-y-4">
              <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <p class="text-sm font-medium text-gray-500 dark:text-gray-400">PO Number</p>
                  <p class="text-base font-semibold text-gray-900 dark:text-white">{{ detailModal.data.po_number }}</p>
                </div>
                <div>
                  <p class="text-sm font-medium text-gray-500 dark:text-gray-400">Order Date</p>
                  <p class="text-base font-semibold text-gray-900 dark:text-white">{{ detailModal.data.order_date }}
                  </p>
                </div>
              </div>

              <div class="border-t border-gray-200 dark:border-gray-700 pt-4">
                <h3 class="text-lg font-medium mb-4">Products</h3>
                <UTable :data="detailModal.data.purchase_order_lines || []" :columns="productColumns">
                  <template #product-cell="{ row }">
                    {{ row.original.product?.name }}
                  </template>
                </UTable>
                <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
                  <UPagination v-model="detailPage" :total="detailTotal" @update:page="(p) => detailPage = p" />
                </div>
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
import type { DropdownMenuItem, TableColumn } from '@nuxt/ui'
import type { Supplier } from '~~/types/supplier';
import type { Product } from '~~/types/product';
import { OrderStatus } from '~~/types/enums/order_enum';


const isOpen = ref(false);
const isShowDetail = ref(false);
const q = ref<PurchaseOrder>({
  id: null,
  po_number: "",
  order_date: "",
});
const supplierSearch = ref();
const productSearch = ref();
const page = ref(1);
const detailPage = ref(1);
const detailModal = ref({
  title: "",
  data: null as PurchaseOrder | null
});
const detailTotal = computed(() => detailModal.value.data?.meta?.total || 0);


const { data, status, refresh } = await useFetch<PaginationResponse<PurchaseOrder>>("/api/inbound/po", {
  query: computed(() => ({
    page: page.value,
    size: 10,
    po_number: q.value.po_number,
    order_date: q.value.order_date,
    id: q.value.id
  })),
  watch: [page]
});

const { data: supplierResponse, execute: executeSupplier } = useLazyFetch<PaginationResponse<Supplier>>("/api/suppliers", {
  key: "suppliers",
  immediate: false,
  query: {
    page: 0,
    size: 10,
    search: computed(() => supplierSearch.value ?? '')
  }
});

const { data: productResponse, execute: executeProduct } = useLazyFetch<PaginationResponse<Product>>("/api/products", {
  key: "products",
  immediate: false,
  query: {
    page: 0,
    size: 10,
    search: computed(() => productSearch.value ?? '')
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
  { accessorKey: "actions", header: "" },
];

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
  order_date: "",
  status: OrderStatus.PENDING,
  supplier_id: null as number | null,
  purchase_order_lines: [] as PurchaseOrderLine[]
});

onMounted(() => {
  state.order_date = new Date().toISOString().split('T')[0] ?? "";
});

function handleCreate() {
  resetForm();
  isOpen.value = true;
}

function addItem() {
  state.purchase_order_lines.push({
    id: null,
    product: {
      id: null,
      name: "",
    },
    quantity: 1,
    price: 0,
    purchase_order_id: null
  });
}

async function cancelPurchaseOrder(id: number | null) {
  if (id === null) return;
  try {
    await $fetch(`/api/inbound/po`, {
      method: "PATCH",
      body: {
        id,
        status: OrderStatus.CANCELLED
      }
    });
    await refresh();
  } catch (error) {
    console.error("Failed to cancel Purchase Order", error);
  }
}

function deleteItem(item: PurchaseOrderLine) {
  state.purchase_order_lines = state.purchase_order_lines.filter(i => i !== item);
}

async function handleShowDetail(row: PurchaseOrder) {
  const detail = await $fetch<ResponseData<PurchaseOrder>>(`/api/inbound/po/${row.id}`, {
    query: {
      page: detailPage.value,
      size: 10
    }
  });
  detailModal.value.data = detail.data;
  detailModal.value.title = detail.data.po_number!;
  isShowDetail.value = true;
}

async function handleShowEdit(row: PurchaseOrder) {
  resetForm();
  try {
    const detail = await $fetch<ResponseData<PurchaseOrder>>(`/api/inbound/po/${row.id}`);
    const po = detail.data;

    state.id = po.id;
    state.po_number = po.po_number ?? "";
    state.order_date = po.order_date ?? "";
    state.status = po.status ?? OrderStatus.OPEN;
    state.supplier_id = po.supplier_id ?? null;
    supplierSearch.value = po.supplier_id;
    state.purchase_order_lines = po.purchase_order_lines?.map(line => ({
      ...line,
      product: { ...line.product }
    })) ?? [];

    isOpen.value = true;
  } catch (error) {
    console.error("Failed to fetch Purchase Order for edit", error);
  }
}

async function savePurchaseOrder() {
  try {
    const body = {
      ...state,
      supplier_id: supplierSearch.value
    };

    if (state.id) {
      await $fetch(`/api/inbound/po`, {
        method: "PUT",
        body
      });
    } else {
      await $fetch("/api/inbound/po", {
        method: "POST",
        body: {
          ...body,
          status: OrderStatus.OPEN
        }
      });
    }

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
  state.order_date = new Date().toISOString().split('T')[0] ?? "";
  state.status = OrderStatus.OPEN;
  state.supplier_id = null;
  state.purchase_order_lines = [];
  supplierSearch.value = null;
}

const items = (row: PurchaseOrder): DropdownMenuItem[][] => {
  const menu: DropdownMenuItem[][] = [];
  menu.push([
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      onSelect: () => handleShowDetail(row),
    },

  ]);

  if (row.status === OrderStatus.OPEN) {
    menu.push([
      {
        label: "Cancel",
        icon: "i-heroicons-x-circle-20-solid",
        color: 'error',
        onSelect: () => cancelPurchaseOrder(row.id),
      },
      {
        label: "Edit",
        icon: "i-heroicons-pencil-square-20-solid",
        onSelect: () => handleShowEdit(row),
      },
    ])
  }
  return menu;
};


watch(detailPage, async () => {
  if (isShowDetail.value && detailModal.value.data) {
    await handleShowDetail(detailModal.value.data);
  }
});

</script>
