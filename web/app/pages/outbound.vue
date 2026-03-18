<template>
  <UPage>
    <UPageHeader title="Outbound">
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
        <UButton icon="i-heroicons-plus" color="primary" label="Create Outbound" @click="isOpen = true" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter outbound..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="salesOrders" :loading="status === 'pending'">
          <template #status-cell="{ row }">
            <UBadge
              :color="row.original.status === 'Completed'
                ? 'success'
                : row.original.status === 'Processing'
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

      <UModal v-model:open="isOpen" title="Create Outbound" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4" @submit="saveSalesOrder">
            <UFormField label="SO Number" name="soNumber">
              <UInput v-model="state.soNumber" placeholder="Enter SO number" />
            </UFormField>
            <UFormField label="Date" name="orderDate">
              <UInput v-model="state.orderDate" type="date" />
            </UFormField>
            <UFormField label="Customer ID" name="customerId">
              <UInput v-model="state.customerId" type="number" />
            </UFormField>
            <UFormField label="Status" name="status">
              <USelect v-model="state.status" :items="['Processing', 'Completed', 'Cancelled']" />
            </UFormField>
            <UButton type="submit" block color="primary">Create Outbound SO</UButton>
          </UForm>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { TableColumn } from '@nuxt/ui';
import type { SalesOrder } from '~~/types/outbound';
import type PaginationResponse from '~~/server/utils/pagination';

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);

const { data, status, refresh } = await useFetch<PaginationResponse<SalesOrder>>("/api/outbound/so", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
});

const salesOrders = computed(() => data.value?.data ?? []);
const total = computed(() => data.value?.meta.total || 0);

// Placeholder for stats since backend doesn't provide them yet
const stats = computed(() => [
  {
    label: 'Total SO',
    value: total.value.toString(),
    icon: 'i-heroicons-cube',
    trend: 'Overall orders',
    trendPositive: true,
  },
  {
    label: 'Processing',
    value: salesOrders.value.filter(s => s.status === 'Processing').length.toString(),
    icon: 'i-heroicons-arrow-path',
    trend: 'Orders in work',
    trendPositive: false,
  },
]);

const columns: TableColumn<SalesOrder>[] = [
  { accessorKey: "id", header: "ID" },
  { accessorKey: "soNumber", header: "SO Number" },
  { accessorKey: "orderDate", header: "Order Date" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  id: null as number | null,
  soNumber: "",
  orderDate: new Date().toISOString().split('T')[0],
  status: "Processing",
  customerId: null as number | null,
  salesOrderLines: []
});

async function saveSalesOrder() {
  try {
    const method = state.id ? 'PUT' : 'POST';
    const body = state.id ? state : [state]; // Assuming backend accepts array for new records
    
    await $fetch("/api/outbound/so", {
      method,
      body
    });
    
    isOpen.value = false;
    resetForm();
    refresh();
  } catch (error) {
    console.error("Failed to save Sales Order", error);
  }
}

function resetForm() {
  state.id = null;
  state.soNumber = "";
  state.orderDate = new Date().toISOString().split('T')[0];
  state.status = "Processing";
  state.customerId = null;
  state.salesOrderLines = [];
}

const items = (row: SalesOrder) => [
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
