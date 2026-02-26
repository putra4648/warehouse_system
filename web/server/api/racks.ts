import type { Rack } from "~~/types/rack";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<string>(event, "/api/v1/master/rack", {
      method: "POST",
      body,
    });
    return result;
  }

  if (method === "PATCH") {
    const body = await readBody(event);
    const result = await callBackend<string>(event, "/api/v1/master/rack", {
      method: "PATCH",
      body,
    });
    return result;
  }

  if (method === "DELETE") {
    const query = getQuery(event);
    const result = await callBackend<string>(event, "/api/v1/master/rack", {
      method: "DELETE",
      query,
    });
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<Rack>>(
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
  return result;
});
