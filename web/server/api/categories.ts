import type { Category } from "~~/types/product";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<Category>(
      event,
      "/api/v1/master/category",
      {
        method: "POST",
        body,
      },
    );
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<Category>>(
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
  return result;
});
