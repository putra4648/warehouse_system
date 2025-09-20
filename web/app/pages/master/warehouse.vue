<template>
  <div class="p-4 sm:p-6 lg:p-8 bg-gray-50 dark:bg-gray-900 min-h-screen">
    <div class="mx-auto">
      <!-- Header -->
      <div class="mb-8 flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white">Warehouse Dashboard</h1>
          <p class="text-lg text-gray-500 dark:text-gray-400">Manage your inventory with ease.</p>
        </div>
        <div class="flex items-center gap-2">
            <UButton
                icon="i-heroicons-arrow-path"
                size="sm"
                color="gray"
                variant="solid"
                :loading="loading"
                @click="refreshData"
            >
                Refresh Data
            </UButton>
             <USelect v-model="selectedStore" :options="stores" option-attribute="name" />
        </div>
      </div>

      <!-- Main Content -->
      <UTabs :items="tabItems" class="w-full">
        <template #item="{ item }">
          <UCard class="mt-4" :ui="{ body: { padding: 'p-4 sm:p-6' } }">
            <!-- Item Master Tab -->
            <div v-if="item.key === 'item-master'">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-xl font-semibold text-gray-800 dark:text-gray-100">Item Master</h2>
                <UButton icon="i-heroicons-plus-circle" @click="isItemModalOpen = true">Add New Item</UButton>
              </div>
              <UTable :columns="itemMasterColumns" :rows="items" :loading="loading">
                 <template #actions-data="{ row }">
                    <UDropdown :items="itemActions(row)">
                      <UButton color="gray" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
                    </UDropdown>
                  </template>
              </UTable>
            </div>

            <!-- Stock Levels Tab -->
            <div v-if="item.key === 'stock-levels'">
               <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 mb-4">
                <h2 class="text-xl font-semibold text-gray-800 dark:text-gray-100">Stock Levels</h2>
                 <UInput icon="i-heroicons-magnifying-glass-20-solid" placeholder="Search items..." v-model="stockSearchQuery"/>
              </div>
              <UTable :columns="stockLevelsColumns" :rows="filteredStockLevels" :loading="loading">
                <template #status-data="{ row }">
                  <UBadge :label="row.status" :color="row.status === 'In Stock' ? 'green' : (row.status === 'Low Stock' ? 'orange' : 'red')" variant="subtle" />
                </template>
              </UTable>
            </div>

            <!-- Inventory Adjustments Tab -->
            <div v-if="item.key === 'inventory-adjustments'">
               <h2 class="text-xl font-semibold text-gray-800 dark:text-gray-100 mb-4">Inventory Adjustments</h2>
               <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
                 <!-- Adjustment Form -->
                 <div class="lg:col-span-1">
                   <UCard>
                     <template #header>
                        <h3 class="font-semibold">Make an Adjustment</h3>
                     </template>
                      <UForm :state="adjustmentState" @submit="submitAdjustment" class="space-y-4">
                         <UFormGroup label="Item" name="itemId">
                            <USelectMenu v-model="adjustmentState.item" :options="items" value-attribute="id" option-attribute="name" placeholder="Select an item" />
                         </UFormGroup>
                         <UFormGroup label="Adjustment Type" name="type">
                            <USelect v-model="adjustmentState.type" :options="['Receive', 'Return', 'Damaged', 'Correction']" placeholder="Select type" />
                         </UFormGroup>
                         <UFormGroup label="Quantity" name="quantity">
                            <UInput type="number" v-model.number="adjustmentState.quantity" />
                         </UFormGroup>
                         <UFormGroup label="Reason / Note" name="reason">
                            <UTextarea v-model="adjustmentState.reason" placeholder="e.g., Stock count correction" />
                         </UFormGroup>
                         <UButton type="submit" block>Submit Adjustment</UButton>
                      </UForm>
                   </UCard>
                 </div>
                 <!-- Adjustment History -->
                 <div class="lg:col-span-2">
                    <UCard>
                      <template #header>
                        <h3 class="font-semibold">Adjustment History</h3>
                      </template>
                      <UTable :columns="adjustmentHistoryColumns" :rows="adjustmentHistory" :loading="loading" />
                    </UCard>
                 </div>
               </div>
            </div>
          </UCard>
        </template>
      </UTabs>
    </div>

    <!-- Add/Edit Item Modal -->
    <UModal v-model="isItemModalOpen">
      <UCard>
        <template #header>
          <h2 class="text-lg font-semibold">{{ isEditing ? 'Edit Item' : 'Add New Item' }}</h2>
        </template>

        <UForm :state="itemFormState" class="space-y-4">
            <UFormGroup label="Item Name" name="name">
              <UInput v-model="itemFormState.name" />
            </UFormGroup>
            <UFormGroup label="SKU" name="sku">
              <UInput v-model="itemFormState.sku" />
            </UFormGroup>
            <UFormGroup label="Category" name="category">
               <UInput v-model="itemFormState.category" />
            </UFormGroup>
             <UFormGroup label="Supplier" name="supplier">
               <UInput v-model="itemFormState.supplier" />
            </UFormGroup>
        </UForm>

        <template #footer>
          <div class="flex justify-end gap-2">
            <UButton color="gray" @click="isItemModalOpen = false">Cancel</UButton>
            <UButton @click="saveItem">{{ isEditing ? 'Save Changes' : 'Add Item' }}</UButton>
          </div>
        </template>
      </UCard>
    </UModal>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'

