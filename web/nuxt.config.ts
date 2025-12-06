import { defineNuxtConfig } from "nuxt/config";
import tailwindcss from "@tailwindcss/vite";

// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: [
    "@nuxt/icon",
    "@nuxt/eslint",
    "@nuxt/scripts",
    "@sidebase/nuxt-auth",
    "@nuxtjs/color-mode",
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
  vite: {
    plugins: [tailwindcss()],
  },
  css: ["~/assets/app-tabulator.scss", "./app/tailwind.css"],
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
