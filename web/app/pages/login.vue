<script setup lang="ts">
definePageMeta({
    layout: false
})
const { signIn } = useAuth()

const login = async () => {
    await signIn('keycloak', { callbackUrl: "/dashboard" })
}
</script>
<template>
    <NuxtLayout name="default">
        <div>
            <v-btn @click="login">Login</v-btn>
        </div>
    </NuxtLayout>
</template>