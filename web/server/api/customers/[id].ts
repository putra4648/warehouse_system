import { callBackend } from "../../utils/api";
import type { Customer } from "~~/types/customer";

export default defineEventHandler(async (event) => {
  const method = getMethod(event);
  const id = getRouterParam(event, "id");

  if (method === "POST") {
    const body = await readBody(event);
    return await callBackend<Customer>(
      event,
      `/api/v1/master/customer/${id}`,
      {
        method: "POST",
        body,
      },
    );
  }

  if (method === "DELETE") {
    return await callBackend<string>(event, `/api/v1/master/customer/${id}`, {
      method: "DELETE",
    });
  }
});
