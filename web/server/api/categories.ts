import type { Category } from "~~/types/product";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Category>>(
      event,
      "/api/v1/master/category",
      {
        method: "GET",
        query: {
          ...query,
          search: query.search || "",
        },
      },
    );
  }

  if (method === "POST") {
    const body = await readBody(event);
    // Backend expects a List<CategoryDto>
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<Category>(event, "/api/v1/master/category", {
      method: "POST",
      body: payload,
    });
  }
});
