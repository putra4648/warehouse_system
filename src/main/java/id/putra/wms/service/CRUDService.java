package id.putra.wms.service;

public interface CRUDService<D, E extends RuntimeException> {

    D getDataById(String id);

    void add(D dto) throws E;

    void update(D dto) throws E;

    void delete(String id) throws E;
}
