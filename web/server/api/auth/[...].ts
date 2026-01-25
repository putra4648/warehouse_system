import { NuxtAuthHandler } from "#auth";
import KeycloakProvider from "next-auth/providers/keycloak";

export default NuxtAuthHandler({
	secret: "some random string",
	providers: [
		// @ts-expect-error You need to use .default here for it to work during SSR. May be fixed via Vite at some point
		KeycloakProvider.default({
			issuer: process.env.KEYCLOAK_ISSUER,
			clientId: process.env.KEYCLOAK_CLIENT_ID || "",
			clientSecret: process.env.KEYCLOAK_CLIENT_SECRET || "",
		}),
	],
	callbacks: {
		async jwt({ token, account }) {
			if (account) {
				token.idToken = account.id_token;
				token.accessToken = account.access_token;
				token.refreshToken = account.refresh_token;
				token.expiresAt = Date.now() + account.expires_at! * 1000

			}
			// ✅ Jika access token masih valid → pakai saja
			if (Date.now() < token.expiresAt! - 60_000) {
				return token
			}
			return refreshAccessToken(token);
		},
		async session({ session, token }) {
			session.idToken = token.idToken;
			session.accessToken = token.accessToken;
			session.refreshToken = token.refreshToken;
			return session;
		},
	},
});


async function refreshAccessToken(token: any) {
	const config = useRuntimeConfig();
	try {
		const url =
			"http://localhost:9000/realms/wms/protocol/openid-connect/toke"

		const response = await $fetch<{
			access_token: string,
			expires_in: number,
			refresh_token: string
		}>(url, {
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded" },
			body: new URLSearchParams({
				clientId: process.env.KEYCLOAK_CLIENT_ID || "",
				clientSecret: process.env.KEYCLOAK_CLIENT_SECRET || "",
				grant_type: "refresh_token",
				refresh_token: token.refreshToken,
			}),
		})

		return {
			...token,
			accessToken: response.access_token,
			accessTokenExpires: Date.now() + response.expires_in * 1000,
			refreshToken: response.refresh_token ?? token.refreshToken,
		}

	} catch (error) {
		console.error("Refresh token error", error)
		return {
			...token,
			error: "RefreshAccessTokenError",
		}
	}
}