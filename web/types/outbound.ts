export interface SalesOrder {
  id: number | null;
  so_number: string;
  order_date: string;
  status: string;
  customer_id: number | null;
  sales_order_lines: SalesOrderLine[];
}

export interface SalesOrderLine {
  id: number | null;
  product_id: number;
  quantity: number;
  sales_order_id: number | null;
}

export interface PickingTask {
  id: number | null;
  sales_order_line_id: number;
  status: string;
}

export interface Packing {
  id: number | null;
  package_number: string;
  weight: number;
  dimension: string;
  shipment_ids: number[];
}

export interface Shipment {
  id: number | null;
  shipment_number: string;
  ship_date: string;
  status: string;
  packing_id: number | null;
  carrier_id: number | null;
}

export interface Carrier {
  id: number | null;
  name: string;
  service_type: string;
}

// Keeping legacy type for compatibility if needed, but we should use the new ones
export interface Outbound {
  id: string | number;
  customer?: string;
  destination?: string;
  date: string;
  quantity: number;
  status: string;
  so_number?: string;
}
