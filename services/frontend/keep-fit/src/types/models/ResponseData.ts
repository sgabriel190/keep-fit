type ResponseData<T> = {
    successfulOperation: boolean;
    code: number;
    data: T;
    error: string;
    message: string;
};

export default ResponseData;
