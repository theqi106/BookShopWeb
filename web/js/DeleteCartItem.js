

function deleteCartItem(productId) {
    fetch('./deletecart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: productId
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
                    statusDiv = document.getElementById("deleteCartStatus");
                    statusDiv.innerHTML = "Delete success!";
                    $('#successModal').modal('show');
                    document.getElementById('Product'+productId).remove();
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



