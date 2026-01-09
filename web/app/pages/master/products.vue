<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold text-gray-900 dark:text-white">Products</h1>
      <UButton icon="i-heroicons-plus" color="primary" label="Add Product" @click="isOpen = true" />
    </div>

    <UCard :ui="{ body: { padding: '' } }">
      <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filter products..." icon="i-heroicons-magnifying-glass" />
      </div>

      <UTable :columns="columns" :data="filteredRows" :loading="pending">
        <template #name-cell="{ row }">
          <div class="flex items-center gap-3">
            <UAvatar :src="row.original.image" :alt="row.original.name" size="sm" />
            <span class="font-medium text-gray-900 dark:text-white">{{
              row.original.name
              }}</span>
          </div>
        </template>

        <template #price-cell="{ row }">
          {{ formatCurrency(row.original.price) }}
        </template>

        <template #status-cell="{ row }">
          <UBadge :color="row.original.stock > 10
            ? 'green'
            : row.original.stock > 0
              ? 'orange'
              : 'red'
            " variant="subtle">
            {{
              row.original.stock > 10
                ? "In Stock"
                : row.original.stock > 0
                  ? "Low Stock"
                  : "Out of Stock"
            }}
          </UBadge>
        </template>

        <template #actions-cell="{ row }">
          <UDropdownMenu :items="items(row.original)">
            <UButton color="gray" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
          </UDropdownMenu>
        </template>
      </UTable>

      <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
        <UPagination v-model="page" :page-count="pageCount" :total="products.length" />
      </div>
    </UCard>

    <UModal v-model:open="isOpen" title="Add Product" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-4">
          <UFormField label="Name" name="name">
            <UInput v-model="state.name" class="w-full" />
          </UFormField>
          <UFormField label="SKU" name="sku">
            <UInput v-model="state.sku" class="w-full" />
          </UFormField>
          <UFormField label="Category" name="category">
            <USelect v-model="state.category" :items="['Electronics', 'Clothing', 'Furniture']" class="w-full" />
          </UFormField>
          <UFormField label="Price" name="price">
            <UInput v-model="state.price" type="number" class="w-full" />
          </UFormField>
          <UFormField label="Stock" name="stock">
            <UInput v-model="state.stock" type="number" class="w-full" />
          </UFormField>
          <UButton type="submit" block>Save Product</UButton>
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
  { accessorKey: "name", header: "Product" },
  { accessorKey: "sku", header: "SKU" },
  { accessorKey: "category", header: "Category" },
  { accessorKey: "price", header: "Price" },
  { accessorKey: "stock", header: "Stock" },
  { accessorKey: "status", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const products = [
  {
    id: 1,
    name: "Nike Air Max",
    sku: "NIK-001",
    category: "Clothing",
    price: 120,
    stock: 50,
    image: "https://avatars.githubusercontent.com/u/739984?v=4",
  },
  {
    id: 2,
    name: "iPhone 15 Pro",
    sku: "APP-001",
    category: "Electronics",
    price: 999,
    stock: 5,
    image: "https://avatars.githubusercontent.com/u/739984?v=4",
  },
  {
    id: 3,
    name: "Sony PS5",
    sku: "SON-001",
    category: "Electronics",
    price: 499,
    stock: 0,
    image: "https://avatars.githubusercontent.com/u/739984?v=4",
  },
  {
    id: 4,
    name: "Herman Miller Chair",
    sku: "HER-001",
    category: "Furniture",
    price: 1200,
    stock: 12,
    image: "https://avatars.githubusercontent.com/u/739984?v=4",
  },
  {
    id: 5,
    name: "MacBook Pro M3",
    sku: "APP-002",
    category: "Electronics",
    price: 1599,
    stock: 20,
    image: "https://avatars.githubusercontent.com/u/739984?v=4",
  },
];

const filteredRows = computed(() => {
  if (!q.value) {
    return products.slice((page.value - 1) * pageCount, page.value * pageCount);
  }

  return products.filter((product) => {
    return Object.values(product).some((value) => {
      return String(value).toLowerCase().includes(q.value.toLowerCase());
    });
  });
});

const state = reactive({
  name: "",
  sku: "",
  category: undefined,
  price: 0,
  stock: 0,
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

const formatCurrency = (value) => {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
  }).format(value);
};
</script>
