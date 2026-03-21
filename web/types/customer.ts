export type ContactPersonCustomer = {
  id: number | null;
  name: string;
  email: string;
  phone: string;
  address: string;
};

export type Customer = {
  id: number | null;
  name: string;
  code: string;
  is_active: boolean;
  contactPerson?: ContactPersonCustomer;
};
