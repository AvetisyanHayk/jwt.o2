/*
 * 
 */


var toggleControls = function (e) {
    var $area = $(this).closest('[data-area=toggle]');
    var address = $area.attr('data-toggle-controls');
    var $ref = $('[data-toggle-control=' + address + ']');
    $ref.prop("disabled", false);
};



var selectAllSlaves = function (e) {
    var address = $(this).attr("data-select");
    var $slaves = $("[data-slaves=" + address + ']').find("input[type=checkbox]");
    $slaves.prop("checked", true).trigger("change");
};

var unselectAllSlaves = function (e) {
    var address = $(this).attr("data-unselect");
    var $slaves = $("[data-slaves=" + address + ']').find("input[type=checkbox]");
    $slaves.prop("checked", false).trigger("change");
};

var disableToggleControls = function (e) {
    var $form = $(this).closest("form");
    $form[0].reset();
    $form.find("[data-toggle-control]").prop("disabled", true);
};

var initDataToggleControls = function () {
    var $toggleAreas = $('[data-area=toggle]');
    $toggleAreas.on("change", "input[type=checkbox]", toggleControls);
    $("button[type=reset]").on("click", disableToggleControls);
};

var initDataSelectControls = function () {
    $('[data-select]').on("click", selectAllSlaves);
    $('[data-unselect]').on("click", unselectAllSlaves);
};

var showPrintDialog = function(e) {
    e.preventDefault();
    window.print();
};

var init = function () {
    initDataToggleControls();
    initDataSelectControls();
    $(".print").on("click", showPrintDialog);
};

$(document).ready(init);
