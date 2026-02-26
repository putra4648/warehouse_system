import type { Rack } from "~~/types/rack";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    const result = await callBackend<Rack>(event, `/api/v1/master/rack/${id}`, {
      method: "GET",
    });
    return result;
  }
});
