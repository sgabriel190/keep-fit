import axios from "axios";

const info = {
    HOST: "localhost",
    PORT: "2025",
    fetchMethod: (url: string, method: string) => {
        return fetch(url, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': "application/json; charset=utf-8"
            }
        }).then((response) => response.json())
            .then((json) => {
                return json;
            })
            .catch((error) => {
                console.log(error);
            });
    },
    httpClient: axios.create({
            baseURL: `http://localhost:2025/api/backend`
        }
    )
}
export default info;
