<script setup lang="ts">
definePageMeta({
    layout: false
})

const { signOut, getSession, } = useAuth()

const logout = async () => {
    // await signOut({
    //     callbackUrl: "/"
    // })

    console.log(await getSession({
        force: true
    }))
    // await refresh();
}
</script>

<template>
    <div>
        <NuxtLayout name="main">
            <button @click="logout"> Logout </button>
        </NuxtLayout>
    </div>
</template>