// --- Page State ---
const loading = ref(false)
const selectedStore = ref('main-warehouse')
const stores = [
  { id: 'main-warehouse', name: 'Main Warehouse' },
  { id: 'retail-store-1', name: 'Retail Store #1' },
]

// --- Tabs ---
const tabItems = [
  { key: 'item-master', label: 'Item Master' },
  { key: 'stock-levels', label: 'Stock Levels' },
  { key: 'inventory-adjustments', label: 'Inventory Adjustments' },
]

// --- Item Master State ---
const isItemModalOpen = ref(false)
const isEditing = ref(false)
const itemFormState = reactive({ id: null, name: '', sku: '', category: '', supplier: '' })
const items = ref([
  { id: 1, name: 'Wireless Mouse', sku: 'LOG-M510', category: 'Electronics', supplier: 'Logitech' },
  { id: 2, name: 'Mechanical Keyboard', sku: 'COR-K70', category: 'Electronics', supplier: 'Corsair' },
  { id: 3, name: '27" 4K Monitor', sku: 'DELL-U2721Q', category: 'Monitors', supplier: 'Dell' },
  { id: 4, name: 'USB-C Hub', sku: 'ANK-A8334', category: 'Accessories', supplier: 'Anker' },
])
const itemMasterColumns = [
  { key: 'id', label: 'ID', sortable: true },
  { key: 'name', label: 'Name', sortable: true },
  { key: 'sku', label: 'SKU' },
  { key: 'category', label: 'Category', sortable: true },
  { key: 'supplier', label: 'Supplier', sortable: true },
  { key: 'actions', label: 'Actions' }
]

const itemActions = (row) => [
  [{
    label: 'Edit',
    icon: 'i-heroicons-pencil-square-20-solid',
    click: () => editItem(row)
  }],
  [{
    label: 'Delete',
    icon: 'i-heroicons-trash-20-solid',
    click: () => deleteItem(row.id)
  }]
]

function editItem(item) {
  isEditing.value = true
  Object.assign(itemFormState, item)
  isItemModalOpen.value = true
}

function deleteItem(itemId) {
    if(confirm('Are you sure you want to delete this item?')) {
        items.value = items.value.filter(item => item.id !== itemId)
    }
}

function saveItem() {
  if (isEditing.value) {
    const index = items.value.findIndex(item => item.id === itemFormState.id)
    if (index !== -1) {
      items.value[index] = { ...itemFormState }
    }
  } else {
    const newItem = { ...itemFormState, id: Math.max(0, ...items.value.map(i => i.id)) + 1 }
    items.value.push(newItem)
  }
  resetForm()
  isItemModalOpen.value = false
}

