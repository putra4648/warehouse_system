<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">
        Warehouses
      </h1>
      <UButton
        icon="i-heroicons-plus"
        color="primary"
        label="Add Warehouse"
        @click="isOpen = true"
      />
    </div>

    <UCard :ui="{ body: { padding: '' } }">
      <div
        class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700"
      >
        <UInput
          v-model="q"
          placeholder="Filter warehouses..."
          icon="i-heroicons-magnifying-glass"
        />
      </div>

      <UTable :columns="columns" :data="filteredRows" :loading="pending">
        <template #name-cell="{ row }">
          <span class="font-medium text-gray-900 dark:text-white">{{
            row.original.name
          }}</span>
        </template>

        <template #actions-cell="{ row }">
          <UDropdownMenu :items="items(row.original)">
            <UButton
              color="gray"
              variant="ghost"
              icon="i-heroicons-ellipsis-horizontal-20-solid"
            />
          </UDropdownMenu>
        </template>
      </UTable>

      <div
        class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700"
      >
        <UPagination
          v-model="page"
          :page-count="pageCount"
          :total="warehouses.length"
        />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Add Warehouse" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4">
          <UFormField label="Name" name="name">
            <UInput v-model="state.name" />
          </UFormField>
          <UFormField label="Type" name="type">
            <USelect
              v-model="state.type"
              :items="[
                'Distribution Center',
                'Fulfillment Center',
                'Cold Storage',
              ]"
            />
          </UFormField>
          <UFormField label="Location" name="location">
            <UInput v-model="state.location" />
          </UFormField>
          <UFormField label="Capacity (sq ft)" name="capacity">
            <UInput v-model="state.capacity" type="number" />
          </UFormField>
          <UButton type="submit" block>Save Warehouse</UButton>
        </UForm>
      </template>
    </UModal>
  </div>
</template>

<script setup>
const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const pageCount = 5;
const pending = ref(false);

const columns = [
  { accessorKey: "name", header: "Name" },
  { accessorKey: "type", header: "Type" },
  { accessorKey: "location", header: "Location" },
  { accessorKey: "capacity", header: "Capacity" },
  { accessorKey: "actions", header: "" },
];

const warehouses = [
  {
    id: 1,
    name: "North DC",
    type: "Distribution Center",
    location: "New York, NY",
    capacity: 50000,
  },
  {
    id: 2,
    name: "South FC",
    type: "Fulfillment Center",
    location: "Atlanta, GA",
    capacity: 75000,
  },
  {
    id: 3,
    name: "West Cold Chain",
    type: "Cold Storage",
    location: "Los Angeles, CA",
    capacity: 20000,
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return warehouses.slice(
      (page.value - 1) * pageCount,
      page.value * pageCount
    );
  }

  return warehouses.filter((warehouse) => {
    return Object.values(warehouse).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  name: "",
  type: undefined,
  location: "",
  capacity: 0,
});

const items = (row) => [
  [
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      click: () => console.log("Edit", row.id),
    },
    {
      label: "Delete",
      icon: "i-heroicons-trash-20-solid",
      click: () => console.log("Delete", row.id),
    },
  ],
];
</script>
