var mealAjaxUrl = "ajax/meals/";
var datatableApim;

$(function () {
    datatableApim = $("#datatablem").DataTable({
        "paging":false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "data": "Actions",
                "defaultContent": "Edit",
                "orderable": false
            }
            // {
            //     "defaultContent": "Delete",
            //     "orderable": false
            // }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditablem();
})