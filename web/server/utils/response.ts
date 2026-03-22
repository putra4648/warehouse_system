export interface PaginationResponse<T> {
  data: T[];
  meta: Meta;
}

interface Meta {
  page: number;
  size: number;
  total: number;
  last_page: number;
}

interface ResponseSchemaAttribute {
  responseCode: string;
  responseMessage: string;
}

export interface ResponseData<T> {
  responseSchema: ResponseSchemaAttribute;
  data: T;
}
