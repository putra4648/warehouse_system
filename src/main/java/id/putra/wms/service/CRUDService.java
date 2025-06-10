package id.putra.wms.service;

import java.util.List;

public interface CRUDService<Form, AppException extends RuntimeException> {

    public List<Form> getAll() throws AppException;

    public void add(Form form) throws AppException;

    public void update(Form form) throws AppException;

    public void delete(Form form) throws AppException;
}
