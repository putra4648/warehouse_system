import type { Supplier } from "~~/types/supplier";
import { callBackend } from "../../utils/api";

export default defineEventHandler(async (event) => {
  const id = getRouterParam(event, "id");
  const method = getMethod(event);

  if (method === "GET") {
    const result = await callBackend<Supplier>(
      event,
      `/api/v1/master/supplier/${id}`,
      {
        method: "GET",
      },
    );
    return result;
  }

  if (method === "PUT") {
    const body = await readBody(event);
    const result = await callBackend(event, `/api/v1/master/supplier/${id}`, {
      method: "PUT",
      body,
    });
    return result;
  }

  if (method === "DELETE") {
    const result = await callBackend(event, `/api/v1/master/supplier/${id}`, {
      method: "DELETE",
    });
    return result;
  }
});
