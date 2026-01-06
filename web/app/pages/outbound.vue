<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">Outbound</h1>
      <UButton
        icon="i-heroicons-plus"
        color="primary"
        label="Create Outbound"
        @click="isOpen = true"
      />
    </div>

    <UCard :ui="{ body: { padding: '' } }">
      <div
        class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700"
      >
        <UInput
          v-model="q"
          placeholder="Filter outbound..."
          icon="i-heroicons-magnifying-glass"
        />
      </div>

      <UTable :columns="columns" :data="filteredRows" :loading="pending">
        <template #status-cell="{ row }">
          <UBadge
            :color="
              row.status === 'Completed'
                ? 'green'
                : row.status === 'Processing'
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
          :total="outbounds.length"
        />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Create Outbound" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4">
          <UFormField label="Customer" name="customer">
            <UInput v-model="state.customer" />
          </UFormField>
          <UFormField label="Date" name="date">
            <UInput v-model="state.date" type="date" />
          </UFormField>
          <UFormField label="Destination" name="destination">
            <UInput v-model="state.destination" />
          </UFormField>
          <UFormField label="Status" name="status">
            <USelect
              v-model="state.status"
              :items="['Processing', 'Completed', 'Cancelled']"
            />
          </UFormField>
          <UButton type="submit" block>Create Outbound</UButton>
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
  { accessorKey: "customer", header: "Customer" },
  { accessorKey: "destination", header: "Destination" },
  { accessorKey: "date", header: "Date" },
  { accessorKey: "items", header: "Total Items" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const outbounds = [
  {
    id: "OUT-001",
    customer: "John Doe",
    destination: "New York, NY",
    date: "2024-05-01",
    items: 2,
    status: "Completed",
  },
  {
    id: "OUT-002",
    customer: "Jane Smith",
    destination: "Chicago, IL",
    date: "2024-05-02",
    items: 5,
    status: "Processing",
  },
  {
    id: "OUT-003",
    customer: "Bob Johnson",
    destination: "Miami, FL",
    date: "2024-05-03",
    items: 1,
    status: "Completed",
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return outbounds.slice(
      (page.value - 1) * pageCount,
      page.value * pageCount
    );
  }

  return outbounds.filter((outbound) => {
    return Object.values(outbound).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  customer: "",
  destination: "",
  date: "",
  status: "Processing",
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
