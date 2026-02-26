<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">
        Suppliers
      </h1>
      <UButton icon="i-heroicons-plus" color="primary" label="Add Supplier" @click="isOpen = true" />
    </div>

    <UCard>
      <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filter suppliers..." icon="i-heroicons-magnifying-glass" />
      </div>

      <UTable :columns="columns" :data="suppliers" :loading="status === 'pending'">
        <template #name-cell="{ row }">
          <span class="font-medium text-gray-900 dark:text-white">{{
            row.original.name
          }}</span>
        </template>

        <template #isActive-cell="{ row }">
          <UBadge :color="row.original.isActive ? 'success' : 'error'" variant="subtle">
            {{ row.original.isActive ? "Active" : "Inactive" }}
          </UBadge>
        </template>

        <template #actions-cell="{ row }">
          <UDropdownMenu :items="items(row.original)">
            <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
          </UDropdownMenu>
        </template>
      </UTable>

      <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
        <UPagination v-model="page" :items-per-page="size" :total="total" />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Add Supplier" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4" @submit="saveSupplier">
          <UFormField label="Name" name="name">
            <UInput v-model="state.name" />
          </UFormField>
          <UFormField label="Code" name="code">
            <UInput v-model="state.code" />
          </UFormField>
          <UButton type="submit" block>Save Supplier</UButton>
        </UForm>
      </template>
    </UModal>

  </div>
</template>

<script setup lang="ts">
import type { Supplier } from '~~/types/supplier'
import type PaginationResponse from '~~/server/utils/pagination'

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);

const { data, status, refresh } = await useFetch<PaginationResponse<Supplier>>("/api/suppliers", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
})

const suppliers = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta.total || 0)

const columns = [
  { accessorKey: "name", header: "Name" },
  { accessorKey: "code", header: "Code" },
  { accessorKey: "isActive", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  name: "",
  code: "",
  isActive: true,
});

async function saveSupplier() {
  try {
    await $fetch('/api/suppliers', {
      method: 'POST',
      body: [state]
    })
    isOpen.value = false
    refresh()
  } catch (error) {
    console.error('Failed to save supplier', error)
  }
}

const items = (row: Supplier) => [
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

