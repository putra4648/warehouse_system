import type { Meta } from "~~/server/utils/response";
import type { OrderStatus } from "./enums/order_enum";
import type { Product } from "./product";
import type { ReceivingStatus } from "./enums/receiving_status_enum";
import type { Location } from "./location";

export interface PurchaseOrder {
  id: number | null;
  po_number?: string;
  order_date?: string;
  status?: OrderStatus;
  supplier_id?: number | null;
  purchase_order_lines?: PurchaseOrderLine[];
  meta?: Meta;
}

export interface PurchaseOrderLine {
  id: number | null;
  product: Product;
  quantity: number;
  purchase_order_id: number | null;
  price: number;
}

export interface Receiving {
  id: number | null;
  receiving_number?: string;
  received_date?: string;
  status?: OrderStatus;
  user_id?: number | null;
  purchase_order?: PurchaseOrder;
  receiving_lines?: ReceivingLine[];
  meta?: Meta;
}

export interface ReceivingLine {
  id: number | null;
  product: Product;
  qty?: number;
  qty_received?: number;
  lot_batch?: string;
  expiry_date?: string;
  received_date?: string;
  status?: ReceivingStatus;
  location: Location;
}
