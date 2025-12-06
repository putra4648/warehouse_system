<template>
  <div class="space-y-6">
    <!-- Page Header -->
    <div>
      <h1 class="text-3xl font-bold text-base-content">Dashboard</h1>
      <p class="text-base-content/60 mt-1">Welcome to your Warehouse Management System</p>
    </div>

    <!-- KPI Cards -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm font-semibold text-base-content/60">Total Products</div>
          <div class="text-3xl font-bold text-primary mt-2">2,450</div>
          <div class="text-xs text-success mt-2">↑ 12% from last month</div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm font-semibold text-base-content/60">Stock Value</div>
          <div class="text-3xl font-bold text-accent mt-2">$245,650</div>
          <div class="text-xs text-success mt-2">↑ 8% from last month</div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm font-semibold text-base-content/60">Pending Orders</div>
          <div class="text-3xl font-bold text-warning mt-2">47</div>
          <div class="text-xs text-error mt-2">3 overdue</div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <div class="text-sm font-semibold text-base-content/60">Warehouses</div>
          <div class="text-3xl font-bold text-success mt-2">8</div>
          <div class="text-xs text-base-content/60 mt-2">All operational</div>
        </div>
      </div>
    </div>

    <!-- Charts Section -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- Stock Levels -->
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <h2 class="card-title text-lg">Stock Levels by Warehouse</h2>
          <div class="mt-4 space-y-3">
            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm">Warehouse A</span>
                <span class="text-sm font-semibold">65%</span>
              </div>
              <progress class="progress progress-primary w-full" value="65" max="100" />
            </div>
            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm">Warehouse B</span>
                <span class="text-sm font-semibold">82%</span>
              </div>
              <progress class="progress progress-accent w-full" value="82" max="100" />
            </div>
            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm">Warehouse C</span>
                <span class="text-sm font-semibold">45%</span>
              </div>
              <progress class="progress progress-success w-full" value="45" max="100" />
            </div>
            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm">Warehouse D</span>
                <span class="text-sm font-semibold">72%</span>
              </div>
              <progress class="progress progress-warning w-full" value="72" max="100" />
            </div>
          </div>
        </div>
      </div>

      <!-- Recent Activity -->
      <div class="card bg-base-100 shadow-md">
        <div class="card-body">
          <h2 class="card-title text-lg">Recent Activity</h2>
          <div class="mt-4 space-y-3">
            <div class="flex items-start gap-3">
              <div class="badge badge-success mt-1">+</div>
              <div>
                <p class="font-semibold text-sm">New shipment received</p>
                <p class="text-xs text-base-content/60">Warehouse A - 2 hours ago</p>
              </div>
            </div>
            <div class="flex items-start gap-3">
              <div class="badge badge-info mt-1">→</div>
              <div>
                <p class="font-semibold text-sm">Order dispatched</p>
                <p class="text-xs text-base-content/60">Order #1245 - 4 hours ago</p>
              </div>
            </div>
            <div class="flex items-start gap-3">
              <div class="badge badge-warning mt-1">!</div>
              <div>
                <p class="font-semibold text-sm">Low stock alert</p>
                <p class="text-xs text-base-content/60">Product SKU-4521 - 6 hours ago</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { definePageMeta } from '#imports';

definePageMeta({
  layout: 'default',
});
</script>
