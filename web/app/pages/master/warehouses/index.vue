<template>
	<div>
		<div class="flex items-center justify-between mb-6">
			<h1 class="text-2xl font-bold text-gray-900 dark:text-white">Warehouse Management</h1>
			<div class="flex gap-2">
				<UButton icon="i-heroicons-plus" color="primary" :label="'Add ' + activeTabLabel"
					@click="openAddModal" />
			</div>
		</div>


		<UTabs v-model="activeTab" :items="tabs" class="mb-6">
			<!-- Warehouse Tab -->
			<template #warehouse>
				<UCard>
					<div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
						<UInput v-model="qWarehouse" placeholder="Filter warehouse..."
							icon="i-heroicons-magnifying-glass" />
					</div>
					<UTable :columns="warehouseColumns" :data="warehouses" :loading="warehouseStatus === 'pending'">
						<template #is_active-cell="{ row: { original: { is_active } } }">
							<UBadge :color="is_active ? 'success' : 'error'">
								{{ is_active ? 'Active' : 'Inactive' }}
							</UBadge>
						</template>
						<template #actions-cell="{ row }">
							<UDropdownMenu :items="warehouseActions(row.original)">
								<UButton color="neutral" variant="ghost"
									icon="i-heroicons-ellipsis-horizontal-20-solid" />
							</UDropdownMenu>
						</template>
					</UTable>
					<div class="flex justify-end mt-4 px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
						<UPagination v-model="warehousePage" :total="warehouseTotal" :items-per-page="size" />
					</div>
				</UCard>
			</template>

			<!-- Zone Tab -->
			<template #zone>
				<UCard>
					<div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
						<UInput v-model="qZone" placeholder="Filter zone..." icon="i-heroicons-magnifying-glass" />
					</div>
					<UTable :columns="zoneColumns" :data="zones" :loading="zoneStatus === 'pending'">
						<template #is_active-cell="{ row: { original: { is_active } } }">
							<UBadge :color="is_active ? 'success' : 'error'">
								{{ is_active ? 'Active' : 'Inactive' }}
							</UBadge>
						</template>
						<template #actions-cell="{ row }">
							<UDropdownMenu :items="zoneActions(row.original as any)">
								<UButton color="neutral" variant="ghost"
									icon="i-heroicons-ellipsis-horizontal-20-solid" />
							</UDropdownMenu>
						</template>
					</UTable>
					<div class="flex justify-end mt-4 px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
						<UPagination v-model="zonePage" :total="zoneTotal" :items-per-page="size" />
					</div>
				</UCard>
			</template>

			<!-- Rack Tab -->
			<template #rack>
				<UCard>
					<div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
						<UInput v-model="qRack" placeholder="Filter rack..." icon="i-heroicons-magnifying-glass" />
					</div>
					<UTable :columns="rackColumns" :data="racks" :loading="rackStatus === 'pending'">
						<template #is_active-cell="{ row: { original: { is_active } } }">
							<UBadge :color="is_active ? 'success' : 'error'">
								{{ is_active ? 'Active' : 'Inactive' }}
							</UBadge>
						</template>
						<template #actions-cell="{ row }">
							<UDropdownMenu :items="rackActions(row.original as any)">
								<UButton color="neutral" variant="ghost"
									icon="i-heroicons-ellipsis-horizontal-20-solid" />
							</UDropdownMenu>
						</template>
					</UTable>
					<div class="flex justify-end mt-4 px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
						<UPagination v-model="rackPage" :total="rackTotal" :items-per-page="size" />
					</div>
				</UCard>
			</template>

			<!-- Location Tab -->
			<template #location>
				<UCard>
					<div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
						<UInput v-model="qLocation" placeholder="Filter location..."
							icon="i-heroicons-magnifying-glass" />
					</div>
					<UTable :columns="locationColumns" :data="locations" :loading="locationStatus === 'pending'">
						<template #is_active-cell="{ row: { original: { is_active } } }">
							<UBadge :color="is_active ? 'success' : 'error'">
								{{ is_active ? 'Active' : 'Inactive' }}
							</UBadge>
						</template>
						<template #actions-cell="{ row }">
							<UDropdownMenu :items="locationActions(row.original as any)">
								<UButton color="neutral" variant="ghost"
									icon="i-heroicons-ellipsis-horizontal-20-solid" />
							</UDropdownMenu>
						</template>
					</UTable>
					<div class="flex justify-end mt-4 px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
						<UPagination v-model="locationPage" :total="locationTotal" :items-per-page="size" />
					</div>
				</UCard>
			</template>
		</UTabs>


		<!-- Modals -->
		<UModal v-model:open="isWarehouseModalOpen" title="Warehouse" scrollable>
			<template #body>
				<UForm :state="warehouseState" class="space-y-4" @submit="saveWarehouse">
					<UFormField label="Name" name="name">
						<UInput v-model="warehouseState.name" class="w-full" />
					</UFormField>
					<UFormField label="Location" name="location">
						<UInput v-model="warehouseState.location" class="w-full" />
					</UFormField>
					<UFormField label="Area" name="area">
						<UInput v-model="warehouseState.area" type="number" class="w-full" />
					</UFormField>
					<UButton type="submit" block>Save Warehouse</UButton>
				</UForm>
			</template>
		</UModal>

		<UModal v-model:open="isZoneModalOpen" title="Zone" scrollable>
			<template #body>
				<UForm :state="zoneState" class="space-y-4" @submit="saveZone">
					<UFormField label="Name" name="name">
						<UInput v-model="zoneState.name" class="w-full" />
					</UFormField>
					<UFormField label="Code" name="code">
						<UInput v-model="zoneState.code" class="w-full" />
					</UFormField>
					<UFormField label="Type" name="type">
						<USelect v-model="zoneState.type" :items="['Storage', 'Receiving', 'Shipping', 'Picking']"
							class="w-full" />
					</UFormField>
					<UFormField label="Warehouse" name="warehouse_id">
						<USelect v-model="zoneState.warehouse_id" :items="warehouseOptions" class="w-full" />
					</UFormField>
					<UButton type="submit" block>Save Zone</UButton>
				</UForm>
			</template>
		</UModal>

		<UModal v-model:open="isRackModalOpen" title="Rack" scrollable>
			<template #body>
				<UForm :state="rackState" class="space-y-4" @submit="saveRack">
					<UFormField label="Name" name="name">
						<UInput v-model="rackState.name" class="w-full" />
					</UFormField>
					<UFormField label="Zone" name="zone_id">
						<USelect v-model="rackState.zone_id" :items="zoneOptions" class="w-full" />
					</UFormField>
					<UFormField label="Rows" name="rows">
						<UInput v-model="rackState.rows" type="number" class="w-full" />
					</UFormField>
					<UFormField label="Cols" name="cols">
						<UInput v-model="rackState.cols" type="number" class="w-full" />
					</UFormField>
					<UButton type="submit" block>Save Rack</UButton>
				</UForm>
			</template>
		</UModal>

		<UModal v-model:open="isLocationModalOpen" title="Location" scrollable>
			<template #body>
				<UForm :state="locationState" class="space-y-4" @submit="saveLocation">
					<UFormField label="Name" name="name">
						<UInput v-model="locationState.name" class="w-full" />
					</UFormField>
					<UFormField label="Rack" name="rack_id">
						<USelect v-model="locationState.rack_id" :items="rackOptions" class="w-full" />
					</UFormField>
					<UFormField label="Type" name="type">
						<USelect v-model="locationState.type" :items="['Storage', 'Receiving', 'Shipping']"
							class="w-full" />
					</UFormField>

					<UFormField label="Bin Number" name="bin_number">
						<UInput v-model="locationState.bin_number" class="w-full" />
					</UFormField>
					<UButton type="submit" block>Save Location</UButton>
				</UForm>
			</template>
		</UModal>
	</div>
