import type { Category } from "~~/types/product";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    return await callBackend<Category>(event, `/api/v1/master/category/${id}`, {
      method: "GET",
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    // Backend expects a List<CategoryDto> for updates at the base URL
    const payload = Array.isArray(body) ? body : [{ ...body, id: Number(id) }];
    return await callBackend(event, "/api/v1/master/category", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    // Backend expects DeleteMapping with @RequestParam List<Long> id
    return await callBackend(event, "/api/v1/master/category", {
      method: "DELETE",
      query: { id: [id] },
    });
  }
});
