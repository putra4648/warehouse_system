<template>
    <div>
        <div class="flex items-center justify-between mb-6">
            <h1 class="text-2xl font-bold text-gray-900 dark:text-white">Product Categories</h1>
            <UButton icon="i-heroicons-plus" color="primary" label="Add Category" @click="openAddModal" />
        </div>

        <UCard>
            <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
                <UInput v-model="q" placeholder="Filter categories..." icon="i-heroicons-magnifying-glass"
                    class="max-w-xs" />
            </div>

            <UTable :columns="columns" :data="categories" :loading="status === 'pending'">
                <template #actions-cell="{ row }">
                    <UDropdownMenu :items="actions(row.original as any)">
                        <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
                    </UDropdownMenu>
                </template>
            </UTable>

            <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
                <UPagination v-model="page" :items-per-page="size" :total="total" />
            </div>
        </UCard>

        <UModal v-model:open="isOpen" :title="isEditing ? 'Edit Category' : 'Add Category'" scrollable>
            <template #body>
                <UForm :state="state" class="space-y-4" @submit="saveCategory">
                    <UFormField label="Name" name="name">
                        <UInput v-model="state.name" placeholder="Category name" class="w-full" />
                    </UFormField>
                    <UFormField label="Description" name="description">
                        <UTextarea v-model="state.description" placeholder="Short description" class="w-full" />
                    </UFormField>
                    <div class="flex justify-end gap-2 pt-4">
                        <UButton color="neutral" variant="ghost" label="Cancel" @click="isOpen = false" />
                        <UButton type="submit" color="primary"
                            :label="isEditing ? 'Update Category' : 'Save Category'" />
                    </div>
                </UForm>
            </template>
        </UModal>
    </div>
</template>

<script setup lang="ts">
import type { Category } from '~~/types/product'
import type PaginationResponse from '~~/server/utils/pagination'
import type { DropdownMenuItem } from '@nuxt/ui';

definePageMeta({
    layout: "default",
});

const q = ref("");
const page = ref(1);
const size = ref(10);
const isOpen = ref(false);
const isEditing = ref(false);

const state = reactive({
    id: undefined as number | undefined,
    name: "",
    description: "",
});

const { data, status, refresh } = await useFetch<PaginationResponse<Category>>("/api/categories", {
    query: {
        page: computed(() => page.value - 1),
        size,
        search: q
    },
    watch: [page, size, q]
})

const categories = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta?.total || 0)

const columns = [
    { accessorKey: "name", header: "Category Name" },
    { accessorKey: "description", header: "Description" },
    { accessorKey: "actions", header: "" },
];

function openAddModal() {
    isEditing.value = false;
    state.id = undefined;
    state.name = "";
    state.description = "";
    isOpen.value = true;
}

function openEditModal(category: Category) {
    isEditing.value = true;
    state.id = category.id;
    state.name = category.name;
    state.description = category.description;
    isOpen.value = true;
}

async function saveCategory() {
    try {
        const method = isEditing.value ? 'PUT' : 'POST'
        const url = isEditing.value ? `/api/categories/${state.id}` : '/api/categories'
        await $fetch(url, {
            method,
            body: state
        })
        isOpen.value = false
        refresh()
    } catch (error) {
        console.error('Failed to save category', error)
    }
}

async function deleteCategory(id: number) {
    if (!confirm("Are you sure you want to delete this category?")) return;
    try {
        await $fetch(`/api/categories/${id}`, {
            method: 'DELETE'
        })
        refresh()
    } catch (error) {
        console.error('Failed to delete category', error)
    }
}

const actions = (row: Category): DropdownMenuItem[][] => [
    [
        {
            label: "Edit",
            icon: "i-heroicons-pencil-square-20-solid",
            onSelect: () => openEditModal(row),
        },
        {
            label: "Delete",
            icon: "i-heroicons-trash-20-solid",
            onSelect: () => deleteCategory(row.id),
            color: 'error' as const
        },
    ],
];
</script>
