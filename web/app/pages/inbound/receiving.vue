<template>
    <UPage>
        <UPageHeader title="Receiving" description="Manage Receiving Goods" />
        <UPageBody>
            <div class="flex items-center justify-between">
                <UButton icon="i-heroicons-plus" color="primary" label="Create Receiving" @click="isOpen = true" />
            </div>
            <UCard>
                <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
                    <UInput v-model="q" placeholder="Filter Receiving..." icon="i-heroicons-magnifying-glass" />
                </div>

                <UTable :data="receivingResponse?.data" :columns="receivingColumns">
                    <template #purchase_order-cell="{ row }">
                        {{ row.original.purchase_order?.po_number }}
                    </template>
                    <template #status-cell="{ row }">
                        <StatusBadge :status="row.original.status!" />
                    </template>
                    <template #actions-cell="{ row }">
                        <UDropdownMenu :items="items(row.original)">
                            <UButton color="neutral" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
                        </UDropdownMenu>
                    </template>
                </UTable>
                <div class="flex px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
                    <UPagination v-model="page" :total="total" @update:page="(p) => page = p" />
                </div>
            </UCard>
        </UPageBody>


        <!-- Create Modal -->
        <UModal v-model:open="isOpen" title="Create Receiving Goods" fullscreen scrollable>
            <template #body>
                <UForm :state="state" class="space-y-4" @submit="saveReceiving">
                    <UFormField label="PO Number" name="poNumber">
                        <USelectMenu v-model="state.purchase_order!.id" class="w-full" value-key="id"
                            label-key="po_number" clear :items="purchaseOrderResponse?.data"
                            @update:open="executePurchaseOrder()" />
                    </UFormField>

                    <UFormField label="Items" name="purchaseOrderLines">
                        <UButton label="Add Item" icon="i-heroicons-plus" color="primary" @click="addItem" />
                        <UTable :data="state.receiving_lines" :columns="receivingLineColumns">
                            <template #productName-cell="{ row }">
                                <USelectMenu v-model="row.original.product.id" class="w-full" value-key="id"
                                    label-key="name" clear :items="productResponse" @update:open="executeProduct()"
                                    @change="handleProductChange(row)" />
                            </template>
                            <template #productQty-cell="{ row }">
                                <UInput v-model="row.original.product.quantity" type="number" class="w-full" min="1"
                                    disabled />
                            </template>
                            <template #qty_received-cell="{ row }">
                                <UInput v-model="row.original.qty_received" type="number" class="w-full" min="1" />
                            </template>
                            <template #lot_number-cell="{ row }">
                                <UInput v-model="row.original.lot_batch" type="text" class="w-full" />
                            </template>
                            <template #expiry_date-cell="{ row }">
                                <UInput v-model="row.original.expiry_date" type="date" class="w-full" />
                            </template>
                            <template #actions-cell="{ row }">
                                <UButton color="error" variant="ghost" icon="i-heroicons-trash"
                                    @click="deleteItem(row.original)" />
                            </template>
                        </UTable>
                    </UFormField>
                    <UButton type="submit" block color="primary">Create PO</UButton>
                </UForm>
            </template>
        </UModal>

        <!-- Detail Modal -->
        <UModal v-model:open="detailModal.open" fullscreen>
            <template #title>
                {{ detailModal.title }}
            </template>

            <template #description>
                Detail Receiving
            </template>

            <template #body>
                <UCard v-if="detailModal.data">
                    <template #header>
                        <div class="flex items-center justify-between">
                            <h2 class="text-lg font-medium">Receiving Order Details</h2>
                            <StatusBadge :status="detailModal.data.status!" />
                        </div>
                    </template>

                    <div class="space-y-4">
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <p class="text-sm font-medium text-gray-500 dark:text-gray-400">Receiving Number</p>
                                <p class="text-base font-semibold text-gray-900 dark:text-white">{{
                                    detailModal.data.receiving_number
                                }}</p>
                            </div>
                            <div>
                                <p class="text-sm font-medium text-gray-500 dark:text-gray-400">Receiving Date</p>
                                <p class="text-base font-semibold text-gray-900 dark:text-white">{{
                                    detailModal.data.received_date }}
                                </p>
                            </div>
                        </div>

                        <div class="border-t border-gray-200 dark:border-gray-700 pt-4">
                            <h3 class="text-lg font-medium mb-4">Products</h3>
                            <UTable :data="detailModal.data.receiving_lines || []" :columns="receivingLineColumns">
                                <template #productName-cell="{ row }">
                                    {{ row.original.product?.name }}
                                </template>

                                <template #status-cell="{ row }">
                                    <StatusBadge :status="row.original.status!" />
                                </template>
                            </UTable>
                            <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
                                <UPagination v-model="detailPage" :total="detailSize"
                                    @update:page="(p) => detailPage = p" />
                            </div>
                        </div>
                    </div>
                </UCard>
            </template>
        </UModal>
    </UPage>
