export type ContactPersonSupplier = {
  id: string;
  name: string;
  email: string;
  phone: string;
  address: string;
};

export type Supplier = {
  id: string;
  name: string;
  code: string;
  isActive: boolean;
  contactPersonSuppliers: ContactPersonSupplier[];
};
