<script setup lang="ts">
const route = useRoute()

</script>
<template>
    <div class="drawer lg:drawer-open">
        <input id="my-drawer-4" type="checkbox" class="drawer-toggle" />
        <div class="drawer-content">
            <!-- Navbar -->
            <nav class="navbar w-full bg-base-300">
                <label for="my-drawer-4" aria-label="open sidebar" class="btn btn-square btn-ghost">
                    <!-- Sidebar toggle icon -->
                    <Icon name="mdi:chevron-right-box-outline" size="1.5em" />
                </label>
                <div class="px-4">{{ route.meta.name }}</div>
            </nav>
            <!-- Page content here -->
            <slot />
        </div>

        <AppSidebar />
    </div>

</template>
