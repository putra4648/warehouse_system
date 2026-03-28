export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "GET") {
    const query = getQuery(event);
    return await callBackend(event, "/api/v1/inbound/po", {
      method: "GET",
      query,
    });
  }

  if (method === "PATCH") {
    const body = await readBody(event);
    return await callBackend(event, `/api/v1/inbound/po`, {
      method: "PATCH",
      body,
    });
  }

  if (method === "PUT") {
    const body = await readBody(event);
    return await callBackend(event, `/api/v1/inbound/po`, {
      method: "PUT",
      body,
    });
  }

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend(event, "/api/v1/inbound/po", {
      method: "POST",
      body,
    });
  }
});
