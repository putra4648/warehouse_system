import type { Product } from "~~/types/product";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    return await callBackend<Product>(event, `/api/v1/master/product/${id}`, {
      method: "GET",
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [{ ...body, id: Number(id) }];
    return await callBackend(event, "/api/v1/master/product", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    return await callBackend(event, "/api/v1/master/product", {
      method: "DELETE",
      query: { id: [id] },
    });
  }
});
