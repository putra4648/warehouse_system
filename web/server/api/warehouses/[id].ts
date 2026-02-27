import type { Warehouse } from "~~/types/warehouse";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    return await callBackend<Warehouse>(
      event,
      `/api/v1/master/warehouse/${id}`,
      {
        method: "GET",
      },
    );
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [{ ...body, id: Number(id) }];
    return await callBackend(event, "/api/v1/master/warehouse", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    return await callBackend(event, "/api/v1/master/warehouse", {
      method: "DELETE",
      query: { id: [id] },
    });
  }
});
