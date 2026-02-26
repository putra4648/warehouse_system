import type { Location } from "~~/types/location";
import type PaginationResponse from "../utils/pagination";
import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend<Location>(
      event,
      "/api/v1/master/location",
      {
        method: "POST",
        body,
      },
    );
    return result;
  }

  if (method === "PATCH") {
    const body = await readBody(event);
    const result = await callBackend<string>(event, "/api/v1/master/location", {
      method: "PATCH",
      body,
    });
    return result;
  }

  if (method === "DELETE") {
    const body = await readBody(event);
    const result = await callBackend<string>(event, "/api/v1/master/location", {
      method: "DELETE",
      body,
    });
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend<PaginationResponse<Location>>(
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
  return result;
});
