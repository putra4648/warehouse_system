<template>
    <form @submit="submit">
        <label for="username"> Username</label>
        <input v-model="form.username">
        <label for="password"> Password</label>
        <input v-model="form.password">
        <button type="submit">Submit</button>
    </form>
</template>
<script setup lang='ts'>
import { ref } from "vue";

const form = ref<{
	username: string;
	password: string;
}>({
	username: "",
	password: "",
});

function submit(e: Event) {
	e.preventDefault();
	console.log("Event");
}
</script>
