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

        <UTable :columns="columns" :data="filteredRows" :loading="pending">
          <template #product-cell="{ row }">
            <div class="flex items-center gap-3">
              <UAvatar :src="row.original.image" :alt="row.original.product" size="sm" />
              <div>
                <span class="font-medium text-gray-900 dark:text-white">
                  {{ row.original.product }}
                </span>
                <p class="text-xs text-gray-500 dark:text-gray-400">
                  {{ row.original.sku }}
                </p>
              </div>
            </div>
          </template>

          <template #quantity-cell="{ row }">
            <div class="flex items-center gap-2">
              <span class="font-semibold text-gray-900 dark:text-white">
                {{ row.original.quantity }}
              </span>
              <span v-if="row.original.reservedQty > 0" class="text-xs text-gray-500">
                ({{ row.original.reservedQty }} reserved)
              </span>
            </div>
          </template>

          <template #status-cell="{ row }">
            <UBadge :color="row.original.quantity > 50
              ? 'success'
              : row.original.quantity > 0
                ? 'warning'
                : 'error'
              " variant="subtle">
              {{
                row.original.quantity > 50
                  ? 'In Stock'
                  : row.original.quantity > 0
                    ? 'Low Stock'
                    : 'Out of Stock'
              }}
            </UBadge>
          </template>

          <template #expiry-cell="{ row }">
            <span v-if="row.original.expiredDate" class="text-sm text-gray-600 dark:text-gray-300">
              {{ formatDate(row.original.expiredDate) }}
            </span>
            <span v-else class="text-sm text-gray-400">-</span>
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="actionItems(row.original)">
              <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :page-count="pageCount" :total="filteredInventory.length" />
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
import type { TableColumn } from '@nuxt/ui';

type InventoryItem = {
  id: number;
  product: string;
  sku: string;
  warehouse: string;
  quantity: number;
  reservedQty: number;
  lotNumber: string;
  expiredDate: string | null;
  image: string;
};

// Reactive state
const q = ref('');
const selectedWarehouse = ref('all');
const page = ref(1);
const pageCount = 5;
const pending = ref(false);
const isAdjustModalOpen = ref(false);
const selectedItem = ref<InventoryItem | null>(null);

// Table columns
const columns: TableColumn<InventoryItem>[] = [
  { accessorKey: 'product', header: 'Product' },
  { accessorKey: 'warehouse', header: 'Warehouse' },
  { accessorKey: 'lotNumber', header: 'Lot Number' },
  { accessorKey: 'quantity', header: 'Quantity' },
  { accessorKey: 'status', header: 'Status' },
  { accessorKey: 'expiry', header: 'Expiry Date' },
  { accessorKey: 'actions', header: '' },
];

