<template>
    <UBadge :color="color" variant="subtle">
        {{ props.status }}
    </UBadge>
</template>

<script setup lang="ts">
import { OrderStatus } from '~~/types/enums/order_enum';
import { ReceivingStatus } from '~~/types/enums/receiving_status_enum';

const props = defineProps<{
    status: OrderStatus | ReceivingStatus
}>()

function isOrderStatus(status: unknown): status is OrderStatus {
    return Object.values(OrderStatus).includes(status as OrderStatus);
}

function isReceivingStatus(status: unknown): status is ReceivingStatus {
    return Object.values(ReceivingStatus).includes(status as ReceivingStatus);
}

const color = computed(() => {
    if (isOrderStatus(props.status)) {
        switch (props.status) {
            case OrderStatus.COMPLETED:
                return 'success'
            case OrderStatus.PENDING:
                return 'warning'
            case OrderStatus.CANCELLED:
                return 'error'
            default:
                return 'secondary'
        }
    } else if (isReceivingStatus(props.status)) {
        switch (props.status) {
            case ReceivingStatus.RECEIVED:
                return 'success'
            case ReceivingStatus.PARTIALLY_RECEIVED:
                return 'primary'
            case ReceivingStatus.OVER_RECEIVE:
                return 'error'
            default:
                return 'secondary'
        }
    } else {
        return 'secondary'
    }
})
</script>