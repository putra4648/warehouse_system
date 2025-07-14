function handleTableChange(event) {}

function showModalDelete(sku) {
  const results = table.searchData("sku", "=", sku);

  if (results.length > 0) {
    $("#delete-master-modal").modal();
    $("#sku-delete-modal").html(results[0].sku);
    $("#app-form").find('input[name="sku"]').val(results[0].sku);
    $("#app-form").find('input[name="name"]').val(results[0].name);
    $("#app-form").find('input[name="min_stock"]').val(results[0].min_stock);
    $("#app-form").find('input[name="max_stock"]').val(results[0].max_stock);
  }
}

function showModalEdit(sku) {
  const results = table.searchData("sku", "=", sku);

  if (results.length > 0) {
    $("#app-form").prop("action", "/master/warehouses?action=edit");
    $("#add-master-modal").modal("toggle");
    $("#app-form").find('input[name="sku"]').val(results[0].sku);
    $("#app-form").find('input[name="name"]').val(results[0].name);
    $("#app-form")
      .find('input[name="description"]')
      .val(results[0].description);
    $("#app-form").find('input[name="min_stock"]').val(results[0].min_stock);
    $("#app-form").find('input[name="max_stock"]').val(results[0].max_stock);
  }
}

function deleteHandler() {
  $("#app-form").prop("action", "/master/warehouses?action=delete");
  $("#app-form").trigger("submit");
}
