function cal(a,cartItemId , cal) {
    const bb = $("button[name='cal"+cal+a +"']");
        type = bb.attr('data-type');
        var input = $("input[id='" + a + "']");
        var currentVal = parseInt(input.val());
        if (!isNaN(currentVal)) {
            if (type === 'minus') {

                if (currentVal > input.attr('min')) {
                    input.val(currentVal - 1).change();
                    changeQuantity(cartItemId,currentVal - 1,a);
                }
                if (parseInt(input.val()) === input.attr('min')) {
                    bb.attr('disabled', true);
                }

            } else if (type === 'plus') {

                if (currentVal < input.attr('max')) {
                    input.val(currentVal + 1).change();
                    changeQuantity(cartItemId,currentVal + 1,a);
                }
                if (parseInt(input.val()) === input.attr('max')) {
                    bb.attr('disabled', true);
                }

            }
        } else {
            input.val(0);
        }
    }
$('.input-number').focusin(function () {
    $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function () {

    minValue = parseInt($(this).attr('min'));
    maxValue = parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());

    name = $(this).attr('name');
    if (valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='" + name + "']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if (valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='" + name + "']").removeAttr('disabled')
    } else {
        alert('Sorry, the maximum value was reached');
        $(this).val($(this).data('oldValue'));
    }


});
$(".input-number").keydown(function (e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
            // Allow: Ctrl+A
                    (e.keyCode == 65 && e.ctrlKey === true) ||
                    // Allow: home, end, left, right
                            (e.keyCode >= 35 && e.keyCode <= 39)) {
                // let it happen, don't do anything
                return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        });
        /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function changeQuantity(cartItemId,quantity,productid) {
   
    fetch('./quantityitem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: cartItemId + '%'+quantity
    }).then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok. Status: ' + response.status);
                }
                return response.text();
            })
            .then(data => {
                var statusDiv;
                var result = data.toString();
                if (result === "success") {
                   
                var arr = document.getElementsByName("price");
                var arr1 = document.getElementsByName("quantity");
                var total=0;
                
                    for (var i = 0; i < arr.length; i++) {
                        
                        total+=Number(arr[i].innerHTML)*Number(arr1[i].value);
                        
                    }
                    
                    document.getElementById("totali").innerHTML=total;
                    document.getElementById("vat").innerHTML=total*10/100;
                    document.getElementById("tota").innerHTML=total*110/100;
                    return true;
                } else {
                    statusDiv = document.getElementById("errorMessage");
                    statusDiv.innerHTML = result;
                    $('#errorModal').modal('show');
                    return false;
                }
            })
            .catch(error => {
                statusDiv = document.getElementById("errorMessage");
                    statusDiv.innerHTML = error;
                    $('#errorModal').modal('show');
                return false;
            });

}
