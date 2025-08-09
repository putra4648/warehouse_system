package id.putra.wms.service;

public interface CRUDService<Form, AppException extends RuntimeException> {

    // public List<Form> getAll() throws AppException;

    Form getDataById(String id);

    void add(Form form) throws AppException;

    void update(Form form) throws AppException;

    void delete(String id) throws AppException;
}
