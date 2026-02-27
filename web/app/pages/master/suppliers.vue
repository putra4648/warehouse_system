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

        <template #is_active-cell="{ row }">
          <UBadge :color="row.original.is_active ? 'success' : 'error'" variant="subtle">
            {{ row.original.is_active ? "Active" : "Inactive" }}
          </UBadge>
        </template>

        <template #contact_person_suppliers-cell="{ row }">
          <div class="flex flex-col gap-1">
            <span class="text-xs text-gray-500">
              <UIcon name="i-heroicons-users" class="inline-block mr-1" />{{
                row.original.contact_person_suppliers?.length || 0 }} contacts
            </span>
            <span class="text-xs text-gray-500">
              <UIcon name="i-heroicons-shopping-bag" class="inline-block mr-1" />
              {{ row.original.products?.length || 0 }} products
            </span>
          </div>
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

    <UModal v-model:open="isOpen" :title="state.id ? 'Edit Supplier' : 'Add Supplier'" scrollable>
      <template #body>
        <UForm :state="state" class="space-y-6" @submit="saveSupplier">
          <UTabs :items="formTabs" class="w-full">
            <template #general>
              <div class="space-y-4 py-4">
                <UFormField label="Name" name="name">
                  <UInput v-model="state.name" class="w-full" placeholder="Enter supplier name" />
                </UFormField>
                <UFormField label="Code" name="code">
                  <UInput v-model="state.code" class="w-full" placeholder="Enter supplier code" />
                </UFormField>
                <UFormField label="Active Status" name="is_active">
                  <USwitch v-model="state.is_active" />
                </UFormField>
              </div>
            </template>

            <template #contacts>
              <div class="space-y-4 py-4">
                <div class="flex items-center justify-between">
                  <h3 class="text-sm font-medium">Manage Contact Persons</h3>
                  <UButton icon="i-heroicons-plus" color="neutral" variant="outline" size="xs" label="Add Contact"
                    @click="addContactPerson" />
                </div>

                <div class="space-y-4 max-h-80 overflow-y-auto pr-2">
                  <div v-for="(contact, index) in state.contact_person_suppliers" :key="index"
                    class="p-4 border border-gray-200 dark:border-gray-700 rounded-lg relative">
                    <UButton icon="i-heroicons-trash" color="error" variant="ghost" size="xs"
                      class="absolute top-2 right-2" @click="removeContactPerson(index)" />

                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                      <UFormField label="Name" :name="`contact-${index}-name`">
                        <UInput v-model="contact.name" placeholder="Name" />
                      </UFormField>
                      <UFormField label="Email" :name="`contact-${index}-email`">
                        <UInput v-model="contact.email" placeholder="Email" />
                      </UFormField>
                      <UFormField label="Phone" :name="`contact-${index}-phone`">
                        <UInput v-model="contact.phone" placeholder="Phone" />
                      </UFormField>
                      <UFormField label="Address" :name="`contact-${index}-address`">
                        <UInput v-model="contact.address" placeholder="Address" />
                      </UFormField>
                    </div>
                  </div>
                  <div v-if="state.contact_person_suppliers.length === 0"
                    class="text-center py-8 text-gray-500 text-sm italic border-2 border-dashed border-gray-200 dark:border-gray-800 rounded-lg">
                    No contact persons added yet.
                  </div>
                </div>
              </div>
            </template>

            <template #products>
              <div class="space-y-4 py-4">
                <div class="flex items-center justify-between">
                  <h3 class="text-sm font-medium">Supplied Products</h3>
                  <div class="flex gap-2">
                    <USelectMenu v-model="selectedProduct" value-key="id" label-key="name" :items="availableProducts"
                      placeholder="Select product to add..." class="w-64" />
                    <UButton :disabled="!selectedProduct" icon="i-heroicons-plus" color="neutral" variant="solid"
                      size="xs" label="Add" @click="addProduct" />
                  </div>
                </div>

                <div class="space-y-2 max-h-80 overflow-y-auto pr-2">
                  <div v-for="(ps, index) in state.products" :key="index"
                    class="flex items-center justify-between p-3 border border-gray-200 dark:border-gray-700 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-800 transition-colors">
                    <div class="flex items-center gap-3">
                      <div
                        class="w-10 h-10 rounded bg-primary-100 dark:bg-primary-900 flex items-center justify-center text-primary-600 dark:text-primary-400 font-bold text-xs">
                        {{ ps.product?.name.substring(0, 2).toUpperCase() }}
                      </div>
                      <div>
                        <div class="font-medium text-sm">{{ ps.product?.name }}</div>
                        <div class="text-xs text-gray-500">ID: {{ ps.product?.id }}</div>
                      </div>
                    </div>
                    <UButton icon="i-heroicons-trash" color="error" variant="ghost" size="xs"
                      @click="removeProduct(index)" />
                  </div>
                  <div v-if="state.products.length === 0"
                    class="text-center py-8 text-gray-500 text-sm italic border-2 border-dashed border-gray-200 dark:border-gray-800 rounded-lg">
                    No products assigned to this supplier.
                  </div>
                </div>
              </div>
            </template>
          </UTabs>

          <UButton type="submit" block color="primary">Save Supplier</UButton>
        </UForm>
      </template>
    </UModal>

  </div>
