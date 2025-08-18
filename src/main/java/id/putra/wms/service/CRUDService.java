package id.putra.wms.service;

import id.putra.wms.shared.base.dto.form.BaseForm;

public interface CRUDService<F extends BaseForm, E extends RuntimeException> {

    F getDataById(String id);

    void add(F form) throws E;

    void update(F form) throws E;

    void delete(String id) throws E;
}
