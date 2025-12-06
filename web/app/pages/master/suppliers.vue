<template>
  <div class="space-y-6">
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Supplier Master</h1>
        <p class="text-base-content/60 mt-1">Manage supplier information and contacts</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> Add Supplier
      </button>
    </div>

    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <input type="text" placeholder="Search suppliers..." class="input input-bordered mb-4" />
        <div ref="tabulatorSuppliers" />
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

const tabulatorSuppliers = ref(null)

const supplierData = [
  { id: 1, name: 'Tech Suppliers Inc', contact: 'john@techsuppliers.com', phone: '+1-555-0101', city: 'New York' },
  { id: 2, name: 'Global Parts Ltd', contact: 'sales@globalparts.com', phone: '+1-555-0102', city: 'Los Angeles' },
  { id: 3, name: 'Electronics Plus', contact: 'info@elecplus.com', phone: '+1-555-0103', city: 'Chicago' },
]

onMounted(() => {
  if (tabulatorSuppliers.value) {
    new TabulatorTables(tabulatorSuppliers.value, {
      data: supplierData,
      autoColumns: false,
      columns: [
        { title: 'Supplier Name', field: 'name', width: 180 },
        { title: 'Contact', field: 'contact', width: 200 },
        { title: 'Phone', field: 'phone', width: 150 },
        { title: 'City', field: 'city', width: 150 },
      ],
      layout: 'fitColumns',
    })
  }
})
</script>
