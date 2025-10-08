<script setup lang="ts">
import { defineProps } from "vue";

const props = defineProps<{
	drawer: boolean;
}>();

const items = [
	{ type: "subheader", title: "WMS PRO" },
	{ title: "Dashboard", icon: "mdi-view-dashboard", to: "/dashboard" },
	{ title: "Inventory", icon: "mdi-cube", to: "/inventory" },
	{ title: "Inbound", icon: "mdi-tray-arrow-down", to: "/inbound" },
	{ title: "Outbound", icon: "mdi-tray-arrow-up", to: "/outbound" },
	{ title: "Reports & Analytics", icon: "mdi-chart-pie" },
	{
		title: "Master Data",
		icon: "mdi-database",
		children: [
			{ title: "Product", icon: "mdi-package-variant", to: "/master/product" },
			{ title: "Warehouse", icon: "mdi-warehouse", to: "/master/warehouse" },
			{ title: "Supplier", icon: "mdi-truck", to: "/master/supplier" },
		],
	},
	{ type: "divider" },
	{ title: "Settings", icon: "mdi-cog" },
	{
		title: "Help & Support",
		icon: "mdi-help-circle",
		children: [
			{ title: "Documentation", icon: "mdi-book-open" },
			{ title: "Contact Support", icon: "mdi-email" },
		],
	},
	{ title: "Logout", icon: "mdi-logout" },
];
</script>


<template>
    <v-navigation-drawer :model-value="props.drawer" app rail expand-on-hover>
        <v-list density="compact" nav>
            <template v-for="item in items" :key="item.title || item.type">
                <v-list-subheader v-if="item.type === 'subheader'">{{ item.title }}</v-list-subheader>
                <v-divider v-else-if="item.type === 'divider'" />
                <v-list-group v-else-if="item.children" :prepend-icon="item.icon" :value="item.title">
                    <template #activator="{ props: groupProps }">
                        <v-list-item v-bind="groupProps" :title="item.title" />
                    </template>
                    <v-list-item v-for="child in item.children" :key="child.title" :title="child.title"
                        :prepend-icon="child.icon" :to="child.to" link />
                </v-list-group>
                <v-list-item v-else :title="item.title" :prepend-icon="item.icon" :to="item.to" link />
            </template>
        </v-list>
    </v-navigation-drawer>
    <v-navigation-drawer :model-value="props.drawer" app rail expand-on-hover>
        <v-list density="compact" nav>
            <template v-for="item in items" :key="item.title || item.type">
                <v-list-subheader v-if="item.type === 'subheader'">{{ item.title }}</v-list-subheader>
                <v-divider v-else-if="item.type === 'divider'" />
                <v-list-group v-else-if="item.children" :prepend-icon="item.icon" :value="item.title">
                    <template #activator="{ props: groupProps }">
                        <v-list-item v-bind="groupProps" :title="item.title" />
                    </template>
                    <v-list-item v-for="child in item.children" :key="child.title" :title="child.title"
                        :prepend-icon="child.icon" :to="child.to" link />
                </v-list-group>
                <v-list-item v-else :title="item.title" :prepend-icon="item.icon" :to="item.to" link />
            </template>
        </v-list>
    </v-navigation-drawer>
</template>
