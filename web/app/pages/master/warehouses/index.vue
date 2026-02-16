<template>
	<div>
		<div class="flex items-center justify-between mb-6">
			<h1 class="text-2xl font-bold text-gray-900 dark:text-white">Warehouse</h1>
			<UButton icon="i-heroicons-plus" color="primary" label="Add Warehouse" @click="isOpen = true" />
		</div>

		<UCard>
			<div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
				<UInput v-model="q" placeholder="Filter warehouse..." icon="i-heroicons-magnifying-glass" />
			</div>

			<UTable :columns="columns" :data="warehouses" :loading="status === 'pending'" />

			<div class="flex justify-end mt-4">
				<UPagination v-model:page="page" :total="total" :items-per-page="size" />
			</div>
		</UCard>

		<!-- <UModal v-model:open="isOpen" title="Add Product" scrollable>
			<template #body>
				<UForm :state="state" class="space-y-4">
					<UFormField label="Name" name="name">
						<UInput v-model="state.name" class="w-full" />
					</UFormField>
					<UFormField label="SKU" name="sku">
						<UInput v-model="state.sku" class="w-full" />
					</UFormField>
					<UFormField label="Category" name="category">
						<USelect v-model="state.category" :items="['Electronics', 'Clothing', 'Furniture']"
							class="w-full" />
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
</UModal> -->
	</div>
</template>

<script setup lang="ts">
import type { Warehouse } from '~~/types/warehouse'
import { definePageMeta } from "#imports";
import type { ColumnDef, RowData } from "@tanstack/vue-table";
import type PaginationResponse from '~~/server/utils/pagination';

definePageMeta({
	layout: "default",
});

const isOpen = ref(false);
const q = ref("");
const page = ref<number>(1);
const size = ref(10)

const warehouses = computed(() => (data.value?.data ?? []) as Warehouse[])
const total = computed(() => data.value?.meta.total || 0)

const columns: ColumnDef<RowData>[] = [
	{
		accessorKey: "id",
		header: "ID",
	},
	{
		accessorKey: "name",
		header: "Name",
	},
	{
		accessorKey: "location",
		header: "Location",
	},
	{
		accessorKey: "area",
		header: "Area",
	},
];
const { data, status } = await useFetch<PaginationResponse<Warehouse>>("/api/warehouses", {
	query: {
		page,
		size
	},
	watch: [page, size]
})
</script>
