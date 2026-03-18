<template>
  <UPage>
    <UPageHeader title="Inbound">
      <template #description>
        Received Product
      </template>
    </UPageHeader>

    <UPageBody>
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
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
            <UIcon
              :name="stat.icon"
              class="w-8 h-8 text-primary-500 bg-primary-100 dark:bg-primary-900 rounded-full p-1.5" />
          </div>
          <p :class="['text-xs mt-2 flex items-center', stat.trendPositive ? 'text-green-500' : 'text-gray-500']">
            <UIcon
              :name="stat.trendPositive ? 'i-heroicons-arrow-trending-up' : 'i-heroicons-minus'"
              class="w-4 h-4 mr-1" />
            {{ stat.trend }}
          </p>
        </UCard>
      </div>
      <div class="flex items-center justify-between mb-6">
        <h1 class="text-2xl font-bold text-gray-900 dark:text-white" />
        <UButton icon="i-heroicons-plus" color="primary" label="Create Inbound" @click="isOpen = true" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter inbound..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="purchaseOrders" :loading="status === 'pending'">
          <template #status-cell="{ row }">
            <UBadge
              :color="row.original.status === 'Completed'
                ? 'success'
                : row.original.status === 'Pending'
                  ? 'primary'
                  : 'secondary'
                " variant="subtle">
              {{ row.original.status }}
            </UBadge>
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="items(row.original)">
              <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :items-per-page="size" :total="total" />
        </div>
      </UCard>

      <UModal v-model:open="isOpen" title="Create Inbound" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4" @submit="savePurchaseOrder">
            <UFormField label="PO Number" name="poNumber">
              <UInput v-model="state.poNumber" class="w-full" placeholder="Enter PO number" />
            </UFormField>
            <UFormField label="Supplier ID" name="supplierId">
              <UInput v-model="state.supplierId" type="number" class="w-full" />
            </UFormField>
            <UFormField label="Date" name="orderDate">
              <UInput v-model="state.orderDate" type="date" class="w-full" />
            </UFormField>
            <UFormField label="Status" name="status">
              <USelect v-model="state.status" class="w-full" :items="['Pending', 'Completed', 'Cancelled']" />
            </UFormField>
            <UButton type="submit" block color="primary">Create Inbound PO</UButton>
          </UForm>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { PurchaseOrder } from '~~/types/inbound'
import type PaginationResponse from '~~/server/utils/pagination'
import type { TableColumn } from '@nuxt/ui'

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);

const { data, status, refresh } = await useFetch<PaginationResponse<PurchaseOrder>>("/api/inbound/po", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
});

const purchaseOrders = computed(() => data.value?.data ?? []);
const total = computed(() => data.value?.meta.total || 0);

// Placeholder for stats since backend doesn't provide them yet
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
    value: purchaseOrders.value.filter(p => p.status === 'Pending').length.toString(),
    icon: 'i-heroicons-clock',
    trend: 'Requires receiving',
    trendPositive: false,
  },
]);

const columns: TableColumn<PurchaseOrder>[] = [
  { accessorKey: "id", header: "ID" },
  { accessorKey: "poNumber", header: "PO Number" },
  { accessorKey: "orderDate", header: "Order Date" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  id: null as number | null,
  poNumber: "",
  orderDate: new Date().toISOString().split('T')[0],
  status: "Pending",
  supplierId: null as number | null,
  purchaseOrderLines: []
});

async function savePurchaseOrder() {
  try {
    const method = state.id ? 'PUT' : 'POST';
    const body = state.id ? state : [state]; // Assuming backend accepts array for new records
    
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
  state.poNumber = "";
  state.orderDate = new Date().toISOString().split('T')[0];
  state.status = "Pending";
  state.supplierId = null;
  state.purchaseOrderLines = [];
}

const items = (row: PurchaseOrder) => [
  [
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      onSelect: () => console.log("View", row.id),
    },
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      onSelect: () => Object.assign(state, row),
    },
  ],
];
</script>
