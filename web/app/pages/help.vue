<template>
  <div class="space-y-6">
    <div>
      <h1 class="text-3xl font-bold text-base-content">Help & Support</h1>
      <p class="text-base-content/60 mt-1">Find answers and get support</p>
    </div>

    <!-- Support Options -->
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <div class="card bg-base-100 shadow-md cursor-pointer hover:shadow-lg transition">
        <div class="card-body">
          <h2 class="card-title text-lg">📚 Documentation</h2>
          <p class="text-sm text-base-content/60">Browse our comprehensive documentation</p>
          <div class="card-actions mt-4">
            <button class="btn btn-sm btn-ghost">View Docs</button>
          </div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md cursor-pointer hover:shadow-lg transition">
        <div class="card-body">
          <h2 class="card-title text-lg">❓ FAQ</h2>
          <p class="text-sm text-base-content/60">Frequently asked questions</p>
          <div class="card-actions mt-4">
            <button class="btn btn-sm btn-ghost">View FAQ</button>
          </div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md cursor-pointer hover:shadow-lg transition">
        <div class="card-body">
          <h2 class="card-title text-lg">🎥 Video Tutorials</h2>
          <p class="text-sm text-base-content/60">Watch step-by-step guides</p>
          <div class="card-actions mt-4">
            <button class="btn btn-sm btn-ghost">Watch Videos</button>
          </div>
        </div>
      </div>

      <div class="card bg-base-100 shadow-md cursor-pointer hover:shadow-lg transition">
        <div class="card-body">
          <h2 class="card-title text-lg">💬 Contact Support</h2>
          <p class="text-sm text-base-content/60">Get help from our support team</p>
          <div class="card-actions mt-4">
            <button class="btn btn-sm btn-ghost">Contact Us</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Contact Form -->
    <div class="card bg-base-100 shadow-md">
      <div class="card-body">
        <h2 class="card-title text-lg">Contact Support</h2>
        <div class="space-y-4 mt-4">
          <div class="form-control">
            <label class="label">
              <span class="label-text">Subject</span>
            </label>
            <input type="text" placeholder="What do you need help with?" class="input input-bordered" />
          </div>
          <div class="form-control">
            <label class="label">
              <span class="label-text">Message</span>
            </label>
            <textarea placeholder="Describe your issue..." class="textarea textarea-bordered" rows="5" />
          </div>
          <button class="btn btn-primary">Send Message</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { definePageMeta } from '#imports';

definePageMeta({
  layout: 'default',
})
</script>
