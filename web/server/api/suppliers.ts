import { callBackend } from "../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    const result = await callBackend(event, "/api/v1/master/supplier", {
      method: "POST",
      body,
    });
    return result;
  }

  const query = getQuery(event);
  const result = await callBackend(event, "/api/v1/master/supplier", {
    method: "GET",
    query: {
      ...query,
      search: query.search || "",
    },
  });
  return result;
});
