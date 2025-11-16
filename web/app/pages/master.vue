<template>
    <div>
        <v-breadcrumbs :items="items">
            <template v-slot:prepend>
                <v-icon icon="mdi-domain" size="small"></v-icon>
            </template>
        </v-breadcrumbs>

        <NuxtPage />
    </div>
</template>
<script setup lang="ts">
import type { BreadcrumbItem } from "vuetify/lib/components/VBreadcrumbs/VBreadcrumbs.mjs";

const router = useRouter();

const items: BreadcrumbItem[] = router.currentRoute.value.fullPath
	.split("/")
	.filter((r) => r !== "master")
	.map((route) => {
		return {
			title: route.charAt(0).toUpperCase() + route.slice(1),
			to: route,
		};
	});

definePageMeta({
	key: (route) => route.fullPath,
});
onMounted(() => {
	console.log("Master page mounted");
});
</script>
