import type { Location } from "~~/types/location";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Location>>(
      event,
      "/api/v1/master/location",
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
    return await callBackend<Location>(event, "/api/v1/master/location", {
      method: "POST",
      body: payload,
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<string>(event, "/api/v1/master/location", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<string>(event, "/api/v1/master/location", {
      method: "DELETE",
      body: payload,
    });
  }
});
