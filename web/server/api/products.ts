import type { Product } from "~~/types/product";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

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
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<Product>(event, "/api/v1/master/product", {
      method: "POST",
      body: payload,
    });
  }
});