</template>

<script setup lang="ts">
import type { DropdownMenuItem, TableColumn } from '@nuxt/ui';
import type { PaginationResponse, ResponseData } from '~~/server/utils/response';
import { ReceivingStatus } from '~~/types/enums/receiving_status_enum';
import type { PurchaseOrder, Receiving, ReceivingLine, } from '~~/types/inbound';
import type { Product } from '~~/types/product';
import type { Row } from '@tanstack/vue-table';
import { OrderStatus } from '~~/types/enums/order_enum';


const isOpen = ref(false);
const q = ref('')
const page = ref(1)
const size = ref(10)
const detailPage = ref(1)
const detailSize = ref(0)
const total = computed(() => receivingResponse.value?.meta?.total || 0)
const state = reactive<Receiving>({
    id: null,
    receiving_number: "",
    received_date: "",
    status: OrderStatus.OPEN,
    user_id: null,
    purchase_order: {
        id: null,
        po_number: "",
    },
    receiving_lines: []
})
const detailModal = ref({
    open: false,
    data: null as Receiving | null,
    title: ""
})

const receivingColumns: TableColumn<Receiving>[] = [
    {
        accessorKey: 'purchase_order',
        header: 'PO Number',
    },
    {
        accessorKey: 'status',
        header: 'Status',
    },
    {
        accessorKey: 'receiving_number',
        header: 'Receiving Number',
    },
    {
        accessorKey: 'received_date',
        header: 'Received Date',
    },
    {
        accessorKey: 'actions',
        header: 'Actions',
    },
]


const receivingLineColumns: TableColumn<ReceivingLine>[] = [
    {
        accessorKey: 'productName',
        header: 'Product',
        cell: ({ row }) => {
            return row.original.product.name
        }
    },
    {
        accessorKey: 'productQty',
        header: 'Quantity',
        cell: ({ row }) => {
            return row.original.product.quantity
        }
    },
    {
        accessorKey: 'receivedPercent',
        header: 'Received Percent',
        cell: ({ row }) => {
            if (row.original.product.quantity && row.original.qty_received) {
                const percent = (row.original.qty_received / row.original.product.quantity) * 100
                // update status
                if (percent === 100) {
                    row.original.status = ReceivingStatus.RECEIVED
                } else if (percent > 0 && percent < 100) {
                    row.original.status = ReceivingStatus.PARTIALLY_RECEIVED
                } else if (percent > 100) {
                    row.original.status = ReceivingStatus.OVER_RECEIVE
                }
                return percent + " %"
            }
            return "0 %"
        },

    },
    {
        accessorKey: 'qty_received',
        header: 'Received Quantity',
    },
    {
        accessorKey: 'lot_batch',
        header: 'Lot Number',
    },
    {
        accessorKey: 'expiry_date',
        header: 'Expiry Date',
    },
    {
        accessorKey: 'status',
        header: 'Status',
    },
]

const { data: receivingResponse, refresh } = useFetch<PaginationResponse<Receiving>>(`/api/inbound/receiving`, {
    query: computed(() => ({
        page: page.value,
        size: size.value,
        search: q.value
    })),
})

