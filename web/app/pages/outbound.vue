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
      <div class="flex items-center justify-between mb-6">
        <h1 class="text-2xl font-bold text-gray-900 dark:text-white"></h1>
        <UButton icon="i-heroicons-plus" color="primary" label="Create Outbound" @click="isOpen = true" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter outbound..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="filteredRows" :loading="pending">
          <template #status-cell="{ row }">
            <UBadge :color="row.original.status === 'Completed'
              ? 'success'
              : row.original.status === 'Processing'
                ? 'primary'
                : 'secondary'
              " variant="subtle">
              {{ row.original.status }}
            </UBadge>
          </template>

          <template #actions-cell="{ row }">
            <UDropdownMenu :items="items(row)">
              <UButton color="secondary" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
            </UDropdownMenu>
          </template>
        </UTable>

        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="page" :page-count="pageCount" :total="outbounds.length" />
        </div>
      </UCard>

      <UModal v-model:open="isOpen" title="Create Outbound" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4">
            <UFormField label="Customer" name="customer">
              <UInput v-model="state.customer" />
            </UFormField>
            <UFormField label="Date" name="date">
              <UInput v-model="state.date" type="date" />
            </UFormField>
            <UFormField label="Destination" name="destination">
              <UInput v-model="state.destination" />
            </UFormField>
            <UFormField label="Status" name="status">
              <USelect v-model="state.status" :items="['Processing', 'Completed', 'Cancelled']" />
            </UFormField>
            <UButton type="submit" block>Create Outbound</UButton>
          </UForm>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { TableColumn, TableRow } from '@nuxt/ui';
import type { Outbound } from '~~/types/outbound';
import type { InventoryItem } from '~~/types/inventory';

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const pageCount = 5;
const pending = ref(false);

const inventory: InventoryItem[] = [
  {
    id: 1,
    product: 'MacBook Pro M3',
    sku: 'APP-MBP-001',
    warehouse: 'Warehouse A',
    quantity: 120,
    reservedQty: 15,
    lotNumber: 'LOT-2024-001',

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

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
];

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

const columns: TableColumn<Outbound>[] = [
  { accessorKey: "id", header: "ID" },
  { accessorKey: "customer", header: "Customer" },
  { accessorKey: "destination", header: "Destination" },
  { accessorKey: "date", header: "Date" },
  { accessorKey: "items", header: "Total Items" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const outbounds: Outbound[] = [
  {
    id: "OUT-001",
    customer: "John Doe",
    destination: "New York, NY",
    date: "2024-05-01",
    quantity: 2,
    status: "Completed",
  },
  {
    id: "OUT-002",
    customer: "Jane Smith",
    destination: "Chicago, IL",
    date: "2024-05-02",
    quantity: 5,
    status: "Processing",
  },
  {
    id: "OUT-003",
    customer: "Bob Johnson",
    destination: "Miami, FL",
    date: "2024-05-03",
    quantity: 1,
    status: "Completed",
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return outbounds.slice(
      (page.value - 1) * pageCount,
      page.value * pageCount
    );
  }

  return outbounds.filter((outbound) => {
    return Object.values(outbound).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  customer: "",
  destination: "",
  date: "",
  status: "Processing",
});

const items = (row: TableRow<Outbound>) => [
  [
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      click: () => console.log("View", row.id),
    },
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      click: () => console.log("Edit", row.id),
    },
  ],
];
</script>
