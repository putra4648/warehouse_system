import type { Product } from "~~/types/product";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    const result = await callBackend<Product>(
      event,
      `/api/v1/master/product/${id}`,
      {
        method: "GET",
      },
    );
    return result;
  }
});
