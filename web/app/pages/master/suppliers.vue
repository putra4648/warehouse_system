<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">
        Suppliers
      </h1>
      <UButton
        icon="i-heroicons-plus"
        color="primary"
        label="Add Supplier"
        @click="isOpen = true"
      />
    </div>

    <UCard :ui="{ body: { padding: '' } }">
      <div
        class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700"
      >
        <UInput
          v-model="q"
          placeholder="Filter suppliers..."
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
          :total="suppliers.length"
        />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Add Supplier" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4">
          <UFormField label="Name" name="name">
            <UInput v-model="state.name" />
          </UFormField>
          <UFormField label="Contact Person" name="contact">
            <UInput v-model="state.contact" />
          </UFormField>
          <UFormField label="Email" name="email">
            <UInput v-model="state.email" type="email" />
          </UFormField>
          <UFormField label="Address" name="address">
            <UTextarea v-model="state.address" />
          </UFormField>
          <UButton type="submit" block>Save Supplier</UButton>
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
  { accessorKey: "contact", header: "Contact Person" },
  { accessorKey: "email", header: "Email" },
  { accessorKey: "address", header: "Address" },
  { accessorKey: "actions", header: "" },
];

const suppliers = [
  {
    id: 1,
    name: "Acme Corp",
    contact: "John Doe",
    email: "john@acme.com",
    address: "123 Acme Way",
  },
  {
    id: 2,
    name: "Global Tech",
    contact: "Jane Smith",
    email: "jane@global.com",
    address: "456 Global Blvd",
  },
  {
    id: 3,
    name: "Logistics Pro",
    contact: "Bob Johnson",
    email: "bob@logistics.com",
    address: "789 Shipping Ln",
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return suppliers.slice(
      (page.value - 1) * pageCount,
      page.value * pageCount
    );
  }

  return suppliers.filter((supplier) => {
    return Object.values(supplier).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  name: "",
  contact: "",
  email: "",
  address: "",
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
