function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8001/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data); // Inspect the response structure
        if (data.token) {
            localStorage.setItem("token", data.token);
            window.location.href = "home.html";
        } else {
            alert("Invalid credentials");
        }
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
        alert('An error occurred while logging in');
    });
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}

function checkAuth() {
    if (!localStorage.getItem("token")) {
        window.location.href = "login.html";
    }
}
