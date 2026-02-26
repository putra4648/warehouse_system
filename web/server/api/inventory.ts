import type { InventoryItem } from "~~/types/inventory";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<InventoryItem>(
      event,
      "/api/v1/inventory/items",
      {
        method: "POST",
        body,
      },
    );
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<InventoryItem>>(
    event,
    "/api/v1/inventory/items",
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
