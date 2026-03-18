<template>
  <div class="min-h-screen flex bg-gray-50 dark:bg-gray-900">
    <!-- Sidebar -->
    <aside
      class="w-64 border-r border-gray-200 dark:border-gray-800 bg-white dark:bg-gray-950  flex-col fixed h-full z-10 hidden md:flex">
      <div class="h-16 flex items-center px-6 border-b border-gray-200 dark:border-gray-800">
        <Logo class="h-8 w-auto text-primary-500" />
        <span class="ml-3 font-bold text-xl text-gray-900 dark:text-white">WMS PRO</span>
      </div>

      <div class="flex-1 overflow-y-auto py-4 px-3">
        <UNavigationMenu :items="links" orientation="vertical" />
      </div>
    </aside>

    <!-- Mobile Header -->
    <div
      class="md:hidden fixed w-full z-20 bg-white dark:bg-gray-950 border-b border-gray-200 dark:border-gray-800 flex items-center justify-between px-4 h-16">
      <div class="flex items-center">
        <Logo class="h-8 w-auto text-primary-500" />
        <span class="ml-2 font-bold text-lg">WMS PRO</span>
      </div>
      <UButton icon="i-heroicons-bars-3" variant="ghost" color="secondary" @click="isOpen = true" />
    </div>

    <!-- Mobile Sidebar Drawer -->
    <USlideover v-model:open="isOpen" side="left" class="md:hidden">
      <template #content>
        <div class="p-4 flex-1 flex flex-col h-full bg-white dark:bg-gray-950">
          <div class="flex items-center justify-between mb-6">
            <div class="flex items-center">
              <Logo class="h-8 w-auto text-primary-500" />
              <span class="ml-2 font-bold text-xl">WMS PRO</span>
            </div>
            <UButton icon="i-heroicons-x-mark" variant="ghost" color="secondary" @click="isOpen = false" />
          </div>
          <UNavigationMenu :items="links" orientation="vertical" @click="isOpen = false" />
          <div class="mt-auto pt-4 border-t border-gray-200 dark:border-gray-800 flex justify-between items-center">
            <span class="text-sm text-gray-500">Theme</span>
            <UColorModeButton />
          </div>
        </div>
      </template>
    </USlideover>

    <!-- Main Content Wrapper -->
    <div class="flex-1 md:ml-64 flex flex-col min-h-screen transition-all duration-300">
      <!-- Desktop Topbar -->
      <header
        class="hidden md:flex items-center justify-between h-16 px-6 border-b border-gray-200 dark:border-gray-800 bg-white dark:bg-gray-950 sticky top-0 z-10">
        <div class="flex items-center">
          <h2 class="text-lg font-semibold text-gray-800 dark:text-gray-200">Dashboard</h2>
        </div>
        <div class="flex items-center gap-4">
          <UDropdownMenu :items="userMenuItems">
            <UButton color="secondary" variant="ghost">
              <UUser :ui="{ name: 'text-left' }" :name="user?.name" :description="user?.email" :avatar="{
                src: user?.image,
                loading: 'lazy',
                icon: 'i-lucide-image'
              }" size="sm" />
              <UIcon name="i-heroicons-chevron-down-20-solid" class="w-5 h-5 text-gray-400" />
            </UButton>
          </UDropdownMenu>
          <UColorModeButton />
        </div>
      </header>

      <!-- Main Content -->
      <main class="flex-1 p-4 md:p-8 pt-20 md:pt-8">
        <slot />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { DropdownMenuItem } from '@nuxt/ui'

const { user, clear } = useUserSession()
const isOpen = ref(false);

const signout = async () => {

  await clear()
  window.location.href = "/auth/auth0"
}

const userMenuItems = ref<DropdownMenuItem[]>([
  [{
    label: 'Notifications',
    icon: 'i-heroicons-bell',
    to: '/notifications'
  }],
  [{
    label: 'Sign out',
    icon: 'i-heroicons-arrow-left-on-rectangle',
    onSelect: signout,
  }]
])

const links = [
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
        label: "Product",
        to: "/master/products",
      },
      {
        label: "Category",
        to: "/master/categories",
      },
      {
        label: "Supplier",
        to: "/master/suppliers",
      },
      {
        label: "Warehouse",
        to: "/master/warehouses",
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
