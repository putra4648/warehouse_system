<template>
  <div class="space-y-6">
    <!-- Page Header -->
    <div>
      <h1 class="text-3xl font-bold text-base-content">Reports & Analysis</h1>
      <p class="text-base-content/60 mt-1">View detailed warehouse analytics and insights</p>
    </div>

    <!-- Filter Section -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
          <select class="select select-bordered">
            <option selected>Last 30 Days</option>
            <option>Last 7 Days</option>
            <option>Last 3 Months</option>
            <option>Custom Range</option>
          </select>
          <select class="select select-bordered">
            <option selected>All Warehouses</option>
            <option>Warehouse A</option>
            <option>Warehouse B</option>
          </select>
          <select class="select select-bordered">
            <option selected>All Categories</option>
            <option>Electronics</option>
            <option>Hardware</option>
          </select>
          <button class="btn btn-primary">
            <span>📊</span> Export Report
          </button>
        </div>
      </div>
    </div>

    <!-- Analysis Cards -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <h3 class="card-title text-sm">Inventory Turnover Rate</h3>
          <div class="text-3xl font-bold text-primary mt-2">4.2x</div>
          <p class="text-xs text-success mt-2">↑ 0.3x vs last month</p>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <h3 class="card-title text-sm">Warehouse Utilization</h3>
          <div class="text-3xl font-bold text-accent mt-2">78%</div>
          <p class="text-xs text-base-content/60 mt-2">Optimal capacity</p>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <h3 class="card-title text-sm">Order Fulfillment Rate</h3>
          <div class="text-3xl font-bold text-success mt-2">96.8%</div>
          <p class="text-xs text-success mt-2">↑ 1.2% vs last month</p>
        </div>
      </div>
    </div>

    <!-- Detailed Reports Table -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg mb-4">Detailed Reports</h2>
        <div class="space-y-2 mb-4">
          <button class="btn btn-outline btn-sm">Inventory Summary</button>
          <button class="btn btn-outline btn-sm">Movement Analysis</button>
          <button class="btn btn-outline btn-sm">Stock Age Report</button>
          <button class="btn btn-primary btn-sm">Warehouse Performance</button>
        </div>
        <div ref="tabulatorReports" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Tabulator } from 'tabulator-tables'
import 'tabulator-tables/dist/css/tabulator.min.css'
import { definePageMeta, ref, onMounted } from '#imports'

definePageMeta({
  layout: 'default',
})

const tabulatorReports = ref(null)

const reportData = [
  { warehouse: 'Warehouse A', items: 450, value: 125000, utilization: '85%', turnover: 4.5 },
  { warehouse: 'Warehouse B', items: 380, value: 98000, utilization: '72%', turnover: 3.8 },
  { warehouse: 'Warehouse C', items: 520, value: 145000, utilization: '88%', turnover: 4.9 },
  { warehouse: 'Warehouse D', items: 290, value: 72000, utilization: '65%', turnover: 3.2 },
]

onMounted(() => {
  if (tabulatorReports.value) {
    new Tabulator(tabulatorReports.value, {
      data: reportData,
      autoColumns: false,
      columns: [
        { title: 'Warehouse', field: 'warehouse', width: 150 },
        { title: 'Items Count', field: 'items', width: 120 },
        { title: 'Total Value', field: 'value', width: 130, formatter: 'money', formatterParams: { symbol: '$' } },
        { title: 'Utilization', field: 'utilization', width: 120 },
        { title: 'Turnover Rate', field: 'turnover', width: 130 },
      ],
      layout: 'fitColumns',
    })
  }
})
</script>
