<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">Inbound</h1>
      <UButton
        icon="i-heroicons-plus"
        color="primary"
        label="Create Inbound"
        @click="isOpen = true"
      />
    </div>

    <UCard :ui="{ body: { padding: '' } }">
      <div
        class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700"
      >
        <UInput
          v-model="q"
          placeholder="Filter inbound..."
          icon="i-heroicons-magnifying-glass"
        />
      </div>

      <UTable :columns="columns" :data="filteredRows" :loading="pending">
        <template #status-cell="{ row }">
          <UBadge
            :color="
              row.status === 'Completed'
                ? 'green'
                : row.status === 'Pending'
                ? 'orange'
                : 'gray'
            "
            variant="subtle"
          >
            {{ row.original.status }}
          </UBadge>
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
          :total="inbounds.length"
        />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Create Inbound" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4">
          <UFormField label="Supplier" name="supplier">
            <USelect
              v-model="state.supplier"
              class="w-full"
              :items="['Acme Corp', 'Global Tech', 'Logistics Pro']"
            />
          </UFormField>
          <UFormField label="Date" name="date">
            <UInput v-model="state.date" type="date" class="w-full" />
          </UFormField>
          <UFormField label="Status" name="status">
            <USelect
              v-model="state.status"
              class="w-full"
              :items="['Pending', 'Completed', 'Cancelled']"
            />
          </UFormField>
          <UButton type="submit" block>Create Inbound</UButton>
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
  { accessorKey: "id", header: "ID" },
  { accessorKey: "supplier", header: "Supplier" },
  { accessorKey: "date", header: "Date" },
  { accessorKey: "items", header: "Total Items" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const inbounds = [
  {
    id: "IN-001",
    supplier: "Acme Corp",
    date: "2024-05-01",
    items: 50,
    status: "Completed",
  },
  {
    id: "IN-002",
    supplier: "Global Tech",
    date: "2024-05-02",
    items: 20,
    status: "Pending",
  },
  {
    id: "IN-003",
    supplier: "Logistics Pro",
    date: "2024-05-03",
    items: 100,
    status: "Completed",
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return inbounds.slice((page.value - 1) * pageCount, page.value * pageCount);
  }

  return inbounds.filter((inbound) => {
    return Object.values(inbound).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  supplier: undefined,
  date: "",
  status: "Pending",
});

const items = (row) => [
  [
    {
      label: "View Details",
      icon: "i-heroicons-eye-20-solid",
      click: () => console.log("View", row.id),
    },
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      click: () => console.log("Edit", row.id),
    },
  ],
];
</script>
