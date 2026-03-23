import { callBackend } from "../../../utils/api";

export default defineEventHandler(async (event) => {
  const { id } = getRouterParams(event);
  const method = event.method;

  if (method === "GET") {
    return await callBackend(event, `/api/v1/outbound/so/${id}`, {
      method: "GET",
    });
  }
});
