export interface PurchaseOrder {
  id: number | null;
  po_number: string;
  order_date: string;
  status: string;
  supplier_id: number | null;
  purchaseOrderLines: PurchaseOrderLine[];
}

export interface PurchaseOrderLine {
  id: number | null;
  product_id: number;
  quantity: number;
  purchase_order_id: number | null;
}

export interface Receiving {
  id: number | null;
  receiving_number: string;
  received_date: string;
  status: string;
  user_id: number | null;
  purchase_order_id: number | null;
  receiving_lines: ReceivingLine[];
}

export interface ReceivingLine {
  id: number | null;
  product_id: number;
  received_quantity: number;
  lot_number: string;
  expiry_date: string;
  status: string;
  receiving_id: number | null;
}

// Keeping legacy type for compatibility
export interface Inbound {
  id: string | number;
  supplier: string;
  date: string;
  quantity: number;
  status: string;
  po_number?: string;
}
