<template>
    <UCard>
        <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
            <UInput v-model="search" :placeholder="`Filter ${title.toLowerCase()}...`"
                icon="i-heroicons-magnifying-glass" />
        </div>

        <UTable :columns="columns" :data="items" :loading="status === 'pending'">
            <template #is_active-cell="{ row: { original: { is_active } } }">
                <UBadge :color="is_active ? 'success' : 'error'" variant="subtle">
                    {{ is_active ? 'Active' : 'Inactive' }}
                </UBadge>
            </template>

            <template #actions-cell="{ row }">
                <UDropdownMenu :items="actionItems(row.original)">
                    <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
                </UDropdownMenu>
            </template>
        </UTable>

        <div class="flex justify-end mt-4 px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
            <UPagination v-model="page" :total="total" :items-per-page="size" />
        </div>
    </UCard>
</template>

<script setup lang="ts">
const props = defineProps<{
    title: string
    apiPath: string
    columns: any[]
    active: boolean // Untuk mengontrol conditional fetch
}>()

const emit = defineEmits(['edit', 'delete'])

const page = ref(1)
const search = ref("")
const size = ref(10)

// Data Fetching dengan Conditional Logic (Hanya fetch jika tab aktif)
const { data, status, refresh } = await useFetch<any>(props.apiPath, {
    lazy: true,
    server: false,
    immediate: props.active,
    query: {
        page: computed(() => page.value - 1),
        size,
        search
    },
    watch: [() => props.active ? props.active : null, page, search]
})

const items = computed(() => data.value?.data ?? [])
const total = computed(() => data.value?.meta?.total || 0)

const actionItems = (row: any) => [[
    {
        label: "Edit",
        icon: "i-heroicons-pencil-square-20-solid",
        onSelect: () => emit('edit', row)
    },
    {
        label: "Delete",
        icon: "i-heroicons-trash-20-solid",
        onSelect: () => emit('delete', row)
    }
]]

// Ekspos fungsi refresh agar bisa dipanggil dari parent (setelah save modal)
defineExpose({ refresh })
</script>