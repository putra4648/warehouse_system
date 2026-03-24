import type { Zone } from "~~/types/zone";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Zone>>(
      event,
      "/api/v1/master/zone",
      {
        method: "GET",
        query: query,
      },
    );
  }

  if (method === "POST") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<Zone>(event, "/api/v1/master/zone", {
      method: "POST",
      body: payload,
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend<string>(event, "/api/v1/master/zone", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "DELETE") {
    const query = getQuery(event);
    return await callBackend<string>(event, "/api/v1/master/zone", {
      method: "DELETE",
      query,
    });
  }
});
