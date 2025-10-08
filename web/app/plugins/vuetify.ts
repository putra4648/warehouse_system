// import this after install `@mdi/font` package
import "@mdi/font/css/materialdesignicons.css";

import "vuetify/styles";
import { createVuetify } from "vuetify";

export default defineNuxtPlugin((app) => {
	const vuetify = createVuetify({
		// ... your configuration
		theme: {
			defaultTheme: "system",
			themes: {
				light: {
					dark: false,
					colors: {
						background: "#F5F5F5",
						surface: "#FFFFFF",
						primary: "#50A6FF",
						secondary: "#E6E9FF",
						error: "#F44336",
						info: "#2196F3",
						success: "#4CAF50",
						warning: "#FFC107",
						"on-background": "#212121",
						"on-surface": "#212121",
					},
				}
			},
		},
		ssr: true
	});
	app.vueApp.use(vuetify);
});
