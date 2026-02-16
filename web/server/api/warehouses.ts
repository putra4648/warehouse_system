import type { Warehouse } from "~~/types/warehouse";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<Warehouse>(
      event,
      "/api/v1/master/warehouse",
      {
        method: "POST",
        body,
      },
    );
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<Warehouse>>(
    event,
    "/api/v1/master/warehouse",
    {
      method: "GET",
      query: query,
    },
  );
  return result;
});
