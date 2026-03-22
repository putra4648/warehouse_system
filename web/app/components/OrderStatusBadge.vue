<template>
    <UBadge :color="color" variant="subtle">
        {{ props.status }}
    </UBadge>
</template>

<script setup lang="ts">
import { OrderStatus } from '~~/types/enums/order_enum';

const props = defineProps<{
    status: OrderStatus
}>()

const color = computed(() => {
    switch (props.status) {
        case OrderStatus.COMPLETED:
            return 'success'
        case OrderStatus.PENDING:
            return 'primary'
        case OrderStatus.CANCELLED:
            return 'secondary'
        default:
            return 'secondary'
    }
})
</script>