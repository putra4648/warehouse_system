import vuetify, { transformAssetUrls } from "vite-plugin-vuetify";
import { defineNuxtConfig } from "nuxt/config";

// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: ["@nuxt/eslint", "@nuxt/scripts", (_options, nuxt) => {
    nuxt.hooks.hook("vite:extendConfig", (config) => {
      config.plugins?.push(vuetify({ autoImport: true }));
    });
  }, "@sidebase/nuxt-auth"],
  pages: true,
  css: ["~/assets/app-tabulator.scss"],
  app: {
    head: {
      title: "WMS PRO",
    },
  },
  build: {
    transpile: ["vuetify"],
  },
  vite: {
    vue: {
      template: {
        transformAssetUrls,
      },
    },
  },
  auth: {
    provider: {
      type: 'authjs',
      trustHost: false,
      defaultProvider: 'keycloak',
      addDefaultCallbackUrl: true
    },
    globalAppMiddleware: true,
  },
  runtimeConfig: {
    app: {
      serverUrl: 'http://localhost:9000',
      clientUrl: 'http://localhost:3000'
    }
  }
});