import type { Rack } from "~~/types/rack";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Rack>>(
      event,
      "/api/v1/master/rack",
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
    return await callBackend<string>(event, "/api/v1/master/rack", {
      method: "POST",
      body: payload,
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<string>(event, "/api/v1/master/rack", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    const query = getQuery(event);
    return await callBackend<string>(event, "/api/v1/master/rack", {
      method: "DELETE",
      query,
    });
  }
});
