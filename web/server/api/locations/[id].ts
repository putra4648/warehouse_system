import type { Location } from "~~/types/location";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    return await callBackend<Location>(event, `/api/v1/master/location/${id}`, {
      method: "GET",
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [{ ...body, id: Number(id) }];
    return await callBackend(event, "/api/v1/master/location", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    // Backend expects DELETE with @RequestBody List<Long>
    return await callBackend(event, "/api/v1/master/location", {
      method: "DELETE",
      body: [Number(id)],
    });
  }
});