const { data: purchaseOrderResponse, execute: executePurchaseOrder } = useLazyFetch<PaginationResponse<PurchaseOrder>>(`/api/inbound/po`, {
    key: `purchase-order`,
    query: computed(() => ({
        page: 1,
        size: size.value,
        id: state.purchase_order!.id,
        status: OrderStatus.OPEN
    })),
    immediate: false,
})

const { data: productResponse, execute: executeProduct } = useLazyFetch(() => `/api/inbound/po/${state.purchase_order!.id}`, {
    key: `product-${state.purchase_order!.id}`,
    query: {
        page: 1,
        limit: 100,
    },
    transform: (response: ResponseData<PurchaseOrder>) => {
        return response.data.purchase_order_lines!.map((line) => {
            const product: Product = {
                ...line.product,
                quantity: line.quantity,
            }
            return product
        })
    },
    immediate: state.purchase_order!.id! != null,
    watch: [() => state.purchase_order!.id!]
})
const items = (row: Receiving): DropdownMenuItem[][] => {
    const menu: DropdownMenuItem[][] = [];
    menu.push([
        {
            label: "View Details",
            icon: "i-heroicons-eye-20-solid",
            onSelect: () => handleShowDetail(row),
        },

    ]);

    if (row.status === OrderStatus.OPEN) {
        menu.push([
            {
                label: "Cancel",
                icon: "i-heroicons-x-circle-20-solid",
                color: 'error',
                onSelect: () => cancelReceiving(row.id!),
            },
            {
                label: "Edit",
                icon: "i-heroicons-pencil-square-20-solid",
                onSelect: () => Object.assign(state, row),
            },
        ])
    }
    return menu;
};

function resetForm() {
    state.receiving_lines = []
    state.purchase_order = {
        id: null,
        po_number: "",
    }
    state.received_date = ""
    state.status = OrderStatus.OPEN
    state.user_id = null
}

async function saveReceiving() {
    try {
        await $fetch<ResponseData<Receiving>>(`/api/inbound/receiving`, {
            method: "POST",
            body: {
                ...state,
                receiving_lines: state.receiving_lines?.map((line) => {
                    return {
                        ...line,
                        product: {
                            ...line.product,
                        },
                        qty: line.product.quantity,
                    }
                }),
                received_date: new Date().toISOString().split('T')[0] ?? "",
            } as Receiving,
        })
        resetForm()
        isOpen.value = false
        await refresh()
    } catch (error) {
        console.log(error)
    }
}

function deleteItem(item: ReceivingLine) {
    state.receiving_lines = state.receiving_lines?.filter((i) => i !== item)
}

function addItem() {
    state.receiving_lines?.push({
        id: null,
        product: {
            id: null,
            name: "",
            quantity: 0
        },
        qty_received: 0,
        lot_batch: '',
        expiry_date: '',
        received_date: new Date().toISOString().split('T')[0] ?? "",
        status: ReceivingStatus.RECEIVED
    })
}

function handleProductChange(row: Row<ReceivingLine>) {
    const product = productResponse.value?.find((p) => p.id === row.original.product.id)
    if (product) {
        row.original.product.quantity = product.quantity
    }
}

async function handleShowDetail(row: Receiving) {
    try {
        const response = await $fetch<ResponseData<Receiving>>(`/api/inbound/receiving/${row.id}`)
        detailModal.value.data = {
            ...response.data,
            receiving_lines: response.data?.receiving_lines?.map((line) => {
                return {
                    ...line,
                    product: {
                        ...line.product,
                        quantity: line.qty,
                    },
                }
            })
        }
        detailModal.value.title = `PO ${response.data?.purchase_order?.po_number}`
        detailModal.value.open = true
    } catch (error) {
        console.log(error)
    }
}

function cancelReceiving(id: number) {
    try {
        $fetch<ResponseData<Receiving>>(`/api/inbound/receiving/${id}`, {
            method: "PATCH",
            query: {
                status: OrderStatus.CANCELLED
            }
        })
    } catch (error) {
        console.log(error)
    }
}


</script>