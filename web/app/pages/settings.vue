<template>
  <div class="space-y-6">
    <div>
      <h1 class="text-3xl font-bold text-base-content">Settings</h1>
      <p class="text-base-content/60 mt-1">Manage system preferences and configurations</p>
    </div>

    <!-- General Settings -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg">General Settings</h2>
        <div class="space-y-4 mt-4">
          <div class="form-control">
            <label class="label">
              <span class="label-text">System Name</span>
            </label>
            <input type="text" placeholder="Warehouse Management System" class="input input-bordered" />
          </div>
          <div class="form-control">
            <label class="label">
              <span class="label-text">Default Currency</span>
            </label>
            <select class="select select-bordered">
              <option selected>USD ($)</option>
              <option>EUR (€)</option>
              <option>GBP (£)</option>
            </select>
          </div>
        </div>
      </div>
    </div>

    <!-- User Management -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg">User Management</h2>
        <div class="space-y-4 mt-4">
          <div class="flex justify-between items-center">
            <div>
              <p class="font-semibold">Admin Users</p>
              <p class="text-sm text-base-content/60">3 users</p>
            </div>
            <button class="btn btn-sm btn-outline">Manage</button>
          </div>
          <div class="divider" />
          <div class="flex justify-between items-center">
            <div>
              <p class="font-semibold">Roles & Permissions</p>
              <p class="text-sm text-base-content/60">Configure user roles</p>
            </div>
            <button class="btn btn-sm btn-outline">Manage</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Backup Settings -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg">Backup & Data</h2>
        <div class="space-y-4 mt-4">
          <button class="btn btn-outline w-full">Create Backup</button>
          <button class="btn btn-outline w-full">View Backup History</button>
        </div>
      </div>
    </div>

    <!-- Save Button -->
    <div class="flex gap-2">
      <button class="btn btn-primary">Save Changes</button>
      <button class="btn btn-ghost">Cancel</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { definePageMeta } from '#imports';

definePageMeta({
  layout: 'default',
})
</script>
