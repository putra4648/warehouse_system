import type { Customer } from "~~/types/customer";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend<PaginationResponse<Customer>>(
      event,
      "/api/v1/master/customer",
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
    return await callBackend<Customer>(event, "/api/v1/master/customer", {
      method: "POST",
      body: payload,
    });
  }
});
