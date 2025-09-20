<script setup lang="ts">
import type { NavigationMenuItem } from '@nuxt/ui';


const items = ref<NavigationMenuItem[]>([
    [
        {
            label: 'WMS PRO',
            type: 'label'
        },
        {
            label: 'Dashboard',
            href: '/dashboard',
            icon: 'i-heroicons-chart-bar-solid',
        },
        {
            label: 'Inventory',
            href: '/inventory',
            icon: 'i-heroicons-cube-solid',
        },
        {
            label: "Inbound",
            href: '/inbound',
            icon: 'i-heroicons-arrow-down-tray-solid',
        },
        {
            label: "Outbound",
            href: '/outbound',
            icon: 'i-heroicons-arrow-up-tray-solid',
        },
        // {
        //     label: "Supplier",
        //   
        //     icon: 'i-heroicons-truck-solid'
        // },
        {
            label: 'Reports & Analytics',

            icon: 'i-heroicons-chart-pie-solid',
        },
        {
            label: "Master Data",
            icon: 'i-heroicons-circle-stack-solid',
            children: [
                {
                    label: 'Product',

                    icon: 'i-heroicons-users-solid',
                },
                {
                    label: 'Warehouse',
     href: 'master/warehouse',
                    icon: 'i-heroicons-shield-check-solid',
                },
                {
                    label: 'Supplier',

                    icon: 'i-heroicons-truck-solid',
                },
            ]
        },
    ],
    [
        {
            label: 'Settings',

            icon: 'i-heroicons-cog-solid',
        },
        {
            label: 'Help & Support',

            icon: 'i-heroicons-question-mark-circle-solid',
            children: [
                {
                    label: 'Documentation',

                    icon: 'i-heroicons-book-open-solid',
                },
                {
                    label: 'Contact Support',

                    icon: 'i-heroicons-envelope-solid',
                },
            ]
        },
        {
            label: 'Logout',

            icon: 'i-heroicons-arrow-right-on-rectangle-solid',
        },
    ]
])
</script>
<template>
    <UCard style="height: 100vh;">
        <UNavigationMenu orientation="vertical" :items="items" class="data-[orientation=vertical]:w-48" />
    </UCard>

</template>