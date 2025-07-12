package id.putra.wms.service;

public interface CRUDService<Form, AppException extends RuntimeException> {

    // public List<Form> getAll() throws AppException;

    public Form getDataById(String id);

    public void add(Form form) throws AppException;

    public void update(Form form) throws AppException;

    public void delete(String id) throws AppException;
}
