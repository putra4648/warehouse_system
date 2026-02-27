import type { Rack } from "~~/types/rack";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    return await callBackend<Rack>(event, `/api/v1/master/rack/${id}`, {
      method: "GET",
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [{ ...body, id: Number(id) }];
    return await callBackend(event, "/api/v1/master/rack", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    return await callBackend(event, "/api/v1/master/rack", {
      method: "DELETE",
      query: { id: [id] },
    });
  }
});
