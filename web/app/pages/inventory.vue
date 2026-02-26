<template>
  <UPage>
    <UPageHeader title="Inventory Management">
      <template #description>
        Real-time inventory tracking across all warehouses
      </template>
    </UPageHeader>

    <UPageBody>
      <!-- KPI Statistics Cards -->
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

      <!-- Inventory Table -->
      <UCard>
        <div class="flex flex-col sm:flex-row gap-3 px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Search by product, warehouse, or lot number..."
            icon="i-heroicons-magnifying-glass" class="flex-1" />
          <USelect v-model="selectedWarehouse" :items="warehouseOptions" placeholder="All Warehouses"
            icon="i-heroicons-building-storefront" class="w-full sm:w-48" />
        </div>

        <UTable :columns="columns" :data="inventory" :loading="status === 'pending'">
          <template #product-cell="{ row }">
            <div class="flex items-center gap-3">
              <div>
                <span class="font-medium text-gray-900 dark:text-white">
                  {{ (row.original as any).product }}
                </span>
                <p class="text-xs text-gray-500 dark:text-gray-400">
                  {{ (row.original as any).sku }}
                </p>
              </div>
            </div>
          </template>

          <template #quantity-cell="{ row }">
            <div class="flex items-center gap-2">
              <span class="font-semibold text-gray-900 dark:text-white">
                {{ (row.original as any).quantity }}
              </span>
              <span v-if="(row.original as any).reservedQty > 0" class="text-xs text-gray-500">
                ({{ (row.original as any).reservedQty }} reserved)
              </span>
            </div>
          </template>

          <template #status-cell="{ row }">
            <UBadge :color="(row.original as any).quantity > 50
              ? 'success'
              : (row.original as any).quantity > 0
                ? 'warning'
                : 'error'
              " variant="subtle">
              {{
                (row.original as any).quantity > 50
                  ? 'In Stock'
                  : (row.original as any).quantity > 0
                    ? 'Low Stock'
                    : 'Out of Stock'
              }}
            </UBadge>
          </template>

          <template #expiry-cell="{ row }">
            <span v-if="(row.original as any).expiredDate" class="text-sm text-gray-600 dark:text-gray-300">
              {{ formatDate((row.original as any).expiredDate) }}
            </span>
            <span v-else class="text-sm text-gray-400">-</span>
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="actionItems(row.original as any)">
              <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :items-per-page="size" :total="total" />
        </div>

      </UCard>

      <!-- Stock Adjustment Modal -->
      <UModal v-model:open="isAdjustModalOpen" title="Adjust Stock" scrollable>
        <template #body>
          <div v-if="selectedItem" class="space-y-4">
            <!-- Product Info -->
            <div class="flex items-center gap-3 p-3 bg-gray-50 dark:bg-gray-800 rounded-lg">
              <UAvatar :src="selectedItem.image" :alt="selectedItem.product" size="md" />
              <div>
                <h4 class="font-semibold text-gray-900 dark:text-white">
                  {{ selectedItem.product }}
                </h4>
                <p class="text-sm text-gray-500">{{ selectedItem.warehouse }}</p>
              </div>
            </div>

            <!-- Current Stock -->
            <div class="p-3 bg-blue-50 dark:bg-blue-900/20 rounded-lg">
              <p class="text-sm text-gray-600 dark:text-gray-300">Current Stock</p>
              <p class="text-2xl font-bold text-gray-900 dark:text-white">
                {{ selectedItem.quantity }} units
              </p>
            </div>

            <!-- Adjustment Form -->
            <UForm :state="adjustmentState" class="space-y-4">
              <UFormField label="Adjustment Type" name="type">
                <USelect v-model="adjustmentState.type" :items="[
                  { label: 'Add Stock', value: 'add' },
                  { label: 'Remove Stock', value: 'remove' }
                ]" class="w-full" />
              </UFormField>

              <UFormField label="Quantity" name="quantity">
                <UInput v-model="adjustmentState.quantity" type="number" min="1" placeholder="Enter quantity"
                  class="w-full" />
              </UFormField>

              <UFormField label="Reason / Notes" name="notes">
                <UTextarea v-model="adjustmentState.notes" placeholder="Enter reason for adjustment..." :rows="3"
                  class="w-full" />
              </UFormField>

              <!-- New Stock Preview -->
              <div v-if="adjustmentState.quantity" class="p-3 bg-green-50 dark:bg-green-900/20 rounded-lg">
                <p class="text-sm text-gray-600 dark:text-gray-300">New Stock Level</p>
                <p class="text-2xl font-bold text-green-600 dark:text-green-400">
                  {{ calculateNewStock() }} units
                </p>
              </div>

              <UButton type="button" block @click="submitAdjustment">
                Confirm Adjustment
              </UButton>
            </UForm>
          </div>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { InventoryItem } from '~~/types/inventory'
