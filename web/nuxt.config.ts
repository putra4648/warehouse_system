import { defineNuxtConfig } from "nuxt/config";
import type { NuxtPage } from "nuxt/schema";

// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: [
    "@nuxt/ui",
    "@nuxt/icon",
    "@nuxt/eslint",
    "@nuxt/scripts",
    "@nuxtjs/color-mode",
    "nuxt-charts",
    "nuxt-auth-utils",
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
  css: ["~/assets/css/main.css"],
  app: {
    head: {
      title: "WMS PRO",
    },
  },
  runtimeConfig: {
    public: {
      serverUrl: process.env.SERVER_URL || "http://localhost:8080",
    },
  },
  hooks: {
    "pages:extend"(pages) {
      function setMiddleware(pages: NuxtPage[]) {
        for (const page of pages) {
          page.meta ||= {};
          page.meta.middleware = ["auth"];

          if (page.children) {
            setMiddleware(page.children);
          }
        }
      }
      setMiddleware(pages);
    },
  },
});
