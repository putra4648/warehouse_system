import { TabulatorFull as Tabulator, type Options } from "tabulator-tables";

const url = "https://jsonplaceholder.typicode.com/photos";

export default function useTabulator(
    tableRef: Ref<HTMLElement | null>,
    columns: Options["columns"]
) {
    const tabulatorInstance = ref<Tabulator | null>(null);

    onMounted(() => {
        if (tableRef.value) {
            const options: Options = {
                columns,
                layout: "fitColumns",
                placeholder: "No Data Available",
                reactiveData: true,
                progressiveLoad: "scroll",
                pagination: true,
                paginationMode: "remote",
                paginationSize: 10,
                paginationSizeSelector: [10, 25, 50, 100],
                ajaxURL: url,
                ajaxResponse: function (url, params, response) {
                    return response;
                },
                paginationCounter: "rows",
            };
            tabulatorInstance.value = new Tabulator(tableRef.value, options);

        }
    });


    function setData<Type>(data: Type[]) {
        tabulatorInstance.value?.setData(data);
    }

    return {
        tabulatorInstance,
        setData,
    };
}
