package id.putra.wms.service;

public interface CRUDService<T> {

    void add(T dto);

    void update(T dto);

    void delete(String id);
}
