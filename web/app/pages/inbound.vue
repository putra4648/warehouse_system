<template>
  <div class="space-y-6">
    <!-- Page Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Inbound Management</h1>
        <p class="text-base-content/60 mt-1">Manage incoming shipments and receipts</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> New Shipment
      </button>
    </div>

    <!-- Stats -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">Pending Receipts</div>
          <div class="text-2xl font-bold text-warning">12</div>
        </div>
      </div>
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">In Transit</div>
          <div class="text-2xl font-bold text-info">8</div>
        </div>
      </div>
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm text-base-content/60">Received Today</div>
          <div class="text-2xl font-bold text-success">5</div>
        </div>
      </div>
    </div>

    <!-- Shipments Table -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg mb-4">Inbound Shipments</h2>
        <div ref="tabulatorInbound" />
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

const tabulatorInbound = ref(null)

const inboundData = [
  { id: 1, shipmentId: 'SHIP-001', supplier: 'Tech Suppliers Inc', quantity: 100, status: 'Pending', eta: '2024-01-15' },
  { id: 2, shipmentId: 'SHIP-002', supplier: 'Global Parts Ltd', quantity: 250, status: 'In Transit', eta: '2024-01-16' },
  { id: 3, shipmentId: 'SHIP-003', supplier: 'Electronics Plus', quantity: 50, status: 'Received', eta: '2024-01-14' },
  { id: 4, shipmentId: 'SHIP-004', supplier: 'Hardware World', quantity: 75, status: 'Pending', eta: '2024-01-17' },
]

onMounted(() => {
  if (tabulatorInbound.value) {
    new Tabulator(tabulatorInbound.value, {
      data: inboundData,
      autoColumns: false,
      columns: [
        { title: 'Shipment ID', field: 'shipmentId', width: 130 },
        { title: 'Supplier', field: 'supplier', width: 180 },
        { title: 'Quantity', field: 'quantity', width: 100, },
        { title: 'ETA', field: 'eta', width: 120 },
        {
          title: 'Status',
          field: 'status',
          width: 130,
          formatter: (cell) => {
            const value = cell.getValue()
            const statusMap = {
              Pending: 'badge-warning',
              'In Transit': 'badge-info',
              Received: 'badge-success',
            }
            return `<span class="badge ${statusMap[value]}">${value}</span>`
          },
        },
        {
          title: 'Actions',
          width: 150,
          formatter: () => '<button class="btn btn-sm btn-ghost">View</button>',
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
