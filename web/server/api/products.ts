import type { Product } from "~~/types/product";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Product>>(
      event,
      "/api/v1/master/product",
      {
        method: "GET",
        query: query,
      },
    );
  }

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend<Product>(event, "/api/v1/master/product", {
      method: "POST",
      body: body,
    });
  }
});
