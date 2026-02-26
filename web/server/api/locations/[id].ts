import type { Location } from "~~/types/location";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    const result = await callBackend<Location>(
      event,
      `/api/v1/master/location/${id}`,
      {
        method: "GET",
      },
    );
    return result;
  }
});
