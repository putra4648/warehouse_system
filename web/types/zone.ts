import type { Rack } from "./rack";

export type Zone = {
  id: string;
  name: string;
  code: string;
  is_active: boolean;
  type: string;
  racks: Rack[];
};
