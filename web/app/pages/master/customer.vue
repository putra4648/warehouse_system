<template>
    <UPage>
        <UPageHeader title="Customers" description="Manage your customers" />
        <UPageBody>
            <div class="flex items-center justify-between">
                <UButton icon="i-heroicons-plus" color="primary" label="Add Customer" @click="openAddModal" />
            </div>

            <UCard>
                <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
                    <UInput v-model="q" placeholder="Filter customers..." icon="i-heroicons-magnifying-glass" />
                </div>

                <UTable :columns="columns" :data="customers" :loading="status === 'pending'">
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

                    <template #contact_person-cell="{ row }">
                        <div v-if="row.original.contactPerson" class="flex flex-col gap-0.5">
                            <span class="text-sm font-medium">{{ row.original.contactPerson.name }}</span>
                            <span class="text-xs text-gray-500">{{ row.original.contactPerson.phone }}</span>
                        </div>
                        <span v-else class="text-xs text-gray-400 italic">No contact</span>
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

            <UModal v-model:open="isOpen" :title="state.id ? 'Edit Customer' : 'Add Customer'" scrollable>
                <template #body>
                    <UForm :state="state" class="space-y-6" @submit="saveCustomer">
                        <UTabs :items="formTabs" class="w-full">
                            <template #general>
                                <div class="space-y-4 py-4">
                                    <UFormField label="Name" name="name">
                                        <UInput v-model="state.name" class="w-full" placeholder="Enter customer name" />
                                    </UFormField>
                                    <UFormField label="Code" name="code">
                                        <UInput v-model="state.code" class="w-full" placeholder="Enter customer code" />
                                    </UFormField>
                                    <UFormField label="Active Status" name="is_active">
                                        <USwitch v-model="state.is_active" />
                                    </UFormField>
                                </div>
                            </template>

                            <template #contact>
                                <div class="space-y-4 py-4">
                                    <div class="grid grid-cols-1  gap-4">
                                        <UFormField label="Contact Name" name="contactPerson.name">
                                            <UInput v-model="state.contactPerson.name" placeholder="Name"
                                                class="w-full" />
                                        </UFormField>
                                        <UFormField label="Email" name="contactPerson.email">
                                            <UInput v-model="state.contactPerson.email" placeholder="Email"
                                                class="w-full" />
                                        </UFormField>
                                        <UFormField label="Phone" name="contactPerson.phone">
                                            <UInput v-model="state.contactPerson.phone" placeholder="Phone"
                                                class="w-full" />
                                        </UFormField>
                                        <div class="col-span-full">
                                            <UFormField label="Address" name="contactPerson.address">
                                                <UTextarea v-model="state.contactPerson.address" placeholder="Address"
                                                    class="w-full" />
                                            </UFormField>
                                        </div>
                                    </div>
                                </div>
                            </template>
                        </UTabs>

                        <UButton type="submit" block color="primary">Save Customer</UButton>
                    </UForm>
                </template>
            </UModal>
        </UPageBody>
    </UPage>
</template>

<script setup lang="ts">
import type { Customer } from '~~/types/customer'
import type PaginationResponse from '~~/server/utils/pagination'
import type { TableColumn, DropdownMenuItem } from '@nuxt/ui'

const UButton = resolveComponent('UButton')
const UBadge = resolveComponent('UBadge')

const isOpen = ref(false);
const q = ref("");
const page = ref(1);
const size = ref(10);

const { data, status, refresh } = await useFetch<PaginationResponse<Customer>>("/api/customers", {
    query: {
        page: computed(() => page.value - 1),
        size,
        search: q
    },
    watch: [page, size, q]
})

const customers = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta.total || 0)

const formTabs = [
    { label: 'General', icon: 'i-heroicons-information-circle', slot: 'general' },
    { label: 'Contact Details', icon: 'i-heroicons-user', slot: 'contact' }
]

const columns: TableColumn<Customer>[] = [
    { accessorKey: "name", header: "Name" },
    { accessorKey: "code", header: "Code" },
    { accessorKey: "contactPerson", header: "Primary Contact" },
    { accessorKey: "is_active", header: "Status" },
    { accessorKey: "actions", header: "" },
];

const state = reactive({
    id: null as number | null,
    name: "",
    code: "",
    is_active: true,
    contactPerson: {
        id: null as number | null,
        name: "",
        email: "",
        phone: "",
        address: ""
    }
});

function resetForm() {
    state.id = null;
    state.name = "";
    state.code = "";
    state.is_active = true;
    state.contactPerson = {
        id: null,
        name: "",
        email: "",
        phone: "",
        address: ""
    };
}

const openAddModal = () => {
    resetForm();
    isOpen.value = true;
};

const editCustomer = (row: Customer) => {
    state.id = row.id;
    state.name = row.name;
    state.code = row.code;
    state.is_active = row.is_active;
    state.contactPerson = row.contactPerson
        ? { ...row.contactPerson }
        : { id: null, name: "", email: "", phone: "", address: "" };
    isOpen.value = true;
};

async function saveCustomer() {
    try {
        const url = state.id ? `/api/customers/${state.id}` : '/api/customers'
        const method = 'POST' // Pattern consistently uses POST for both add and update with ID

        // For single entity save, backend usually doesn't need body: [state] unless it's a batch API
        // Category used [state] for POST. Supplier used [state] for POST and state (single) for POST with ID.
        const body = state.id ? state : [state]

        await $fetch(url, {
            method,
            body
        })
        isOpen.value = false
        resetForm()
        refresh()
    } catch (error) {
        console.error('Failed to save customer', error)
    }
}

const deleteCustomer = async (id: number) => {
    if (!confirm('Are you sure you want to delete this customer?')) return
    try {
        await $fetch(`/api/customers/${id}`, { method: 'DELETE' })
        refresh()
    } catch (error) {
        console.error('Failed to delete customer', error)
    }
}

const items = (row: Customer): DropdownMenuItem[][] => [
    [
        {
            label: "Edit",
            icon: "i-heroicons-pencil-square-20-solid",
            onSelect: () => editCustomer(row),
        },
        {
            label: "Delete",
            icon: "i-heroicons-trash-20-solid",
            onSelect: () => row.id && deleteCustomer(row.id),
        },
    ],
];
</script>
