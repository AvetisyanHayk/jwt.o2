/*
 * 
 */


var toggleControls = function (e) {
    var $area = $(this).closest('[data-area=toggle]');
    var address = $area.attr('data-toggle-controls');
    var $ref = $('[data-toggle-control=' + address + ']');
    $ref.prop("disabled", false);
};

var initDataToggleControls = function () {
    var $toggleAreas = $('[data-area=toggle]');
    $toggleAreas.on("change", "input[type=checkbox]", toggleControls);
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

var initDataSelectControls = function () {
    $('[data-select]').on("click", selectAllSlaves);
    $('[data-unselect]').on("click", unselectAllSlaves);
};

var init = function () {
    initDataToggleControls();
    initDataSelectControls();
};

$(document).ready(init);
