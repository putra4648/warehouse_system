import { defineNuxtConfig } from "nuxt/config";

// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: [
    '@nuxt/ui',
    "@nuxt/icon",
    "@nuxt/eslint",
    "@nuxt/scripts",
    "@sidebase/nuxt-auth",
    "@nuxtjs/color-mode",
    "nuxt-charts",
  ],
  icon: {
    mode: "css",
    cssLayer: "base",
  },
  colorMode: {
    preference: "nord",
    dataValue: "theme",
  },
  pages: true,
  css: ['~/assets/css/main.css'],
  app: {
    head: {
      title: "WMS PRO",
    },
  },
  auth: {
    provider: {
      type: "authjs",
      trustHost: false,
      defaultProvider: "keycloak",
      addDefaultCallbackUrl: true,
    },
    globalAppMiddleware: false,
  },
  runtimeConfig: {
    app: {},
    public: {
      serverUrl: "http://localhost:9000",
      clientUrl: "http://localhost:3000",
    },
  },
});