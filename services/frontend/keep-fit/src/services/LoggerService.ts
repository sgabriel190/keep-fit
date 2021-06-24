class LoggerService{
    formatLog(className: String, msg: String): void{
        console.log(`[${className} ${new Date().toTimeString().split(" ")[0]}] - ${msg}`);
    }
}

const loggerService = new LoggerService();
export default loggerService;
