<template>
    <div>
        <v-row class="mb-4">
            <v-col>
                <h1>Warehouse List</h1>
            </v-col>
            <v-col class="d-flex justify-end">
                <v-btn color="primary" @click="add">
                    <v-icon left>mdi-plus</v-icon>
                    Add Warehouse
                </v-btn>
            </v-col>
        </v-row>
        <div ref="myTable" />
        <v-btn ref="myButton" @click="save">Save</v-btn>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import useTabulator from "../../composables/useTabulator";


const myTable = ref<HTMLElement | null>(null);

const { tabulatorInstance } = useTabulator(myTable, [
    {
        title: "Name",
        field: "title",
        headerFilter: "input",
        editor: "input",
    },
    {
        title: "Location",
        field: "location",
        headerFilter: "input",
        editor: "input",
    },
    {
        title: "Capacity",
        field: "capacity",
        hozAlign: "right",
        formatter: "money",
        formatterParams: {
            decimal: ",",
            thousand: ".",
            symbol: "kg",
            symbolAfter: true,
            precision: 0,
        },
        headerFilter: "input",
    },
    {
        headerSort: false,
        title: "Actions",
        field: "actions",
        hozAlign: "center",
        formatter: function () {
            return "<i class='mdi mdi-trash-can'></i>";
        },
        cellClick: function (e, cell) {
            //delete row on click
            cell.getRow().delete();
        },
    },
]);

const save = () => {
    console.log(tabulatorInstance.value?.getData());
};

const add = () => {
    tabulatorInstance.value?.addRow({});
};

onMounted(() => {
});
</script>
