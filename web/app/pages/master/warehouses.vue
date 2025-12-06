<template>
  <div class="space-y-6">
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Warehouse Master</h1>
        <p class="text-base-content/60 mt-1">Manage warehouse locations and capacity</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> Add Warehouse
      </button>
    </div>

    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <input type="text" placeholder="Search warehouses..." class="input input-bordered mb-4" />
        <div ref="tabulatorWarehouses" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import TabulatorTables from 'tabulator-tables'
import { definePageMeta, ref, onMounted } from '#imports'

definePageMeta({
  layout: 'default',
})

const tabulatorWarehouses = ref(null)

const warehouseData = [
  { id: 1, code: 'WH-A', name: 'Warehouse A', location: 'New York', capacity: 5000, utilization: '85%' },
  { id: 2, code: 'WH-B', name: 'Warehouse B', location: 'Los Angeles', capacity: 3000, utilization: '72%' },
  { id: 3, code: 'WH-C', name: 'Warehouse C', location: 'Chicago', capacity: 4500, utilization: '88%' },
]

onMounted(() => {
  if (tabulatorWarehouses.value) {
    new TabulatorTables(tabulatorWarehouses.value, {
      data: warehouseData,
      autoColumns: false,
      columns: [
        { title: 'Code', field: 'code', width: 100 },
        { title: 'Name', field: 'name', width: 150 },
        { title: 'Location', field: 'location', width: 150 },
        { title: 'Capacity', field: 'capacity', width: 120, align: 'right' },
        { title: 'Utilization', field: 'utilization', width: 120 },
      ],
      layout: 'fitColumns',
    })
  }
})
</script>
