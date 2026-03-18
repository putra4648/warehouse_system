# Nuxt Minimal Starter

Look at the [Nuxt documentation](https://nuxt.com/docs/getting-started/introduction) to learn more.

## Authentication

This application uses Auth0 for authentication. Make sure to configure the following in your `.env` file:

```bash
AUTH_ORIGIN=http://localhost:3000/api/auth
NUXT_OAUTH_AUTH0_CLIENT_ID=your-client-id
NUXT_OAUTH_AUTH0_CLIENT_SECRET=your-client-secret
NUXT_OAUTH_AUTH0_DOMAIN=your-auth0-domain
NUXT_SESSION_PASSWORD=at-least-32-character-long-password
```

## Setup

Make sure to install dependencies:

```bash
# npm
npm install

# pnpm
pnpm install

# yarn
yarn install

# bun
bun install
```

## Development Server

Start the development server on `http://localhost:3000`:

```bash
# npm
npm run dev

# pnpm
pnpm dev

# yarn
yarn dev

# bun
bun run dev
```

## Production

Build the application for production:

```bash
# npm
npm run build

# pnpm
pnpm build

# yarn
yarn build

# bun
bun run build
```

Locally preview production build:

```bash
# npm
npm run preview

# pnpm
pnpm preview

# yarn
yarn preview

# bun
bun run preview
```

Check out the [deployment documentation](https://nuxt.com/docs/getting-started/deployment) for more information.
