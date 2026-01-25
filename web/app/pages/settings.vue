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
                <UInput v-model="generalState.systemName" />
              </UFormField>

              <UFormField label="Default Currency" name="currency">
                <USelect v-model="generalState.currency" :options="currencies" />
              </UFormField>

              <UFormField label="Timezone" name="timezone">
                <USelect v-model="generalState.timezone" :options="timezones" />
              </UFormField>

              <div class="flex justify-end">
                <UButton type="submit" label="Save Changes" color="primary" />
              </div>
            </UForm>
          </UCard>
        </template>

        <!-- Members Settings -->
        <template #members="{ item }">
          <UCard>
            <template #header>
              <div class="flex items-center justify-between">
                <div>
                  <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
                    {{ item.label }}
                  </h3>
                  <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
                    Manage team members and permissions.
                  </p>
                </div>
                <UButton icon="i-heroicons-user-plus" label="Invite Member" color="primary" variant="ghost" />
              </div>
            </template>

            <UTable :columns="memberColumns" :data="members">
              <template #role-cell="{ row }">
                <UBadge :color="row.original.role === 'Admin' ? 'primary' : 'neutral'" variant="subtle">
                  {{ row.original.role }}
                </UBadge>
              </template>
              <template #actions-cell>
                <UButton icon="i-heroicons-ellipsis-vertical" color="secondary" variant="ghost" />
              </template>
            </UTable>
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

        <!-- Security Settings -->
        <template #security="{ item }">
          <UCard>
            <template #header>
              <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
                {{ item.label }}
              </h3>
              <p class="mt-1 text-sm text-gray-500 dark:text-gray-400">
                Update your password and security settings.
              </p>
            </template>

            <UForm :state="securityState" class="space-y-4" @submit="onSaveSecurity">
              <UFormField label="Current Password" name="currentPassword">
                <UInput v-model="securityState.currentPassword" type="password" />
              </UFormField>
              <UFormField label="New Password" name="newPassword">
                <UInput v-model="securityState.newPassword" type="password" />
              </UFormField>
              <UFormField label="Confirm Password" name="confirmPassword">
                <UInput v-model="securityState.confirmPassword" type="password" />
              </UFormField>

              <div class="flex justify-end">
                <UButton type="submit" label="Update Password" color="primary" />
              </div>
            </UForm>
          </UCard>
        </template>
      </UTabs>
    </UPageBody>
  </UPage>
</template>

<script setup lang="ts">
import type { TableColumn } from "@nuxt/ui";

const items = [
  { slot: "general", label: "General" },
  { slot: "members", label: "Members" },
  { slot: "notifications", label: "Notifications" },
  { slot: "security", label: "Security" },
];

// General
const generalState = reactive({
  systemName: "WMS PRO",
  currency: "USD",
  timezone: "UTC",
});

const currencies = ["USD", "EUR", "GBP", "IDR"];
const timezones = ["UTC", "Asia/Jakarta", "America/New_York", "Europe/London"];

function onSaveGeneral() {
  // TODO: Implement save logic
  console.log("General settings saved", generalState);
}

// Members
type Member = {
  id: number;
  name: string;
  email: string;
  role: string;
};

const memberColumns: TableColumn<Member>[] = [
  { accessorKey: "name", header: "Name" },
  { accessorKey: "email", header: "Email" },
  { accessorKey: "role", header: "Role" },
  { id: "actions" },
];

const members: Member[] = [
  { id: 1, name: "John Doe", email: "john@example.com", role: "Admin" },
  { id: 2, name: "Jane Smith", email: "jane@example.com", role: "Staff" },
  { id: 3, name: "Bob Johnson", email: "bob@example.com", role: "Viewer" },
];

// Notifications
const notificationState = reactive({
  email: true,
  push: false,
  marketing: false,
});

// Security
const securityState = reactive({
  currentPassword: "",
  newPassword: "",
  confirmPassword: "",
});

function onSaveSecurity() {
  // TODO: Implement save logic
  console.log("Security settings saved");
}
</script>
