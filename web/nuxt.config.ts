// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: ["@nuxt/eslint", "@nuxt/scripts", "@nuxt/icon", "@nuxt/ui"],
  css: ["~/assets/css/main.css"],
  app: {
    head: {
      title: "WMS PRO",
    },
  },
});
