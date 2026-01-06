<script setup lang="ts">
import type { TableColumn } from '@nuxt/ui';
type Inventory = {
  id: number;
  product: string;
  warehouse: string;
  quantity: number;
}
const columns: TableColumn<Inventory>[] = [
  {
    accessorKey: 'id',
    header: 'id',
  },
  {
    accessorKey: 'product',
    header: 'product',
  },
  {
    accessorKey: 'warehouse',
    header: 'warehouse',
  },
  {
    accessorKey: 'quantity',
    header: 'quantity',
  },
]

const inventory = [{
  id: 1,
  product: 'Laptop',
  warehouse: 'Warehouse A',
  quantity: 100
}, {
  id: 2,
  product: 'Mouse',
  warehouse: 'Warehouse A',
  quantity: 250
}, {
  id: 3,
  product: 'Keyboard',
  warehouse: 'Warehouse B',
  quantity: 150
}, {
  id: 4,
  product: 'Monitor',
  warehouse: 'Warehouse B',
  quantity: 80
}]
</script>

<template>
  <UPage>
    <UPageHeader title="Inventory" />
    <UPageBody>
      <UTable :columns="columns" :data="inventory" />
    </UPageBody>
  </UPage>
</template>