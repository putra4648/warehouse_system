import { callBackend } from "../utils/api";
import type PaginationResponse from "../utils/pagination";
import type { Customer } from "~~/types/customer";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

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