</template>

<script setup lang="ts">
import type { Warehouse } from '~~/types/warehouse'
import type { Zone } from '~~/types/zone'
import type { Rack } from '~~/types/rack'
import type { Location } from '~~/types/location'
import type PaginationResponse from '~~/server/utils/pagination';

definePageMeta({
	layout: "default",
});

const tabs = [
	{ label: 'Warehouses', icon: 'i-heroicons-building-office-2', slot: 'warehouse' },
	{ label: 'Zones', icon: 'i-heroicons-squares-plus', slot: 'zone' },
	{ label: 'Racks', icon: 'i-heroicons-rectangle-group', slot: 'rack' },
	{ label: 'Locations', icon: 'i-heroicons-map-pin', slot: 'location' }
]
const activeTab = ref('0')
const activeTabLabel = computed(() => tabs[parseInt(activeTab.value)]?.label.slice(0, -1))

const size = ref(10)

// Warehouse State
const qWarehouse = ref("");
const warehousePage = ref(1);
const isWarehouseModalOpen = ref(false);
const warehouseState = reactive({
	name: "",
	location: "",
	area: 0,
	is_active: true
});

const { data: warehouseData, status: warehouseStatus, refresh: refreshWarehouses } = await useFetch<PaginationResponse<Warehouse>>("/api/warehouses", {
	query: { page: computed(() => warehousePage.value - 1), size, search: qWarehouse },
	watch: [warehousePage, size, qWarehouse]
})

