<template>
	<div class="space-y-6">
		<div class="flex justify-between items-center">
			<div>
				<h1 class="text-3xl font-bold text-base-content">Warehouse Master</h1>
				<p class="text-base-content/60 mt-1">Manage warehouse locations and capacity</p>
			</div>
			<NuxtLink to="/master/warehouses/add" class="btn btn-primary">
				<Icon name="mdi-plus" />
				Add Warehouse
			</NuxtLink>
		</div>

		<div class="card bg-base-100 shadow-md">
			<div class="card-body">
				<input type="text" placeholder="Search warehouses..." class="input input-bordered mb-4" />
				<AppTable path="/master/warehouse" :columns="columns" :data="data" :row-count="meta.rowCount"
					v-model:page="page" :total-pages="meta.totalPages" />
			</div>
		</div>
	</div>
</template>

<script setup lang="ts">
import { definePageMeta, onMounted } from "#imports";
import type { ColumnDef, RowData } from "@tanstack/vue-table";

definePageMeta({
	layout: "default",
});

const page = ref<number>(1);
const meta = ref<{
	totalPages: number;
	rowCount: number;
}>({
	rowCount: 0,
	totalPages: 0,
});

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

const data = ref<unknown[]>([]);
const isLoading = ref(false);

const { data: dataSession } = useAuth();

const getData = async () => {
	if (isLoading.value) return;

	isLoading.value = true;
	try {
		const res = await $fetch<{
			meta: {
				total: number;
				page: number;
				last_page: number;
			};
			data: Array<object>;
		}>("http://localhost:8080/api/v1/master/warehouse", {
			method: "GET",
			headers: {
				Authorization: `Bearer ${dataSession.value?.accessToken}`,
			},
			query: {
				page: page.value,
			}
		})

		meta.value = {
			totalPages: res.meta.last_page,
			rowCount: res.meta.total,
		};
		data.value = res.data;
	}
	finally {
		isLoading.value = false
	}
}

watch(page, () => getData(), { flush: 'post' })

onMounted(getData)
</script>
