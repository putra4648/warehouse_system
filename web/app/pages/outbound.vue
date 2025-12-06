<template>
  <div class="space-y-6">
    <!-- Page Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Outbound Management</h1>
        <p class="text-base-content/60 mt-1">Manage outgoing orders and shipments</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> New Order
      </button>
    </div>

    <!-- Stats -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">Pending Orders</div>
          <div class="text-2xl font-bold text-warning">23</div>
        </div>
      </div>
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">Shipped Today</div>
          <div class="text-2xl font-bold text-success">15</div>
        </div>
      </div>
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">Delivered</div>
          <div class="text-2xl font-bold text-info">342</div>
        </div>
      </div>
    </div>

    <!-- Orders Table -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg mb-4">Outbound Orders</h2>
        <div ref="tabulatorOutbound" />
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

const tabulatorOutbound = ref(null)

const outboundData = [
  { id: 1, orderId: 'ORD-001', customer: 'Acme Corp', items: 15, amount: 1250.00, status: 'Pending', dueDate: '2024-01-15' },
  { id: 2, orderId: 'ORD-002', customer: 'XYZ Industries', items: 8, amount: 890.50, status: 'Shipped', dueDate: '2024-01-14' },
  { id: 3, orderId: 'ORD-003', customer: 'Tech Solutions', items: 22, amount: 2100.00, status: 'Delivered', dueDate: '2024-01-13' },
  { id: 4, orderId: 'ORD-004', customer: 'Global Traders', items: 12, amount: 1580.00, status: 'Pending', dueDate: '2024-01-16' },
]

onMounted(() => {
  if (tabulatorOutbound.value) {
    new Tabulator(tabulatorOutbound.value, {
      data: outboundData,
      autoColumns: false,
      columns: [
        { title: 'Order ID', field: 'orderId', width: 130 },
        { title: 'Customer', field: 'customer', width: 180 },
        { title: 'Items', field: 'items', width: 80 },
        { title: 'Amount', field: 'amount', width: 120, formatter: 'money', formatterParams: { symbol: '$' } },
        { title: 'Due Date', field: 'dueDate', width: 120 },
        {
          title: 'Status',
          field: 'status',
          width: 130,
          formatter: (cell) => {
            const value = cell.getValue()
            const statusMap = {
              Pending: 'badge-warning',
              Shipped: 'badge-info',
              Delivered: 'badge-success',
            }
            return `<span class="badge ${statusMap[value]}">${value}</span>`
          },
        },
        {
          title: 'Actions',
          width: 150,
          formatter: () => '<button class="btn btn-sm btn-ghost">Track</button>',
        },
      ],
      layout: 'fitColumns',
      pagination: true,
      paginationMode: 'local',
      paginationSize: 10,
    })
  }
})
</script>
