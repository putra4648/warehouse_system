export type Category = {
  id: number;
  name: string;
  description: string;
};

export type Product = {
  id: number;
  name: string;
  description: string;
  min_stock: number;
  max_stock: number;
  quantity: number;
  is_active: boolean;
  category: Category;
};
