/**
 * 
 */
var ajaxError = false;
function params(ids) {
    var data = {};
    jQuery.each(ids, function(i, id) {
        if (id.indexOf("[]") >= 0) {
            var name = id.replace('[]', '');
            jQuery('input[name="' + id + '"]').each(function(i, e) {
                data[name + '[' + i + ']'] = jQuery(e).val();
            });
        } else {
            var _id = jQuery('#' + id);
            var required = _id.prop('required');
            if (required && !_id.val()) {
                alert(_id.attr('placeholder') + '不能为空');
                ajaxError = true;
            }
            data[_id.attr('name') || id] = _id.val();
        }
    });
    return data;
}

function ajaxPost(url, ids, success, error) {
    var data = params(ids);
    if (ajaxError) {
        ajaxError = false;
        return;
    }

    jQuery.ajax({
        'url': url,
        'type': 'POST',
        'data': data,
        'success': success,
        'error': error,
    });
}

function ajaxGet(url, ids, success, error) {
    var data = params(ids);
    if (ajaxError) {
        ajaxError = false;
        return;
    }
    jQuery.ajax({
        'url': url,
        'type': 'GET',
        'data': data,
        'success': success,
        'error': error,
    });
}

function ajaxDelete(url, ids, success, error) {
    var data = params(ids);
    if (ajaxError) {
        ajaxError = false;
        return;
    }
    jQuery.ajax({
        'url': url,
        'type': 'DELETE',
        'data': data,
        'success': success,
        'error': error,
    });
}