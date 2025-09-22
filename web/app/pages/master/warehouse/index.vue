<template>
    <div class="bg-gray-50 dark:bg-gray-900 min-h-screen">
        <div class="mx-auto">
            <!-- Header -->
            <div class="mb-8 flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
                <div>
                    <h1 class="text-3xl font-bold text-gray-900 dark:text-white">Warehouse Dashboard</h1>
                    <p class="text-lg text-gray-500 dark:text-gray-400">Manage your inventory with ease.</p>
                </div>
                <div class="flex items-center gap-2">
                    <UButton icon="i-heroicons-arrow-path" size="sm" color="secondary" variant="solid"
                        :loading="loading" @click="refreshData">
                        Refresh Data
                    </UButton>
                    <USelectMenu v-model="selectedStore" :options="stores" value-attribute="id"
                        option-attribute="name" />
                </div>
            </div>

            <!-- Main Content -->
            <UTabs :items="tabItems" class="w-full">
                <template #overview>
                    <Overview />
                </template>

                <template #item>
                    <Items />
                </template>

                <template #stock-level>
                    <StockLevel />
                </template>

                <template #adjustment>
                    <Adjustment />
                </template>
            </UTabs>
        </div>

        <!-- <UModal v-model="isConfirmModalOpen">
      <UCard>
        <template #header>
          <h2 class="text-lg font-semibold">Confirm Deletion</h2>
        </template>
        <p>Are you sure you want to delete this item? This action cannot be undone.</p>
        <template #footer>
          <div class="flex justify-end gap-2">
            <UButton color="gray" @click="isConfirmModalOpen = false">Cancel</UButton>
            <UButton color="red" @click="confirmDeleteItem">Delete</UButton>
          </div>
        </template>
      </UCard>
    </UModal> -->

    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { TabsItem } from '@nuxt/ui'
import Overview from '../../../components/master/warehouse/overview/overview.vue'
import Items from '~/components/master/warehouse/overview/item.vue'
import StockLevel from '~/components/master/warehouse/overview/stock-level.vue'
import Adjustment from '~/components/master/warehouse/overview/adjustment.vue'


// --- Page State ---
const loading = ref(false)
const selectedStore = ref('main-warehouse')
const stores = [{ id: 'main-warehouse', name: 'Main Warehouse' }, { id: 'retail-store-1', name: 'Retail Store #1' }]

// --- Tabs ---
const tabItems = [
    { key: 'overview', label: 'Overview', slot: 'overview' as const },
    { key: 'item-master', label: 'Item Master', slot: "item" },
    { key: 'stock-levels', label: 'Stock Levels', slot: 'stock-level' },
    { key: 'inventory-adjustments', label: 'Inventory Adjustments', slot: 'adjustment' },
] satisfies TabsItem[]

function refreshData() {
    loading.value = !loading.value;

    setTimeout(() => {
        loading.value = !loading.value;
    }, 3000);
}

</script>
