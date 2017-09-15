function makeEditablem() {
    $(".delete").click(function () {
        deleteRowm($(this).attr("id"));
    });

    $("#detailsFormm").submit(function () {
        savem();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNotym(event, jqXHR, options, jsExc);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function addm() {
    $("#detailsFormm").find(":input").val("");
    $("#editRowm").modal();
}

function deleteRowm(id) {
    $.ajax({
        url: mealAjaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTablem();
            successNotym("Deleted");
        }
    });
}

function updateTablem() {
    $.get(mealAjaxUrl, function (data) {
        datatableApim.clear().rows.add(data).draw();
    });
}

function savem() {
    var form = $("#detailsFormm");
    $.ajax({
        type: "POST",
        url: mealAjaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editRowm").modal("hide");
            updateTablem();
            successNotym("Saved");
        }
    });
}

var failedNote;

function closeNotym() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNotym(text) {
    closeNotym();
    new Noty({
        text: "<span class='glyphicon glyphicon-ok'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNotym(event, jqXHR, options, jsExc) {
    closeNotym();
    failedNote = new Noty({
        text: "<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}