import type PaginationResponse from '~~/server/utils/pagination'

// Reactive state
const q = ref('');
const selectedWarehouse = ref('all');
const page = ref(1);
const size = ref(10);
const isAdjustModalOpen = ref(false);
const selectedItem = ref<InventoryItem | null>(null);

const { data, status, refresh } = await useFetch<PaginationResponse<InventoryItem>>("/api/inventory", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
})

const inventory = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta.total || 0)

const { data: warehouseData } = await useFetch<PaginationResponse<any>>("/api/warehouses", {
  query: { page: 0, size: 100 }
})

const warehouseOptions = computed(() => {
  const warehouses = warehouseData.value?.data ?? []
  return [
    { label: 'All Warehouses', value: 'all' },
    ...warehouses.map(w => ({ label: w.name, value: w.name }))
  ];
});

// KPI Statistics
const stats = computed(() => {
  const totalItems = total.value;
  // Note: For real stats, we might need a separate endpoint for dashboard/stats
  return [
    {
      label: 'Total Items',
      value: totalItems.toString(),
      icon: 'i-heroicons-cube',
      trend: 'Active items',
      trendPositive: true,
    },
    // ... possibly others if backend provides them
  ];
});

// Table columns
const columns = [
  { accessorKey: 'product', header: 'Product' },
  { accessorKey: 'warehouse', header: 'Warehouse' },
  { accessorKey: 'lotNumber', header: 'Lot Number' },
  { accessorKey: 'quantity', header: 'Quantity' },
  { accessorKey: 'status', header: 'Status' },
  { accessorKey: 'expiry', header: 'Expiry Date' },
  { accessorKey: 'actions', header: '' },
];

// Adjustment modal state
const adjustmentState = reactive({
  type: 'add',
  quantity: 0,
  notes: '',
});

// Action items for dropdown
const actionItems = (item: InventoryItem) => [
  [
    {
      label: 'View Details',
      icon: 'i-heroicons-eye',
      click: () => console.log('View details:', item.id),
    },
    {
      label: 'Adjust Stock',
      icon: 'i-heroicons-arrows-up-down',
      click: () => openAdjustModal(item),
    },
    {
      label: 'View History',
      icon: 'i-heroicons-clock',
      click: () => console.log('View history:', item.id),
    },
  ],
];

// Helper functions
const formatDate = (dateStr: string) => {
  return new Date(dateStr).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  });
};

const openAdjustModal = (item: InventoryItem) => {
  selectedItem.value = item;
  adjustmentState.type = 'add';
  adjustmentState.quantity = 0;
  adjustmentState.notes = '';
  isAdjustModalOpen.value = true;
};

const calculateNewStock = () => {
  if (!selectedItem.value || !adjustmentState.quantity) return 0;
  const current = selectedItem.value.quantity;
  const adjustment = parseInt(adjustmentState.quantity.toString());
  return adjustmentState.type === 'add'
    ? current + adjustment
    : Math.max(0, current - adjustment);
};

const submitAdjustment = async () => {
  // In real implementation, this would call an API
  console.log('Submitting adjustment:', {
    item: selectedItem.value,
    adjustment: adjustmentState,
    newStock: calculateNewStock(),
  });
  isAdjustModalOpen.value = false;
};
</script>

