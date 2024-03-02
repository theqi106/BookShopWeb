/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function changeQuantity(productId) {
    var quantity = document.getElementById(productId).value;
    fetch('./quantityitem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: productId + '%'+quantity
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
                    statusDiv = document.getElementById("addCartStatus");
                    statusDiv.innerHTML = "Add cart success!";
                    $('#successModal').modal('show');
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
