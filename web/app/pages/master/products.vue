<template>
  <div class="space-y-6">
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Product Master</h1>
        <p class="text-base-content/60 mt-1">Manage product catalog and details</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> Add Product
      </button>
    </div>

    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <input type="text" placeholder="Search products..." class="input input-bordered mb-4" />
        <div ref="tabulatorProducts" />
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

const tabulatorProducts = ref(null)

const productData = [
  { id: 1, code: 'PROD-001', name: 'Laptop', category: 'Electronics', unit: 'pcs', reorderPoint: 10 },
  { id: 2, code: 'PROD-002', name: 'Mouse', category: 'Electronics', unit: 'pcs', reorderPoint: 50 },
  { id: 3, code: 'PROD-003', name: 'Keyboard', category: 'Electronics', unit: 'pcs', reorderPoint: 30 },
]

onMounted(() => {
  if (tabulatorProducts.value) {
    new TabulatorTables(tabulatorProducts.value, {
      data: productData,
      autoColumns: false,
      columns: [
        { title: 'Product Code', field: 'code', width: 130 },
        { title: 'Name', field: 'name', width: 180 },
        { title: 'Category', field: 'category', width: 130 },
        { title: 'Unit', field: 'unit', width: 100 },
        { title: 'Reorder Point', field: 'reorderPoint', width: 130 },
      ],
      layout: 'fitColumns',
    })
  }
})
</script>
