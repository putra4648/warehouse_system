import type { ContactPerson } from "./contact_person";
import type { Zone } from "./zone";

export type Warehouse = {
  id: string;
  name: string;
  is_active: boolean;
  location: string;
  area: number;
  total: number;
  zones: Zone[];
  contact_person_warehouse: ContactPerson;
};
