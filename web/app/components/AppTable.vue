<template>
	<div class="overflow-x-auto ">
		<table class="table">
			<thead>
				<tr v-for="headerGroup in table.getHeaderGroups()" :key="headerGroup.id">
					<th v-for="header in headerGroup.headers" :key="header.id" :colSpan="header.colSpan">
						<FlexRender v-if="!header.isPlaceholder" :render="header.column.columnDef.header"
							:props="header.getContext()" />
					</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="row in table.getRowModel().rows" :key="row.id">
					<td v-for="cell in row.getVisibleCells()" :key="cell.id">
						<FlexRender :render="cell.column.columnDef.cell" :props="cell.getContext()" />
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr v-for="footerGroup in table.getFooterGroups()" :key="footerGroup.id">
					<th v-for="header in footerGroup.headers" :key="header.id" :colSpan="header.colSpan">
						<FlexRender v-if="!header.isPlaceholder" :render="header.column.columnDef.footer"
							:props="header.getContext()" />
					</th>
				</tr>
			</tfoot>
		</table>
		<AppPagination v-model:page="pageProxy" :total-pages="$props.totalPages" :table="table" />
	</div>
</template>

<script setup lang="ts">
import {
	FlexRender,
	getCoreRowModel,
	useVueTable,
	type ColumnDef,
} from "@tanstack/vue-table";

import type { PropType } from "vue";

const props = defineProps({
	columns: {
		required: true,
		type: Array as PropType<ColumnDef<unknown, unknown>[]>,
	},
	data: {
		required: true,
		type: Array<unknown>,
	},
	rowCount: {
		required: true,
		type: Number,
	},
	page: {
		required: true,
		type: Number,
	},
	totalPages: {
		required: true,
		type: Number,
	},
});

const emit = defineEmits<{
	(e: "update:page", value: number): void;
}>();

const pageProxy = computed({
	get: () => props.page,
	set: (val: number) => emit("update:page", val),
});

const table = useVueTable({
	columns: props.columns,
	get data() {
		return props.data;
	},
	getCoreRowModel: getCoreRowModel(),
	manualPagination: true,
	rowCount: props.rowCount,
	state: {
		pagination: {
			pageIndex: props.page - 1, // sinkron dengan parent
			pageSize: 10,
		},
	},
	onPaginationChange: (updater) => {
		const next =
			typeof updater === "function"
				? updater(table.getState().pagination)
				: updater;

		emit("update:page", next.pageIndex + 1); // balik ke 1-based untuk UI
	},
});

onMounted(() => { });
</script>