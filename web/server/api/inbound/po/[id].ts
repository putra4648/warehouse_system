import { callBackend } from "../../../utils/api";
import type { PurchaseOrder } from "~~/types/inbound";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);
  const id = getRouterParam(event, "id");

  if (method === "GET") {
    return await callBackend<PurchaseOrder>(event, `/api/v1/inbound/po/${id}`, {
      method: "GET",
    });
  }

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend<PurchaseOrder>(event, `/api/v1/inbound/po/${id}`, {
      method: "POST",
      body,
    });
  }
});
