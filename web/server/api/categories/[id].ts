import type { Category } from "~~/types/product";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    const result = await callBackend<Category>(
      event,
      `/api/v1/master/category/${id}`,
      {
        method: "GET",
      },
    );
    return result;
  }
});
