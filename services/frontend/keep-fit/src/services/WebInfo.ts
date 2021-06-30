import axios from "axios";

const info = {
    HOST_IMAGE: "http://localhost:2025/api/backend/nutrition/image/",
    httpClient: axios.create({
            baseURL: `http://localhost:2025/api/backend`
        }
    )
}
export default info;
