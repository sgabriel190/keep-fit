from datetime import datetime


class Logger:
    def __init__(self, file_location: str = './logger/log.info'):
        self.__file = open(file_location, 'w+')

    def log(self, info: str, is_error=False) -> None:
        current_time = datetime.now().strftime("%H:%M:%S")
        if is_error:
            self.__file.write('{} - [ERROR] - {}\n'.format(current_time, info))
        else:
            self.__file.write('{} - [INFO] - {}\n'.format(current_time, info))

    def __del__(self):
        self.__file.close()
