const URL = "http://localhost:8080/api/auth";

export function login(loginData) {
    console.log(loginData);
    const options = {
        method: "POST",
        body: JSON.stringify(loginData),
    };
    fetch(`${URL}/login`, options)
        .then((res) => {
            if (res.status === 200) {
                return res.json();
            } else {
                return Promise.reject(`Unexpected status code: ${res.status}`);
            }
        })
        .then((data) => {
            console.log(data);
        });
}
