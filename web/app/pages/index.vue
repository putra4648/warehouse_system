<template>
  <div>
    <h1 class="text-2xl font-bold mb-6 text-gray-900 dark:text-white">Dashboard</h1>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
      <UCard>
        <template #header>
          <div class="flex items-center gap-2">
            <UIcon name="i-heroicons-arrow-down-on-square-stack" class="w-5 h-5 text-primary-500" />
            <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400">Inbound Today</h3>
          </div>
        </template>
        <p class="text-3xl font-bold text-gray-900 dark:text-white">12</p>
      </UCard>

      <UCard>
        <template #header>
          <div class="flex items-center gap-2">
            <UIcon name="i-heroicons-arrow-up-on-square-stack" class="w-5 h-5 text-orange-500" />
            <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400">Outbound Today</h3>
          </div>
        </template>
        <p class="text-3xl font-bold text-gray-900 dark:text-white">8</p>
      </UCard>

      <UCard>
        <template #header>
          <div class="flex items-center gap-2">
            <UIcon name="i-heroicons-cube" class="w-5 h-5 text-green-500" />
            <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400">Total Products</h3>
          </div>
        </template>
        <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ totalProducts }}</p>
      </UCard>

      <UCard>
        <template #header>
          <div class="flex items-center gap-2">
            <UIcon name="i-heroicons-home-modern" class="w-5 h-5 text-blue-500" />
            <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400">Total Warehouses</h3>
          </div>
        </template>
        <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ totalWarehouses }}</p>
      </UCard>

    </div>

    <!-- Recent Activity Section -->
    <UCard>
      <template #header>
        <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">Recent Activity</h3>
      </template>

      <div class="space-y-4">
        <div v-for="(activity, index) in recentActivities" :key="index"
          class="flex items-center justify-between border-b last:border-0 border-gray-100 dark:border-gray-800 pb-3 last:pb-0">
          <div class="flex items-center gap-3">
            <UAvatar :icon="activity.type === 'inbound' ? 'i-heroicons-arrow-down' : 'i-heroicons-arrow-up'"
              :bg-color="activity.type === 'inbound' ? 'primary' : 'orange'" size="sm" />
            <div>
              <p class="text-sm font-medium text-gray-900 dark:text-white">{{ activity.title }}</p>
              <p class="text-xs text-gray-500">{{ activity.time }}</p>
            </div>
          </div>
          <span class="text-sm font-semibold"
            :class="activity.type === 'inbound' ? 'text-green-600' : 'text-orange-600'">
            {{ activity.type === 'inbound' ? '+' : '-' }}{{ activity.quantity }}
          </span>
        </div>
      </div>
    </UCard>
  </div>
</template>

<script setup lang="ts">
import type { Product } from '~~/types/product'
import type { Warehouse } from '~~/types/warehouse'
import type PaginationResponse from '~~/server/utils/pagination'

const { data: productsData } = await useFetch<PaginationResponse<Product>>("/api/products", {
  query: { page: 0, size: 1 }
})
const totalProducts = computed(() => productsData.value?.meta.total || 0)

const { data: warehousesData } = await useFetch<PaginationResponse<Warehouse>>("/api/warehouses", {
  query: { page: 0, size: 1 }
})
const totalWarehouses = computed(() => warehousesData.value?.meta.total || 0)

const recentActivities = [
  { type: 'inbound', title: 'Received Nike Air Max', time: '10 mins ago', quantity: 50 },
  { type: 'outbound', title: 'Shipped iPhone 15 Pro', time: '45 mins ago', quantity: 2 },
  { type: 'inbound', title: 'Received Samsung S24', time: '2 hours ago', quantity: 20 },
  { type: 'outbound', title: 'Shipped Sony PS5', time: '3 hours ago', quantity: 1 }
]
</script>
