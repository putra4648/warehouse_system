import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend(event, "/api/v1/inbound/receiving", {
      method: "POST",
      body,
    });
  }
});
