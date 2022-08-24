window.onload = function () {
    if (!sessionStorage.product) {
        ajax.post(
            '/config/getPublicKey',
            null,
            (response) => {
                console.log(response.data);
                sessionStorage.product = response.data;
            }
        );
    }
    let username = sessionStorage.access;
    if (username) document.getElementById("access").innerHTML = username;
}

function access() {
    ajax.post(
        '/config/test',
        {'num':rsa('Hello World!')},
        ()=>{}
    );
    //window.location.href = "/management";
}