</template>

<script setup lang="ts">
import type { Supplier, ContactPersonSupplier, ProductSupplier } from '~~/types/supplier'
import type { Product } from '~~/types/product'
import type PaginationResponse from '~~/server/utils/pagination'
import type { TableColumn, DropdownMenuItem } from '@nuxt/ui'

const UButton = resolveComponent('UButton')
const UBadge = resolveComponent('UBadge')

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);
const selectedProduct = ref<number>();

const { data, status, refresh } = await useFetch<PaginationResponse<Supplier>>("/api/suppliers", {
  query: {
    page: computed(() => page.value - 1),
    size,
    search: q
  },
  watch: [page, size, q]
})

const { data: productsData } = await useFetch<PaginationResponse<Product>>("/api/products", {
  query: { size: 100, search: q.value }
})

const suppliers = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta.total || 0)
const availableProducts = computed(() => productsData.value?.data ?? [])

const formTabs = [
  { label: 'General', icon: 'i-heroicons-information-circle', slot: 'general' },
  { label: 'Contacts', icon: 'i-heroicons-users', slot: 'contacts' },
  { label: 'Products', icon: 'i-heroicons-shopping-bag', slot: 'products' }
]

const columns: TableColumn<Supplier>[] = [
  { accessorKey: "name", header: "Name" },
  { accessorKey: "code", header: "Code" },
  {
    accessorKey: "contact_person_suppliers", header: "Relations"
  },
  { accessorKey: "is_active", header: "Status" },
  { accessorKey: "actions", header: "" },
];

const state = reactive({
  id: null as number | null,
  name: "",
  code: "",
  is_active: true,
  contact_person_suppliers: [] as ContactPersonSupplier[],
  products: [] as ProductSupplier[]
});

function addContactPerson() {
  state.contact_person_suppliers.push({
    id: null,
    name: "",
    email: "",
    phone: "",
    address: ""
  } as ContactPersonSupplier);
}

function removeContactPerson(index: number) {
  state.contact_person_suppliers.splice(index, 1);
}

function addProduct() {
  if (!selectedProduct.value) return;

  const productId = selectedProduct.value;

  // Check if already exists
  if (state.products.find(p => p.id.product_id === productId)) {
    return;
  }

  state.products.push({
    id: {
      product_id: productId,
      supplier_id: state.id
    },
    product: productsData.value?.data.find(p => p.id === productId)
  } as ProductSupplier);

  selectedProduct.value = undefined;
}

function removeProduct(index: number) {
  state.products.splice(index, 1);
}

function resetForm() {
  state.id = null;
  state.name = "";
  state.code = "";
  state.is_active = true;
  state.contact_person_suppliers = [];
  state.products = [];
  selectedProduct.value = undefined;
}

const editSupplier = (row: Supplier) => {
  state.id = row.id;
  state.name = row.name;
  state.code = row.code;
  state.is_active = row.is_active;
  state.contact_person_suppliers = row.contact_person_suppliers ? [...row.contact_person_suppliers] : [];
  state.products = row.products ? [...row.products] : [];
  isOpen.value = true;
};

async function saveSupplier() {
  try {
    const url = state.id ? `/api/suppliers` : '/api/suppliers'

    // For single entity save, backend usually doesn't need body: [state] unless it's a batch API
    // Looking at previous code, it used body: [state] for POST.
    const body = state.id ? state : [state]

    await $fetch(url, {
      method: 'POST',
      body
    })
    isOpen.value = false
    resetForm()
    refresh()
  } catch (error) {
    console.error('Failed to save supplier', error)
  }
}

const deleteSupplier = async (id: number) => {
  if (!confirm('Are you sure you want to delete this supplier?')) return
  try {
    await $fetch(`/api/suppliers/${id}`, { method: 'DELETE' })
    refresh()
  } catch (error) {
    console.error('Failed to delete supplier', error)
  }
}

const items = (row: Supplier): DropdownMenuItem[][] => [
  [
    {
      label: "Edit",
      icon: "i-heroicons-pencil-square-20-solid",
      onSelect: () => editSupplier(row),
    },
    {
      label: "Delete",
      icon: "i-heroicons-trash-20-solid",
      onSelect: () => row.id && deleteSupplier(row.id),
    },
  ],
];
</script>
