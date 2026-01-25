import type { ColumnDef } from "@tanstack/vue-table";

export type TableProps<T> = {
	columns: ColumnDef<T, unknown>[];
	data: T[];
};