// Mock inventory data
const inventory: InventoryItem[] = [
  {
    id: 1,
    product: 'MacBook Pro M3',
    sku: 'APP-MBP-001',
    warehouse: 'Warehouse A',
    quantity: 120,
    reservedQty: 15,
    lotNumber: 'LOT-2024-001',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 2,
    product: 'iPhone 15 Pro',
    sku: 'APP-IP15-001',
    warehouse: 'Warehouse A',
    quantity: 45,
    reservedQty: 10,
    lotNumber: 'LOT-2024-002',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 3,
    product: 'Sony WH-1000XM5',
    sku: 'SON-WH-001',
    warehouse: 'Warehouse B',
    quantity: 85,
    reservedQty: 5,
    lotNumber: 'LOT-2024-003',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 4,
    product: 'Dell XPS 15',
    sku: 'DEL-XPS-001',
    warehouse: 'Warehouse A',
    quantity: 30,
    reservedQty: 8,
    lotNumber: 'LOT-2024-004',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 5,
    product: 'Samsung Galaxy S24',
    sku: 'SAM-GS24-001',
    warehouse: 'Warehouse C',
    quantity: 0,
    reservedQty: 0,
    lotNumber: 'LOT-2024-005',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 6,
    product: 'LG OLED TV 55"',
    sku: 'LG-OLED-001',
    warehouse: 'Warehouse B',
    quantity: 25,
    reservedQty: 3,
    lotNumber: 'LOT-2024-006',
    expiredDate: '2026-12-31',
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 7,
    product: 'Logitech MX Master 3',
    sku: 'LOG-MX3-001',
    warehouse: 'Warehouse A',
    quantity: 150,
    reservedQty: 20,
    lotNumber: 'LOT-2024-007',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 8,
    product: 'Herman Miller Aeron',
    sku: 'HM-AER-001',
    warehouse: 'Warehouse C',
    quantity: 18,
    reservedQty: 2,
    lotNumber: 'LOT-2024-008',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 9,
    product: 'Canon EOS R5',
    sku: 'CAN-R5-001',
    warehouse: 'Warehouse B',
    quantity: 12,
    reservedQty: 1,
    lotNumber: 'LOT-2024-009',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 10,
    product: 'Nintendo Switch OLED',
    sku: 'NIN-SW-001',
    warehouse: 'Warehouse A',
    quantity: 95,
    reservedQty: 12,
    lotNumber: 'LOT-2024-010',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 11,
    product: 'Bose QuietComfort 45',
    sku: 'BOS-QC45-001',
    warehouse: 'Warehouse C',
    quantity: 8,
    reservedQty: 0,
    lotNumber: 'LOT-2024-011',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 12,
    product: 'Microsoft Surface Pro 9',
    sku: 'MS-SP9-001',
    warehouse: 'Warehouse B',
    quantity: 42,
    reservedQty: 6,
    lotNumber: 'LOT-2024-012',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 13,
    product: 'Apple Watch Series 9',
    sku: 'APP-AW9-001',
    warehouse: 'Warehouse A',
    quantity: 68,
    reservedQty: 9,
    lotNumber: 'LOT-2024-013',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 14,
    product: 'Dyson V15 Detect',
    sku: 'DYS-V15-001',
    warehouse: 'Warehouse C',
    quantity: 22,
    reservedQty: 4,
    lotNumber: 'LOT-2024-014',
    expiredDate: '2027-06-30',
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 15,
    product: 'iPad Pro 12.9"',
    sku: 'APP-IPP-001',
    warehouse: 'Warehouse B',
    quantity: 55,
    reservedQty: 7,
    lotNumber: 'LOT-2024-015',
    expiredDate: null,
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
];

// KPI Statistics
const stats = computed(() => {
  const totalItems = inventory.length;
  const totalStock = inventory.reduce((sum, item) => sum + item.quantity, 0);
  const lowStockItems = inventory.filter(item => item.quantity > 0 && item.quantity <= 50).length;
  const warehouses = [...new Set(inventory.map(item => item.warehouse))].length;

  return [
    {
      label: 'Total Items',
      value: totalItems.toString(),
      icon: 'i-heroicons-cube',
      trend: `${inventory.filter(i => i.quantity > 0).length} in stock`,
      trendPositive: true,
    },
    {
      label: 'Total Stock',
      value: totalStock.toLocaleString(),
      icon: 'i-heroicons-archive-box',
      trend: `${inventory.reduce((sum, item) => sum + item.reservedQty, 0)} reserved`,
      trendPositive: false,
    },
    {
      label: 'Low Stock Alerts',
      value: lowStockItems.toString(),
      icon: 'i-heroicons-exclamation-triangle',
      trend: 'Requires attention',
      trendPositive: false,
    },
    {
      label: 'Warehouses',
      value: warehouses.toString(),
      icon: 'i-heroicons-building-storefront',
      trend: 'Active locations',
      trendPositive: false,
    },
  ];
});

// Warehouse filter options
const warehouseOptions = computed(() => {
  const warehouses = [...new Set(inventory.map(item => item.warehouse))];
  return [
    { label: 'All Warehouses', value: 'all' },
    ...warehouses.map(w => ({ label: w, value: w }))
  ];
});

// Filtered inventory based on search and warehouse
const filteredInventory = computed(() => {
  let filtered = inventory;

  // Filter by warehouse
  if (selectedWarehouse.value !== 'all') {
    filtered = filtered.filter(item => item.warehouse === selectedWarehouse.value);
  }

  // Filter by search query
  if (q.value) {
    filtered = filtered.filter(item => {
      const searchLower = q.value.toLowerCase();
      return (
        item.product.toLowerCase().includes(searchLower) ||
        item.warehouse.toLowerCase().includes(searchLower) ||
        item.lotNumber.toLowerCase().includes(searchLower) ||
        item.sku.toLowerCase().includes(searchLower)
      );
    });
  }

  return filtered;
});

// Paginated rows
const filteredRows = computed(() => {
  const start = (page.value - 1) * pageCount;
  const end = start + pageCount;
  return filteredInventory.value.slice(start, end);
});

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

const submitAdjustment = () => {
  console.log('Submitting adjustment:', {
    item: selectedItem.value,
    adjustment: adjustmentState,
    newStock: calculateNewStock(),
  });
  isAdjustModalOpen.value = false;
  // In real implementation, this would call an API
};
</script>
