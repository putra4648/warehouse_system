export interface SalesOrder {
  id: number | null;
  soNumber: string;
  orderDate: string;
  status: string;
  customerId: number | null;
  salesOrderLines: SalesOrderLine[];
}

export interface SalesOrderLine {
  id: number | null;
  productId: number;
  quantity: number;
  salesOrderId: number | null;
}

export interface PickingTask {
  id: number | null;
  salesOrderLineId: number;
  status: string;
}

export interface Packing {
  id: number | null;
  packageNumber: string;
  weight: number;
  dimension: string;
  shipmentIds: number[];
}

export interface Shipment {
  id: number | null;
  shipmentNumber: string;
  shipDate: string;
  status: string;
  packingId: number | null;
  carrierId: number | null;
}

export interface Carrier {
  id: number | null;
  name: string;
  serviceType: string;
}

// Keeping legacy type for compatibility if needed, but we should use the new ones
export interface Outbound {
    id: string | number;
    customer?: string;
    destination?: string;
    date: string;
    quantity: number;
    status: string;
    soNumber?: string;
}
