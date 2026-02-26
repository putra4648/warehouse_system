import type { Product } from "~~/types/product";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<Product>(event, "/api/v1/master/product", {
      method: "POST",
      body,
    });
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<Product>>(
    event,
    "/api/v1/master/product",
    {
      method: "GET",
      query: query,
    },
  );
  return result;
});
