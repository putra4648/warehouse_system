import NextAuth, { DefaultSession } from "next-auth";

declare module "next-auth" {
    interface Session {
        idToken?: string;
        accessToken?: string;
        refreshToken?: string;
    }

    interface User {
        idToken?: string;
    }
}

declare module "next-auth/jwt" {
    interface JWT {
        idToken?: string;
        accessToken?: string;
        refreshToken?: string;
    }
}
