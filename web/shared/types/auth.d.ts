// shared/types/auth.d.ts
declare module "#auth-utils" {
  interface User {
    id: string;
    name: string;
    email: string;
    image: string;
  }

  interface UserSession {
    // Add your own fields
  }

  interface SecureSessionData {
    // Add your own fields
  }
}

export {};
