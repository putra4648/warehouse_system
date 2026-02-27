import type { Product } from "./product";

export type ContactPersonSupplier = {
  id: number | null;
  name: string;
  email: string;
  phone: string;
  address: string;
};

export type ProductSupplierId = {
  product_id: number;
  supplier_id: number | null;
};

export type ProductSupplier = {
  id: ProductSupplierId;
  product: Product;
};

export type Supplier = {
  id: number | null;
  name: string;
  code: string;
  is_active: boolean;
  contact_person_suppliers: ContactPersonSupplier[];
  products: ProductSupplier[];
};
