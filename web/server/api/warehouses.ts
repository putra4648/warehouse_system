import type { Warehouse } from "~~/types/warehouse";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Warehouse>>(
      event,
      "/api/v1/master/warehouse",
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
    return await callBackend<Warehouse>(event, "/api/v1/master/warehouse", {
      method: "POST",
      body: payload,
    });
  }
});
