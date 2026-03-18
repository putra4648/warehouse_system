export interface PurchaseOrder {
  id: number | null;
  poNumber: string;
  orderDate: string;
  status: string;
  supplierId: number | null;
  purchaseOrderLines: PurchaseOrderLine[];
}

export interface PurchaseOrderLine {
  id: number | null;
  productId: number;
  quantity: number;
  purchaseOrderId: number | null;
}

export interface Receiving {
  id: number | null;
  receivingNumber: string;
  receivedDate: string;
  status: string;
  userId: number | null;
  purchaseOrderId: number | null;
  receivingLines: ReceivingLine[];
}

export interface ReceivingLine {
  id: number | null;
  productId: number;
  receivedQuantity: number;
  lotNumber: string;
  expiryDate: string;
  status: string;
  receivingId: number | null;
}

// Keeping legacy type for compatibility
export interface Inbound {
  id: string | number;
  supplier: string;
  date: string;
  quantity: number;
  status: string;
  poNumber?: string;
}
