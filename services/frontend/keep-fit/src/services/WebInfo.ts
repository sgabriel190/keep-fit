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
    fetchMethodBody: (url: string, method: string, data: object) => {
        return fetch(url, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': "application/json; charset=utf-8"
            },
            body: JSON.stringify(data)
        }).then((response) => response.json())
            .then((json) => {
                return json;
            })
            .catch((error) => {
                console.log(error);
            });
    }
}
export default info;
