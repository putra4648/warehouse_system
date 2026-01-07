FROM node:20-alpine AS base

# Install pnpm
RUN corepack enable

FROM base AS dependencies

WORKDIR /app
COPY package.json pnpm-lock.yaml ./
RUN pnpm i

FROM base AS build

WORKDIR /app
COPY --from=dependencies /app/node_modules ./node_modules
COPY . .
RUN pnpm build

FROM base AS main

WORKDIR /app
COPY --from=build /app/.output ./

ENV HOST=0.0.0.0
ENV PORT=3000
EXPOSE 3000

CMD ["node", "server/index.mjs"]
