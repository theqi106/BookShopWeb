

function addToCart(productId) {
    fetch('./AddToCartController', {
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
