<template>
  <UPage>
    <UPageHeader title="Settings">
      <template #description>
        Manage your workspace preferences and configurations
      </template>
    </UPageHeader>

    <UPageBody>
      <UTabs :items="items" class="w-full">
        <!-- General Settings -->
        <template #general="{ item }">
          <UCard>
            <template #header>
              <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
                {{ item.label }}
              </h3>
              <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
                Configure general system settings.
              </p>
            </template>

            <UForm :state="generalState" class="space-y-4" @submit="onSaveGeneral">
              <UFormField label="System Name" name="systemName">
                <UInput v-model="generalState.systemName" class="w-48" />
              </UFormField>

              <UFormField label="Default Currency" name="currency">
                <USelect v-model="generalState.currency" :items="currencies" class="w-48" />
              </UFormField>

              <UFormField label="Timezone" name="timezone">
                <USelect v-model="generalState.timezone" :items="timezones" class="w-48" />
              </UFormField>

              <div class="flex justify-end">
                <UButton type="submit" label="Save Changes" color="primary" />
              </div>
            </UForm>
          </UCard>
        </template>

        <!-- Notifications Settings -->
        <template #notifications="{ item }">
          <UCard>
            <template #header>
              <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
                {{ item.label }}
              </h3>
              <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
                Manage how you receive notifications.
              </p>
            </template>

            <div class="space-y-6">
              <div class="flex items-start justify-between">
                <div>
                  <p class="font-medium text-gray-900 dark:text-white">
                    Email Notifications
                  </p>
                  <p class="text-sm text-gray-500 dark:text-gray-400">
                    Receive daily summaries and alerts via email.
                  </p>
                </div>
                <USwitch v-model="notificationState.email" />
              </div>
              <div class="flex items-start justify-between">
                <div>
                  <p class="font-medium text-gray-900 dark:text-white">
                    Push Notifications
                  </p>
                  <p class="text-sm text-gray-500 dark:text-gray-400">
                    Receive real-time alerts on your device.
                  </p>
                </div>
                <USwitch v-model="notificationState.push" />
              </div>
              <div class="flex items-start justify-between">
                <div>
                  <p class="font-medium text-gray-900 dark:text-white">
                    Marketing Emails
                  </p>
                  <p class="text-sm text-gray-500 dark:text-gray-400">
                    Receive news and updates about new features.
                  </p>
                </div>
                <USwitch v-model="notificationState.marketing" />
              </div>
            </div>
            <div class="flex justify-end mt-6">
              <UButton label="Save Preferences" color="primary" />
            </div>
          </UCard>
        </template>


      </UTabs>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { SelectItem } from "@nuxt/ui";

const items = [
  { slot: "general", label: "General" },
  { slot: "notifications", label: "Notifications" },
];

// General
const generalState = reactive({
  systemName: "WMS PRO",
  currency: "USD",
  timezone: "UTC",
});

const currencies: SelectItem[] = [
  {
    label: "Indonesian Rupiah",
    value: "IDR"
  },
  {
    label: "Dollar (US)",
    value: "USD"
  }
]
const timezones = ["UTC", "Asia/Jakarta", "America/New_York", "Europe/London"];

function onSaveGeneral() {
  // TODO: Implement save logic
  console.log("General settings saved", generalState);
}

// Notifications
const notificationState = reactive({
  email: true,
  push: false,
  marketing: false,
});

</script>