const warehouses = computed(() => warehouseData.value?.data ?? [])
const warehouseTotal = computed(() => warehouseData.value?.meta?.total || 0)
const warehouseOptions = computed(() => warehouses.value.map(w => ({ label: w.name, value: w.id })))


const warehouseColumns = [
	{ accessorKey: "name", header: "Name" },
	{ accessorKey: "location", header: "Location" },
	{ accessorKey: "area", header: "Area" },
	{ accessorKey: "is_active", header: "Status" },
	{ accessorKey: "actions", header: "" }
];

// Zone State
const qZone = ref("");
const zonePage = ref(1);
const isZoneModalOpen = ref(false);
const zoneState = reactive({
	name: "",
	code: "",
	type: "Storage",
	warehouse_id: undefined,
	is_active: true
});

const { data: zoneData, status: zoneStatus, refresh: refreshZones } = await useFetch<PaginationResponse<Zone>>("/api/zones", {
	query: { page: computed(() => zonePage.value - 1), size, search: qZone },
	watch: [zonePage, size, qZone]
})

const zones = computed(() => zoneData.value?.data ?? [])
const zoneTotal = computed(() => zoneData.value?.meta?.total || 0)
const zoneOptions = computed(() => zones.value.map(z => ({ label: z.name, value: z.id })))

const zoneColumns = [
	{ accessorKey: "name", header: "Name" },
	{ accessorKey: "code", header: "Code" },
	{ accessorKey: "type", header: "Type" },
	{ accessorKey: "is_active", header: "Status" },
	{ accessorKey: "actions", header: "" }
];

// Rack State
const qRack = ref("");
const rackPage = ref(1);
const isRackModalOpen = ref(false);
const rackState = reactive({
	name: "",
	rows: 0,
	cols: 0,
	zone_id: undefined,
	is_active: true
});

const { data: rackData, status: rackStatus, refresh: refreshRacks } = await useFetch<PaginationResponse<Rack>>("/api/racks", {
	query: { page: computed(() => rackPage.value - 1), size, search: qRack },
	watch: [rackPage, size, qRack]
})

const racks = computed(() => rackData.value?.data ?? [])
const rackTotal = computed(() => rackData.value?.meta?.total || 0)

