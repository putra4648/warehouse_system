export type InventoryItem = {
  id: number | string;
  product: string;
  sku: string;
  warehouse: string;
  quantity: number;
  reservedQty: number;
  lotNumber: string;
  expiredDate?: Date;
  image: string;
};
