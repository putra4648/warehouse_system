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
        <UButton icon="i-heroicons-plus" color="primary" label="Create Inbound" @click="isOpen = true" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter inbound..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="filteredRows" :loading="pending">
          <template #status-cell="{ row }">
            <UBadge :color="row.original.status === 'Completed'
              ? 'success'
              : row.original.status === 'Pending'
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
          <UPagination v-model="page" :page-count="pageCount" :total="inbounds.length" />
        </div>
      </UCard>

      <UModal v-model:open="isOpen" title="Create Inbound" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4">
            <UFormField label="Supplier" name="supplier">
              <USelect v-model="state.supplier" class="w-full" :items="['Acme Corp', 'Global Tech', 'Logistics Pro']" />
            </UFormField>
            <UFormField label="Date" name="date">
              <UInput v-model="state.date" type="date" class="w-full" />
            </UFormField>
            <UFormField label="Status" name="status">
              <USelect v-model="state.status" class="w-full" :items="['Pending', 'Completed', 'Cancelled']" />
            </UFormField>
            <UButton type="submit" block>Create Inbound</UButton>
          </UForm>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { InventoryItem } from '../../types/inventory'
import type { Inbound } from '../../types/inbound'
import type { TableRow, TableColumn } from '@nuxt/ui'

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const pageCount = 5;
const pending = ref(false);

const columns: TableColumn<Inbound>[] = [
  { accessorKey: "id", header: "ID" },
  { accessorKey: "supplier", header: "Supplier" },
  { accessorKey: "date", header: "Date" },
  { accessorKey: "items", header: "Total Items" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const inbounds: Inbound[] = [
  {
    id: "IN-001",
    supplier: "Acme Corp",
    date: "2024-05-01",
    quantity: 50,
    status: "Completed",
  },
  {
    id: "IN-002",
    supplier: "Global Tech",
    date: "2024-05-02",
    quantity: 20,
    status: "Pending",
  },
  {
    id: "IN-003",
    supplier: "Logistics Pro",
    date: "2024-05-03",
    quantity: 100,
    status: "Completed",
  },
];

const
  filteredRows = computed(() => {
    if (!q.value) {
      return inbounds.slice((page.value - 1) * pageCount, page.value * pageCount);
    }

    return inbounds.filter((inbound) => {
      return Object.values(inbound).some((value) => {
        return String(value).toLowerCase().includes(q.value.toLowerCase());
      });
    })
  });

const inventory: InventoryItem[] = [
  {
    id: 1,
    product: 'MacBook Pro M3',
    sku: 'APP-MBP-001',
    warehouse: 'Warehouse A',
    quantity: 120,
    reserved_qty: 15,
    lot_number: 'LOT-2024-001',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 2,
    product: 'iPhone 15 Pro',
    sku: 'APP-IP15-001',
    warehouse: 'Warehouse A',
    quantity: 45,
    reserved_qty: 10,
    lot_number: 'LOT-2024-002',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 3,
    product: 'Sony WH-1000XM5',
    sku: 'SON-WH-001',
    warehouse: 'Warehouse B',
    quantity: 85,
    reserved_qty: 5,
    lot_number: 'LOT-2024-003',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 4,
    product: 'Dell XPS 15',
    sku: 'DEL-XPS-001',
    warehouse: 'Warehouse A',
    quantity: 30,
    reserved_qty: 8,
    lot_number: 'LOT-2024-004',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 5,
    product: 'Samsung Galaxy S24',
    sku: 'SAM-GS24-001',
    warehouse: 'Warehouse C',
    quantity: 0,
    reserved_qty: 0,
    lot_number: 'LOT-2024-005',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 6,
    product: 'LG OLED TV 55"',
    sku: 'LG-OLED-001',
    warehouse: 'Warehouse B',
    quantity: 25,
    reserved_qty: 3,
    lot_number: 'LOT-2024-006',
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 7,
    product: 'Logitech MX Master 3',
    sku: 'LOG-MX3-001',
    warehouse: 'Warehouse A',
    quantity: 150,
    reserved_qty: 20,
    lot_number: 'LOT-2024-007',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 8,
    product: 'Herman Miller Aeron',
    sku: 'HM-AER-001',
    warehouse: 'Warehouse C',
    quantity: 18,
    reserved_qty: 2,
    lot_number: 'LOT-2024-008',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 9,
    product: 'Canon EOS R5',
    sku: 'CAN-R5-001',
    warehouse: 'Warehouse B',
    quantity: 12,
    reserved_qty: 1,
    lot_number: 'LOT-2024-009',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 10,
    product: 'Nintendo Switch OLED',
    sku: 'NIN-SW-001',
    warehouse: 'Warehouse A',
    quantity: 95,
    reserved_qty: 12,
    lot_number: 'LOT-2024-010',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 11,
    product: 'Bose QuietComfort 45',
    sku: 'BOS-QC45-001',
    warehouse: 'Warehouse C',
    quantity: 8,
    reserved_qty: 0,
    lot_number: 'LOT-2024-011',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 12,
    product: 'Microsoft Surface Pro 9',
    sku: 'MS-SP9-001',
    warehouse: 'Warehouse B',
    quantity: 42,
    reserved_qty: 6,
    lot_number: 'LOT-2024-012',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 13,
    product: 'Apple Watch Series 9',
    sku: 'APP-AW9-001',
    warehouse: 'Warehouse A',
    quantity: 68,
    reserved_qty: 9,
    lot_number: 'LOT-2024-013',

    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 14,
    product: 'Dyson V15 Detect',
    sku: 'DYS-V15-001',
    warehouse: 'Warehouse C',
    quantity: 22,
    reserved_qty: 4,
    lot_number: 'LOT-2024-014',
    image: 'https://avatars.githubusercontent.com/u/739984?v=4',
  },
  {
    id: 15,
    product: 'iPad Pro 12.9"',
    sku: 'APP-IPP-001',
    warehouse: 'Warehouse B',
    quantity: 55,
    reserved_qty: 7,
    lot_number: 'LOT-2024-015',

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
      trend: `${inventory.reduce((sum, item) => sum + item.reserved_qty, 0)} reserved`,
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

const state = reactive({
  supplier: undefined,
  date: "",
  status: "Pending",
});

const items = (row: TableRow<Inbound>) => [
  [
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      onSelect: () => console.log("View", row.id),
    },
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      onSelect: () => console.log("Edit", row.id),
    },
  ],
];
</script>
