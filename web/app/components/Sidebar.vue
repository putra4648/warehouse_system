<script setup lang="ts">
import type { NavigationMenuItem } from '@nuxt/ui';


const items = ref<NavigationMenuItem[][]>([
    [
        {
            label: 'WMS PRO',
            type: 'label'
        },
        {
            label: 'Dashboard',
            href: '/dashboard',
            icon: 'i-heroicons-chart-bar-20-solid',
        },
        {
            label: 'Inventory',

            icon: 'i-heroicons-cube-20-solid',
        },
        {
            label: "Inbound",

            icon: 'i-heroicons-arrow-down-tray-20-solid',
        },
        {
            label: "Outbound",

            icon: 'i-heroicons-arrow-up-tray-20-solid',
        },
        // {
        //     label: "Supplier",
        //   
        //     icon: 'i-heroicons-truck-20-solid'
        // },
        {
            label: 'Reports & Analytics',

            icon: 'i-heroicons-chart-pie-20-solid',
        },
        {
            label: "Master Data",

            icon: 'i-heroicons-circle-stack-20-solid',
            children: [
                {
                    label: 'Product',

                    icon: 'i-heroicons-users-20-solid',
                },
                {
                    label: 'Warehouse',

                    icon: 'i-heroicons-shield-check-20-solid',
                },
                {
                    label: 'Supplier',

                    icon: 'i-heroicons-truck-20-solid',
                },
            ]
        },
        {
            label: 'Outbound',

            icon: 'i-heroicons-arrow-up-tray-20-solid',
        },
        {
            label: 'Warehouse',

            icon: 'i-heroicons-building-office-20-solid',
        },
    ],
    [
        {
            label: 'Settings',

            icon: 'i-heroicons-cog-20-solid',
        },
        {
            label: 'Help & Support',

            icon: 'i-heroicons-question-mark-circle-20-solid',
            children: [
                {
                    label: 'Documentation',

                    icon: 'i-heroicons-book-open-20-solid',
                },
                {
                    label: 'Contact Support',

                    icon: 'i-heroicons-envelope-20-solid',
                },
            ]
        },
        {
            label: 'Logout',

            icon: 'i-heroicons-arrow-right-on-rectangle-20-solid',
        },
    ]
])
</script>
<template>
    <UNavigationMenu orientation="vertical" :items="items" class="data-[orientation=vertical]:w-48" />

</template>