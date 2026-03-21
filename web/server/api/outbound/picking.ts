import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend(event, "/api/v1/outbound/picking", {
      method: "POST",
      body,
    });
  }

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend(event, "/api/v1/outbound/picking", {
        method: "GET",
        query,
    });
  }
});
