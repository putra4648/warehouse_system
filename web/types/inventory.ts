export type InventoryItem = {
  id: number | string;
  product: string;
  sku: string;
  warehouse: string;
  quantity: number;
  reserved_qty: number;
  lot_number: string;
  expired_date?: Date;
  image: string;
};
