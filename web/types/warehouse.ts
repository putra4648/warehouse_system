import type { ContactPerson } from "./contact_person";
import type { Zone } from "./zone";

export type Warehouse = {
  id: string;
  name: string;
  isActive: boolean;
  location: string;
  area: number;
  total: number;
  zones: Zone[];
  contactPersonWarehouse: ContactPerson;
};
