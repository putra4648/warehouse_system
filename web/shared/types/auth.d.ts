// shared/types/auth.d.ts
declare module "#auth-utils" {
  interface User {
    id: string;
    name: string;
    email: string;
    image: string;
  }

  interface SecureSessionData {
    accessToken: string;
  }
}

export {};
