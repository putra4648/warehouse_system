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

        <v-dialog max-width="500" v-model="dialog">
            <template v-slot:activator="{ props: activatorProps }">
                <v-btn v-bind="activatorProps" color="surface-variant" text="Open Dialog" variant="flat"></v-btn>
            </template>

            <template v-slot:default="{ isActive }">
                <v-card title="Dialog">
                    <v-card-text>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua.
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn text="Close Dialog" @click="isActive.value = false"></v-btn>
                    </v-card-actions>
                </v-card>
            </template>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import useTabulator from "../../composables/useTabulator";

const dialog = ref(false);
const myTable = ref<HTMLElement | null>(null);

const { tabulatorInstance } = useTabulator(myTable, [
    {
        title: "", field: "", formatter: function () {
            return "<i class='mdi mdi-eye'></i>";
        },
        cellClick: function () {
            dialog.value = !dialog.value;

        },
        width: 50,
        headerSort: false
    },
    {
        title: "No",
        field: "",
        formatter: "rownum",
        width: 50,
        headerSort: false
    },
    {
        title: "Name",
        field: "title",
        headerFilter: "input",

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
