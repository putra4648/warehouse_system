<template>
  <div class="space-y-6">
    <!-- Page Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-base-content">Inventory Management</h1>
        <p class="text-base-content/60 mt-1">Track and manage all warehouse inventory</p>
      </div>
      <button class="btn btn-primary">
        <span>➕</span> Add Product
      </button>
    </div>

    <!-- Filters -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
          <input v-model="searchQuery" type="text" placeholder="Search products..." class="input input-bordered" />
          <select class="select select-bordered">
            <option selected>All Categories</option>
            <option>Electronics</option>
            <option>Hardware</option>
            <option>Software</option>
          </select>
          <select class="select select-bordered">
            <option selected>All Warehouses</option>
            <option>Warehouse A</option>
            <option>Warehouse B</option>
            <option>Warehouse C</option>
          </select>
        </div>
      </div>
    </div>

    <!-- Table Container -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg mb-4">Inventory Items</h2>
        <div ref="tableContainer" class="overflow-x-auto" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Tabulator } from 'tabulator-tables'
import 'tabulator-tables/dist/css/tabulator.min.css'
import { ref, onMounted } from 'vue'

definePageMeta({
  layout: 'default',
})

const searchQuery = ref('')
const tableContainer = ref(null)
let table = null;

const tableData = [
  { id: 1, sku: 'SKU-001', name: 'Laptop', quantity: 45, warehouse: 'Warehouse A', price: 899.99, status: 'In Stock' },
  { id: 2, sku: 'SKU-002', name: 'Mouse', quantity: 120, warehouse: 'Warehouse B', price: 29.99, status: 'In Stock' },
  { id: 3, sku: 'SKU-003', name: 'Keyboard', quantity: 5, warehouse: 'Warehouse C', price: 79.99, status: 'Low Stock' },
  { id: 4, sku: 'SKU-004', name: 'Monitor', quantity: 23, warehouse: 'Warehouse A', price: 299.99, status: 'In Stock' },
  { id: 5, sku: 'SKU-005', name: 'Headphones', quantity: 0, warehouse: 'Warehouse D', price: 149.99, status: 'Out of Stock' },
]

onMounted(() => {
  if (tableContainer.value) {
    table = new Tabulator(tableContainer.value, {
      data: tableData,
      autoColumns: false,
      columns: [
        { title: 'SKU', field: 'sku', width: 120 },
        { title: 'Product Name', field: 'name', width: 150 },
        { title: 'Quantity', field: 'quantity', width: 100 },
        { title: 'Warehouse', field: 'warehouse', width: 130 },
        { title: 'Price', field: 'price', width: 100, formatter: 'money', formatterParams: { symbol: '$' } },
        {
          title: 'Status',
          field: 'status',
          width: 120,
          formatter: (cell) => {
            const value = cell.getValue()
            let badge = 'badge-success'
            if (value === 'Low Stock') badge = 'badge-warning'
            if (value === 'Out of Stock') badge = 'badge-error'
            return `<span class="badge ${badge}">${value}</span>`
          },
        },
        {
          title: 'Actions',
          width: 150,
          formatter: () => {
            return '<button class="btn btn-sm btn-ghost">Edit</button>'
          },
        },
      ],
      layout: 'fitColumns',
      responsiveLayout: 'collapse',
      pagination: true,
      paginationMode: 'local',
      paginationSize: 10,
    })
  }
})
</script>
