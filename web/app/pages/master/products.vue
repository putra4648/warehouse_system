<template>
  <UPage>
    <UPageHeader title="Products" description="Manage your products" />
    <UPageBody>
      <div class="flex items-center justify-between">
        <UButton icon="i-heroicons-plus" color="primary" label="Add Product" @click="openAddModal" />
      </div>

      <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
          <UInput v-model="q" placeholder="Filter products..." icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="products" :loading="status === 'pending'">
          <template #is_active-cell="{ row }">
            <UBadge :color="row.original.is_active ? 'success' : 'error'" variant="subtle">
              {{ row.original.is_active ? "Active" : "Inactive" }}
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

      <UModal v-model:open="isOpen" :title="state.id ? 'Edit Product' : 'Add Product'" scrollable>
        <template #body>
          <UForm :state="state" class="space-y-4" @submit="saveProduct">
            <UFormField label="Name" name="name">
              <UInput v-model="state.name" class="w-full" />
            </UFormField>
            <UFormField label="SKU" name="sku">
              <UInput v-model="state.sku" class="w-full" />
            </UFormField>
            <UFormField label="Category" name="category">
              <USelect v-model="state.category.id" value-key="id" label-key="name" :items="categories" class="w-full" />
            </UFormField>
            <UFormField label="Stock" name="quantity">
              <UInput v-model="state.quantity" type="number" class="w-full" />
            </UFormField>
            <UFormField label="Active Status" name="is_active">
              <USwitch v-model="state.is_active" />
            </UFormField>
            <UButton type="submit" block>Save Product</UButton>
          </UForm>
        </template>
      </UModal>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { Product, Category } from '~~/types/product'
import type { PaginationResponse } from '~~/server/utils/response'

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);

const { data: categoriesData } = await useFetch<PaginationResponse<Category>>("/api/categories", {
  query: {
    page: 1,
    size: 100,
    search: ''
  }
})

const categories = computed(() => (categoriesData.value?.data ?? []))

const { data, status, refresh } = await useFetch<PaginationResponse<Product>>("/api/products", {
  query: {
    page: computed(() => page.value), // Spring Data uses 0-based page
    size,
    search: q
  },
  watch: [page, size, q]
})

const products = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta.total || 0)

const columns = [
  { accessorKey: "name", header: "Product" },
  { accessorKey: "sku", header: "SKU" },
  { accessorKey: "category.name", header: "Category" },
  { accessorKey: "quantity", header: "Stock" },
  { accessorKey: "is_active", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  id: null as number | null,
  name: "",
  sku: "",
  category: {
    id: 0
  },
  quantity: 0,
  is_active: true,
});

function resetProduct() {
  state.id = null;
  state.name = "";
  state.sku = "";
  state.category = { id: (categories.value[0]?.id || 0) };
  state.quantity = 0;
}

function openAddModal() {
  resetProduct();
  isOpen.value = true;
}

function editProduct(row: Product) {
  Object.assign(state, {
    ...row,
    category: { id: row.category?.id || 0 }
  });
  isOpen.value = true;
}

async function deleteProduct(id: number) {
  if (!confirm("Are you sure you want to delete this product?")) return;
  try {
    await $fetch(`/api/products/${id}`, {
      method: 'DELETE'
    });
    refresh();
  } catch (error) {
    console.error('Failed to delete product', error);
  }
}

async function saveProduct() {
  try {
    const method = state.id ? 'PUT' : 'POST';
    const body = state;
    const url = state.id ? `/api/products/${state.id}` : '/api/products';
    await $fetch(url, {
      method,
      body
    })
    isOpen.value = false
    refresh()
  } catch (error) {
    console.error('Failed to save product', error)
  }
}

const items = (row: Product) => [
  [
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      onSelect: () => editProduct(row),
    },
    {
      label: "Delete",
      icon: "i-heroicons-trash-20-solid",
      variant: "neutral" as const,
      color: "error" as const,
      onSelect: () => deleteProduct(row.id),
    },
  ],
];
</script>
