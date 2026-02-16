export type Rack = {
  id: string;
  name: string;
  rows: number;
  cols: number;
  isActive: boolean;
  locations: Location[];
};
