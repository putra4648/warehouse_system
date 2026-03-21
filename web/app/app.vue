<template>
  <UApp>
    <UHeader title="WMS Pro" mode="slideover">
      <template #left>
        <Logo class="h-8 w-auto text-primary-500" />
      </template>
      <UNavigationMenu :items="items" />
      <template #right>
        <UDropdownMenu :items="userMenuItems">
          <UButton color="secondary" variant="ghost">
            <UAvatar :src="user?.image" :name="user?.name" :description="user?.email" size="sm" />
            <UIcon name="i-heroicons-chevron-down-20-solid" class="w-5 h-5 text-gray-400" />
          </UButton>
        </UDropdownMenu>
        <UColorModeButton />
      </template>
      <template #body>
        <UNavigationMenu :items="items" orientation="vertical" class="-mx-2.5" />
      </template>

    </UHeader>

    <UMain>
      <NuxtLayout>
        <NuxtPage />
      </NuxtLayout>
    </UMain>

    <UFooter />
  </UApp>
</template>

<script setup lang="ts">
import type { DropdownMenuItem } from '@nuxt/ui'

const { user, clear } = useUserSession()
const signout = async () => {

  await clear()
  window.location.href = "/auth/auth0"
}

const userMenuItems = computed<DropdownMenuItem[]>(() => [
  [
    {
      label: user.value?.name,
      icon: 'i-heroicons-user',
      description: user.value?.email,
    },
    {
      label: 'Notifications',
      icon: 'i-heroicons-bell',
      to: '/notifications'
    }
  ],
  [
    {
      label: 'Sign out',
      icon: 'i-heroicons-arrow-left-on-rectangle',
      onSelect: signout,
    },
  ]
])

const items = [
  {
    label: "Dashboard",
    icon: "i-heroicons-squares-2x2",
    to: "/",
  },
  {
    label: "Inventory",
    icon: "i-heroicons-building-office-2",
    to: "/inventory",
  },
  {
    label: "Inbound",
    icon: "i-heroicons-arrow-down-on-square-stack",
    to: "/inbound",
  },
  {
    label: "Outbound",
    icon: "i-heroicons-arrow-up-on-square-stack",
    to: "/outbound",
  },
  {
    label: "Master Data",
    icon: "i-heroicons-circle-stack",
    children: [
      {
        label: "Customer",
        to: "/master/customer",
        icon: "i-heroicons-user",
      },
      {
        label: "Product",
        to: "/master/products",
        icon: "i-heroicons-cube",
      },
      {
        label: "Category",
        to: "/master/categories",
        icon: "i-heroicons-tag",
      },
      {
        label: "Supplier",
        to: "/master/suppliers",
        icon: "i-heroicons-user-group",
      },
      {
        label: "Warehouse",
        to: "/master/warehouses",
        icon: "i-heroicons-building-office-2",
      },
    ],
  },
  {
    label: "Reports",
    icon: "i-heroicons-chart-pie",
    to: "/reports",
  },
  {
    label: "Settings",
    icon: "i-heroicons-cog-6-tooth",
    to: "/settings",
  },
];
</script>
