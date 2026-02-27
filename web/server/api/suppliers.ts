import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend(event, "/api/v1/master/supplier", {
      method: "GET",
      query: {
        ...query,
        search: query.search || "",
      },
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend(event, "/api/v1/master/supplier", {
      method: "PUT",
      body: payload,
    });
  }

  if (method === "POST") {
    const body = await readBody(event);
    const payload = Array.isArray(body) ? body : [body];
    return await callBackend(event, "/api/v1/master/supplier", {
      method: "POST",
      body: payload,
    });
  }
});
