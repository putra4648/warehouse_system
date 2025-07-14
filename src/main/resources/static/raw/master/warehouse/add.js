$(document).ready(function () {
  console.log("Ready");
});

const zoneTable = new Tabulator("#zone-table", {
  layout: "fitColumns",
  placeholder: "No Data Available",
  dataTree: true,
  dataTreeChildField: "racks",
  dataTreeStartExpanded: true,
  data: [
    {
      id: "zone-01",
      name: "Zone 01",
      racks: [
        {
          rack_id: "rack-01",
          rack_name: "Rack 01",
        },
      ],
    },
  ],
  columns: [
    {
      title: "ID",
      field: "id",
      minWidth: 100,
      editor: "input",
    },
    {
      title: "Name",
      field: "name",
      editor: "input",
      minWidth: 120,
    },
    {
      title: "Type",
      field: "type",
      editor: "input",
      minWidth: 120,
    },
    {
      title: "",
      field: "",
      headerSort: false,
      maxWidth: 120,
      cellClick: (e, cell) => {
        const row = cell.getRow();
        if (
          (row.getTreeParent() == false) & (row.getTreeChildren().length > 0) ||
          row.getTreeParent() == false
        ) {
          row.addTreeChild({ rack_id: "", rack_name: "" });
        }
      },
      formatter: (cell) => {
        const row = cell.getRow();
        if (
          (row.getTreeParent() == false) & (row.getTreeChildren().length > 0) ||
          row.getTreeParent() == false
        ) {
          return `<div>
                    <i class='fas fa-add'></i>
                    <span>Add Rack</span>
                    </div>`;
        }

        return "";
      },
    },
    {
      title: "Rack ID",
      field: "rack_id",
      editor: "input",
      minWidth: 120,
      editable: (cell) => {
        const row = cell.getRow();
        if (
          (row.getTreeParent() == false) & (row.getTreeChildren().length > 0) ||
          row.getTreeParent() == false
        ) {
          return false;
        }

        return true;
      },
    },
    {
      title: "Rack Name",
      field: "rack_name",
      editor: "input",
      minWidth: 120,
      editable: (cell) => {
        const row = cell.getRow();
        if (
          (row.getTreeParent() == false) & (row.getTreeChildren().length > 0) ||
          row.getTreeParent() == false
        ) {
          return false;
        }

        return true;
      },
    },
    // {
    //   title: "Rack Rows",
    //   field: "rack_rows",
    //   editor: "number",
    //   minWidth: 120,
    //   editable: (cell) => {
    //     const row = cell.getRow();
    //     if (
    //       (row.getTreeParent() == false) &
    //         (row.getTreeChildren().length > 0) ||
    //       row.getTreeParent() == false
    //     ) {
    //       return false;
    //     }
    //
    //     return true;
    //   },
    //   editorParams: {
    //     min: 1,
    //   },
    // },
    // {
    //   title: "Rack Cols",
    //   field: "rack_cols",
    //   editor: "input",
    //   minWidth: 120,
    //   editable: (cell) => {
    //     const row = cell.getRow();
    //     if (
    //       (row.getTreeParent() == false) &
    //         (row.getTreeChildren().length > 0) ||
    //       row.getTreeParent() == false
    //     ) {
    //       return false;
    //     }
    //
    //     return true;
    //   },
    //   editorParams: {
    //     min: 1,
    //   },
    // },
    {
      title: "Actions",
      headerSort: false,
      frozen: true,
      responsive: 0,
      minWidth: 100,
      formatter: (cell) => {
        return ` <i class='fas fa-trash'></i>`;
      },
      cellClick: (e, cell) => {
        const row = cell.getRow();
        row.getTreeChildren().forEach((child) => child.delete());
        row.delete();
      },
    },
  ],
});

zoneTable.on("dataChanged", function (data) {
  $("input[name='zone_json_string']").val(JSON.stringify(data));
});

zoneTable.on("tableBuilt", function () {
  $("input[name='zone_json_string']").val(JSON.stringify(zoneTable.getData()));
});

function deleteRow(index = 0) {
  zoneTable.deleteRow(index);
}

function addZone(event) {
  event.preventDefault();
  zoneTable.addData({});
}
