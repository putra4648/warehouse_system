<template>
    <v-layout>
        <v-app-bar color="primary">
            <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>WMS Pro</v-toolbar-title>
        </v-app-bar>

        <MainSidebar :drawer="drawer" />

        <v-main>
            <v-container>
                <NuxtPage />
            </v-container>
        </v-main>
    </v-layout>

</template>
<script setup lang="ts">
import { ref, watch } from "vue";

const drawer = ref(false);
const group = ref(null);

watch(group, () => {
	drawer.value = false;
});
</script>