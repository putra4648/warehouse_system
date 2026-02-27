import { NuxtAuthHandler } from "#auth";
import type { JWT } from "next-auth/jwt";
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
        token.expiresAt = Date.now() + account.expires_at! * 1000;
      }
      // 2. Refresh token before 5 minutes of expiration (300,000 ms)
      if (Date.now() < (token.accessTokenExpires as number) - 300000) {
        return token;
      }

      // 3. Expired or near expiration -> Refresh!
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

async function refreshAccessToken(token: JWT) {
  try {
    // Ambil base issuer dari env agar dinamis (apakah itu wms atau erp)
    const issuer = process.env.KEYCLOAK_ISSUER;
    const url = `${issuer}/protocol/openid-connect/token`;

    if (!token.refreshToken) throw Error("Refresh token not found");

    const response = await $fetch<{
      access_token: string;
      expires_in: number;
      refresh_token: string;
    }>(url, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({
        client_id: process.env.KEYCLOAK_CLIENT_ID || "", // Gunakan snake_case: client_id
        client_secret: process.env.KEYCLOAK_CLIENT_SECRET || "", // Gunakan snake_case: client_secret
        grant_type: "refresh_token",
        refresh_token: token.refreshToken as string,
      }),
    });

    return {
      ...token,
      accessToken: response.access_token,
      // Pastikan nama property konsisten dengan yang dicek di callback jwt (token.expiresAt)
      expiresAt: Date.now() + response.expires_in * 1000,
      refreshToken: response.refresh_token ?? token.refreshToken,
    };
  } catch (error) {
    console.error(error);
    return {
      ...token,
      error: "RefreshAccessTokenError",
    };
  }
}
