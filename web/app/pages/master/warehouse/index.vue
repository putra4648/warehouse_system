<template>
    <div>
        <v-row class="mb-4">
            <v-col>
                <h1>Warehouse List</h1>
            </v-col>
            <v-col class="d-flex justify-end">
                <v-btn color="primary" @click="dialog = true">
                    <v-icon left>mdi-plus</v-icon>
                    Add Warehouse
                </v-btn>
            </v-col>
        </v-row>
        <v-data-table :headers="headers" :items="warehouses" class="elevation-1">
            <template #item.actions="{ item }">
                <v-btn icon @click="editWarehouse(item)"><v-icon>mdi-pencil</v-icon></v-btn>
                <v-btn icon @click="deleteWarehouse(item)"><v-icon>mdi-delete</v-icon></v-btn>
            </template>
        </v-data-table>

        <v-dialog v-model="dialog" max-width="500px">
            <v-card>
                <v-card-title>
                    <span class="headline">{{ editedIndex === -1 ? 'Add' : 'Edit' }} Warehouse</span>
                </v-card-title>
                <v-card-text>
                    <v-text-field v-model="editedItem.name" label="Name" required></v-text-field>
                    <v-text-field v-model="editedItem.location" label="Location" required></v-text-field>
                    <v-text-field v-model="editedItem.capacity" label="Capacity" type="number" required></v-text-field>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text @click="closeDialog">Cancel</v-btn>
                    <v-btn color="primary" text @click="saveWarehouse">Save</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

type Warehouse = {
    readonly name: string
    readonly location: string
    readonly capacity: number
}

const headers = [
    { text: 'Name', value: 'name' },
    { text: 'Location', value: 'location' },
    { text: 'Capacity', value: 'capacity' },
    { text: 'Actions', value: 'actions', sortable: false },
]

const warehouses = ref<Warehouse[]>([
    { name: 'Main Warehouse', location: 'Jakarta', capacity: 10000 },
    { name: 'Secondary Warehouse', location: 'Bandung', capacity: 5000 },
])

const dialog = ref(false)
const editedIndex = ref(-1)
const editedItem = reactive({ name: '', location: '', capacity: 0 })

function editWarehouse(item: Warehouse) {
    editedIndex.value = warehouses.value.indexOf(item)
    Object.assign(editedItem, item)
    dialog.value = true
}

function deleteWarehouse(item: Warehouse) {
    const index = warehouses.value.indexOf(item)
    if (index > -1) warehouses.value.splice(index, 1)
}

function closeDialog() {
    dialog.value = false
    editedIndex.value = -1
    Object.assign(editedItem, { name: '', location: '', capacity: 0 })
}

function saveWarehouse() {
    if (editedIndex.value > -1) {
        warehouses.value[editedIndex.value] = editedItem
    } else {
        warehouses.value.push({ ...editedItem })
    }
    closeDialog()
}
</script>

<style scoped>
.v-data-table {
    margin-top: 16px;
}
</style>
