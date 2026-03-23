import type { InventoryItem } from "~~/types/inventory";
import type PaginationResponse from "../utils/response";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<InventoryItem>>(
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
  }

  if (method === "POST") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<InventoryItem>(event, "/api/v1/inventory/items", {
      method: "POST",
      body: payload,
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<string>(event, "/api/v1/inventory/items", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    const query = getQuery(event);
    return await callBackend<string>(event, "/api/v1/inventory/items", {
      method: "DELETE",
      query,
    });
  }
});
