<script setup lang="ts">
import type { Table } from "@tanstack/vue-table";
import { computed } from "vue";

const props = defineProps<{
    page: number;
    totalPages: number;
    table: Table<unknown>;
}>();

const emit = defineEmits<{
    (e: "update:page", value: number): void;
}>();

const pages = computed(() => {
    return Array.from({ length: props.totalPages }, (_, i) => i + 1);
});

function goTo(p: number) {
    if (p >= 1 && p <= props.totalPages) {
        emit("update:page", p);
        props.table.setPageIndex(p - 1);
    }
}
</script>

<template>
    <div class="join">
        <button class="join-item btn" :disabled="page === 1" @click="goTo(page - 1)">
            «
        </button>

        <template v-for="p in pages" :key="p">
            <button class="join-item btn" :class="{ 'btn-active': p === page }" @click="goTo(p)">
                {{ p }}
            </button>
        </template>

        <button class="join-item btn" :disabled="page === totalPages" @click="goTo(page + 1)">
            »
        </button>
    </div>
</template>
