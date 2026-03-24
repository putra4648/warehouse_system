import type { PurchaseOrder } from "~~/types/inbound";

export default defineEventHandler(async (event) => {
  const method = event.method;
  const id = getRouterParam(event, "id");

  if (method === "GET") {
    return await callBackend<PurchaseOrder>(
      event,
      `/api/v1/inbound/receiving/${id}`,
      {
        method: "GET",
      },
    );
  }

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend<PurchaseOrder>(
      event,
      `/api/v1/inbound/receiving/${id}`,
      {
        method: "POST",
        body,
      },
    );
  }
});
