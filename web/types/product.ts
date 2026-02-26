export type Category = {
  id: string;
  name: string;
  description: string;
};

export type Product = {
  id: string;
  name: string;
  description: string;
  minStock: number;
  maxStock: number;
  quantity: number;
  isActive: boolean;
  category: Category;
};
