<template>
  <div class="drawer lg:drawer-open">
    <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
    <div class="drawer-content flex flex-col">
      <!-- Header -->
      <div class="navbar text-primary-content shadow-lg">
        <div class="flex-1">
          <label for="my-drawer-2" class="btn btn-ghost drawer-button lg:hidden">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </label>
          <a href="/" class="btn btn-ghost text-xl font-bold">📦 WMS</a>
        </div>
        <div class="flex-none gap-2">
          <div class="dropdown dropdown-end">
            <button tabindex="0" class="btn btn-ghost btn-circle avatar">
              <div class="w-10 rounded-full bg-accent">
                <span class="text-sm font-bold">👤</span>
              </div>
            </button>
            <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
              <li><a href="#" class="text-base-content">Profile</a></li>
              <li><a href="#" class="text-base-content">Logout</a></li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Main Content -->
      <main class="flex-1 p-4 md:p-6">
        <slot />
      </main>
    </div>

    <!-- Sidebar -->
    <div class="drawer-side">
      <label for="my-drawer-2" aria-label="close sidebar" class="drawer-overlay" />
      <ul class="menu p-4  min-h-full bg-white text-secondary-content space-y-2">
        <!-- Logo -->
        <li class="mb-4">
          <div class="text-2xl font-bold text-accent">📦 WMS</div>
        </li>

        <!-- Navigation Items -->
        <li>
          <NuxtLink to="/" :class="{ active: $route.path === '/' }">
            <span>📊</span> Dashboard
          </NuxtLink>
        </li>
        <li>
          <NuxtLink to="/inventory" :class="{ active: $route.path === '/inventory' }">
            <span>📋</span> Inventory
          </NuxtLink>
        </li>
        <li>
          <NuxtLink to="/inbound" :class="{ active: $route.path === '/inbound' }">
            <span>📥</span> Inbound
          </NuxtLink>
        </li>
        <li>
          <NuxtLink to="/outbound" :class="{ active: $route.path === '/outbound' }">
            <span>📤</span> Outbound
          </NuxtLink>
        </li>
        <li>
          <NuxtLink to="/reports" :class="{ active: $route.path === '/reports' }">
            <span>📈</span> Reports & Analysis
          </NuxtLink>
        </li>

        <!-- Master Data -->
        <li>
          <details>
            <summary><span>⚙️</span> Master Data</summary>
            <ul class="pl-4">
              <li>
                <NuxtLink to="/master/products" :class="{ active: $route.path === '/master/products' }">
                  Products
                </NuxtLink>
              </li>
              <li>
                <NuxtLink to="/master/suppliers" :class="{ active: $route.path === '/master/suppliers' }">
                  Suppliers
                </NuxtLink>
              </li>
              <li>
                <NuxtLink to="/master/warehouses" :class="{ active: $route.path === '/master/warehouses' }">
                  Warehouses
                </NuxtLink>
              </li>
            </ul>
          </details>
        </li>

        <!-- Settings & Help -->
        <li class="mt-auto pt-4 border-t border-secondary-focus">
          <NuxtLink to="/settings" :class="{ active: $route.path === '/settings' }">
            <span>⚙️</span> Settings
          </NuxtLink>
        </li>
        <li>
          <NuxtLink to="/help" :class="{ active: $route.path === '/help' }">
            <span>❓</span> Help & Support
          </NuxtLink>
        </li>
      </ul>
    </div>
  </div>
</template>