const rackOptions = computed(() => racks.value.map(r => ({ label: r.name, value: r.id })))

const rackColumns = [
	{ accessorKey: "name", header: "Name" },
	{ accessorKey: "rows", header: "Rows" },
	{ accessorKey: "cols", header: "Cols" },
	{ accessorKey: "is_active", header: "Status" },
	{ accessorKey: "actions", header: "" }
];

// Location State
const qLocation = ref("");
const locationPage = ref(1);
const isLocationModalOpen = ref(false);
const locationState = reactive({
	name: "",
	type: "Storage",
	bin_number: "",
	rack_id: undefined,
	is_active: true
});

const { data: locationData, status: locationStatus, refresh: refreshLocations } = await useFetch<PaginationResponse<Location>>("/api/locations", {
	query: { page: computed(() => locationPage.value - 1), size, search: qLocation },
	watch: [locationPage, size, qLocation]
})

const locations = computed(() => locationData.value?.data ?? [])
const locationTotal = computed(() => locationData.value?.meta?.total || 0)


const locationColumns = [
	{ accessorKey: "name", header: "Name" },
	{ accessorKey: "type", header: "Type" },
	{ accessorKey: "bin_number", header: "Bin #" },
	{ accessorKey: "is_active", header: "Status" },
	{ accessorKey: "actions", header: "" }
];

// Helpers & Actions
function openAddModal() {
	if (activeTab.value === '0') isWarehouseModalOpen.value = true;
	else if (activeTab.value === '1') isZoneModalOpen.value = true;
	else if (activeTab.value === '2') isRackModalOpen.value = true;
	else if (activeTab.value === '3') isLocationModalOpen.value = true;
}

async function saveWarehouse() {
	try {
		await $fetch('/api/warehouses', { method: 'POST', body: [warehouseState] })
		isWarehouseModalOpen.value = false;
		refreshWarehouses();
	} catch (e) {
		console.error(e);
	}
}

async function saveZone() {
	try {
		await $fetch('/api/zones', { method: 'POST', body: [zoneState] })
		isZoneModalOpen.value = false;
		refreshZones();
	} catch (e) {
		console.error(e);
	}
}

async function saveRack() {
	try {
		await $fetch('/api/racks', { method: 'POST', body: [rackState] })
		isRackModalOpen.value = false;
		refreshRacks();
	} catch (e) {
		console.error(e);
	}
}

async function saveLocation() {
	try {
		await $fetch('/api/locations', { method: 'POST', body: [locationState] })
		isLocationModalOpen.value = false;
		refreshLocations();
	} catch (e) {
		console.error(e);
	}
}

const warehouseActions = (row: Warehouse) => [[
	{ label: "Edit", icon: "i-heroicons-pencil-square-20-solid", onSelect: () => console.log("Edit", row.id) },
	{ label: "Delete", icon: "i-heroicons-trash-20-solid", onSelect: () => console.log("Delete", row.id) }
]];

const zoneActions = (row: Zone) => [[
	{ label: "Edit", icon: "i-heroicons-pencil-square-20-solid", onSelect: () => console.log("Edit", row.id) },
	{ label: "Delete", icon: "i-heroicons-trash-20-solid", onSelect: () => console.log("Delete", row.id) }
]];

const rackActions = (row: Rack) => [[
	{ label: "Edit", icon: "i-heroicons-pencil-square-20-solid", onSelect: () => console.log("Edit", row.id) },
	{ label: "Delete", icon: "i-heroicons-trash-20-solid", onSelect: () => console.log("Delete", row.id) }
]];

const locationActions = (row: Location) => [[
	{ label: "Edit", icon: "i-heroicons-pencil-square-20-solid", onSelect: () => console.log("Edit", row.id) },
	{ label: "Delete", icon: "i-heroicons-trash-20-solid", onSelect: () => console.log("Delete", row.id) }
]];
</script>
