from datetime import datetime


class Logger:
    """
        Logger class is a singleton instance and uses almost all class methods to keep consistency across logging in
        multiple procedures or location where the logger is called.
    """
    __instance = None
    __file = None

    def __new__(cls, file_location: str = './logger/log.info'):
        if cls.__instance is None:
            cls.__instance = object.__new__(cls)
            cls.__file = open(file_location, 'w+')
        return cls.__instance

    @classmethod
    def log(cls, info: str, is_error=False) -> None:
        current_time = datetime.now().strftime("%H:%M:%S")
        if is_error:
            cls.__file.write('{} - [ERROR] - {}\n'.format(current_time, info))
        else:
            cls.__file.write('{} - [INFO] - {}\n'.format(current_time, info))

    @classmethod
    def __del__(cls):
        cls.__file.close()
