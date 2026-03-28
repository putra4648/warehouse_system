import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const method = event.method;

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend(event, "/api/v1/outbound/packing", {
      method: "POST",
      body,
    });
  }
});
