/**
 * 
 */
function ajaxPost(url, ids, success, error) {
    var data = {};
    jQuery.each(ids, function(i, id) {
        if (id.indexOf("[]") >= 0) {
            var name = id.replace('[]', '');
            console.log(name);
            jQuery('input[name="' + id + '"]').each(function(i, e) {
                console.log(e);
                data[name + '[' + i + ']'] = jQuery(e).val();
            });
            
        } else {
            data[id] = jQuery('#' + id).val();
        }
    });
    console.log(data);
    jQuery.ajax({
        'url': url,
        'type': 'POST',
        'data': data,
        'success': success,
        'error': error,
    });
}

function ajaxGet(url, ids, success, error) {
    var data = {};
    jQuery.each(ids, function(i, id) {
        data[id] = jQuery('#' + id).val();
    });
    jQuery.ajax({
        'url': url,
        'type': 'GET',
        'data': data,
        'success': success,
        'error': error,
    });
}