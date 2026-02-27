import type { Inbound } from "./inbound";

export type Outbound = Partial<Inbound> & {
  customer: string;
  destination: string;
};
