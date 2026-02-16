export default interface PaginationResponse<T> {
  data: T[];
  meta: Meta;
}

interface Meta {
  page: number;
  size: number;
  total: number;
  last_page: number;
}
