<template>
  <UPage>
    <UPageHeader title="Report & Analysis">
      <template #description>
        Overview of warehouse performance and statistics
      </template>
    </UPageHeader>

    <UPageBody>
      <!-- KPI Cards -->
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
          <p class="text-xs text-green-500 mt-2 flex items-center">
            <UIcon name="i-heroicons-arrow-trending-up" class="w-4 h-4 mr-1" />
            {{ stat.trend }}
          </p>
        </UCard>
      </div>

      <!-- Charts -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
        <UCard>
          <template #header>
            <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
              Inbound vs Outbound
            </h3>
          </template>
          <div class="w-full">
            <BarChart :data="barData" :categories="barCategories" :y-axis="barYAxis" />
          </div>
        </UCard>

        <UCard>
          <template #header>
            <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
              Inventory By Category
            </h3>
          </template>
          <div class="w-full flex items-center justify-center">
            <DonutChart :data="donutData" :categories="donutCategories" />
          </div>
        </UCard>
      </div>

      <!-- Recent Activity -->
      <UCard :ui="{ body: { padding: '' } }">
        <template #header>
          <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
            Recent Transactions
          </h3>
        </template>
        <UTable :columns="columns" :data="recentTransactions">
          <template #status-cell="{ row }">
            <UBadge :color="row.status === 'Completed'
                ? 'green'
                : row.status === 'Pending'
                  ? 'orange'
                  : 'gray'
              " variant="subtle">
              {{ row.status }}
            </UBadge>
          </template>
        </UTable>
      </UCard>
    </UPageBody>
  </UPage>
</template>

<script setup>
const stats = [
  {
    label: "Total Revenue",
    value: "$45,231.89",
    icon: "i-heroicons-currency-dollar",
    trend: "+20.1% from last month",
  },
  {
    label: "Total Orders",
    value: "2,345",
    icon: "i-heroicons-shopping-cart",
    trend: "+15.3% from last month",
  },
  {
    label: "Inventory Value",
    value: "$120,400",
    icon: "i-heroicons-archive-box",
    trend: "+4.5% from last month",
  },
  {
    label: "Low Stock Items",
    value: "12",
    icon: "i-heroicons-exclamation-triangle",
    trend: "-2 items from last week",
  },
];

const recentTransactions = [
  {
    id: "TRX-001",
    type: "Inbound",
    details: "Acme Corp - 50 Items",
    date: "2024-05-01",
    status: "Completed",
  },
  {
    id: "TRX-002",
    type: "Outbound",
    details: "Order #1234 - John Doe",
    date: "2024-05-02",
    status: "Pending",
  },
  {
    id: "TRX-003",
    type: "Inbound",
    details: "Global Tech - 20 Items",
    date: "2024-05-03",
    status: "Completed",
  },
  {
    id: "TRX-004",
    type: "Outbound",
    details: "Order #5678 - Jane Smith",
    date: "2024-05-04",
    status: "Completed",
  },
];

const columns = [
  { accessorKey: "id", header: "ID" },
  { accessorKey: "type", header: "Type" },
  { accessorKey: "details", header: "Details" },
  { accessorKey: "date", header: "Date" },
  { accessorKey: "status", header: "Status" },
];

// Bar Chart Data (Array of Objects)
const barData = ref([
  { month: "Jan", inbound: 40, outbound: 20 },
  { month: "Feb", inbound: 20, outbound: 10 },
  { month: "Mar", inbound: 12, outbound: 22 },
  { month: "Apr", inbound: 39, outbound: 19 },
  { month: "May", inbound: 10, outbound: 20 },
  { month: "Jun", inbound: 40, outbound: 30 },
]);

const barCategories = {
  inbound: { name: "Inbound", color: "#3b82f6" },
  outbound: { name: "Outbound", color: "#ef4444" },
};

const barYAxis = ["inbound", "outbound"];

// Donut Chart Data (Array of Numbers)
const donutData = ref([40, 20, 20, 10]);

const donutCategories = {
  electronics: { name: "Electronics", color: "#3b82f6" },
  clothing: { name: "Clothing", color: "#10b981" },
  furniture: { name: "Furniture", color: "#f59e0b" },
  others: { name: "Others", color: "#6b7280" },
};
</script>