function resetForm() {
    isEditing.value = false
    itemFormState.id = null
    itemFormState.name = ''
    itemFormState.sku = ''
    itemFormState.category = ''
    itemFormState.supplier = ''
}

// --- Stock Levels State ---
const stockSearchQuery = ref('')
const stockLevels = ref([
  { id: 1, name: 'Wireless Mouse', sku: 'LOG-M510', location: 'Aisle 1, Shelf B', quantity: 150, reorderPoint: 50, status: 'In Stock' },
  { id: 2, name: 'Mechanical Keyboard', sku: 'COR-K70', location: 'Aisle 1, Shelf C', quantity: 45, reorderPoint: 50, status: 'Low Stock' },
  { id: 3, name: '27" 4K Monitor', sku: 'DELL-U2721Q', location: 'Aisle 3, Shelf A', quantity: 0, reorderPoint: 20, status: 'Out of Stock' },
  { id: 4, name: 'USB-C Hub', sku: 'ANK-A8334', location: 'Aisle 2, Shelf D', quantity: 200, reorderPoint: 75, status: 'In Stock' },
])
const stockLevelsColumns = [
  { key: 'name', label: 'Item Name', sortable: true },
  { key: 'sku', label: 'SKU' },
  { key: 'location', label: 'Location' },
  { key: 'quantity', label: 'Quantity', sortable: true },
  { key: 'reorderPoint', label: 'Reorder Point' },
  { key: 'status', label: 'Status', sortable: true },
]
const filteredStockLevels = computed(() => {
  if (!stockSearchQuery.value) {
    return stockLevels.value
  }
  return stockLevels.value.filter(item => {
    return Object.values(item).some(value => {
      return String(value).toLowerCase().includes(stockSearchQuery.value.toLowerCase())
    })
  })
})

// --- Inventory Adjustments State ---
const adjustmentState = reactive({ item: null, type: '', quantity: null, reason: '' })
const adjustmentHistory = ref([
  { id: 1, date: '2023-10-26', itemName: 'Wireless Mouse', type: 'Receive', quantity: 200, reason: 'New stock arrival' },
  { id: 2, date: '2023-10-25', itemName: '27" 4K Monitor', type: 'Damaged', quantity: -2, reason: 'Forklift accident' },
  { id: 3, date: '2023-10-24', itemName: 'Mechanical Keyboard', type: 'Return', quantity: -5, reason: 'Customer return' },
])
const adjustmentHistoryColumns = [
    { key: 'date', label: 'Date', sortable: true },
    { key: 'itemName', label: 'Item' },
    { key: 'type', label: 'Type' },
    { key: 'quantity', label: 'Quantity' },
    { key: 'reason', label: 'Reason' },
]

function submitAdjustment() {
    if (!adjustmentState.item || !adjustmentState.type || !adjustmentState.quantity) {
        alert('Please fill all required fields.');
        return;
    }
    const selectedItem = items.value.find(i => i.id === adjustmentState.item);

    const newAdjustment = {
        id: adjustmentHistory.value.length + 1,
        date: new Date().toISOString().split('T')[0],
        itemName: selectedItem.name,
        type: adjustmentState.type,
        quantity: ['Damaged', 'Return'].includes(adjustmentState.type) ? -Math.abs(adjustmentState.quantity) : Math.abs(adjustmentState.quantity),
        reason: adjustmentState.reason
    }
    adjustmentHistory.value.unshift(newAdjustment)

    // Reset form
    adjustmentState.item = null
    adjustmentState.type = ''
    adjustmentState.quantity = null
    adjustmentState.reason = ''
}


// --- General Functions ---
function refreshData() {
  loading.value = true
  setTimeout(() => {
    // In a real app, you would fetch data from an API here
    loading.value = false
  }, 1000)
}
</script>
