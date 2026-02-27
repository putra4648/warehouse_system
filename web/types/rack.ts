import type { Location } from "./location";

export type Rack = {
  id: string;
  name: string;
  rows: number;
  cols: number;
  is_active: boolean;
  locations: Location[];
};
