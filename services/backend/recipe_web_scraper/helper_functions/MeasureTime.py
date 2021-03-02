import time


class MeasureTime:
    def __init__(self):
        self.__start_stamp = 0
        self.__stop_stamp = 0

    def start(self) -> None:
        self.__start_stamp = time.perf_counter()

    def stop(self) -> None:
        self.__stop_stamp = time.perf_counter()

    def get_minutes(self) -> float:
        return (self.__stop_stamp - self.__start_stamp) / 60

    def get_seconds(self) -> float:
        return self.__stop_stamp - self.__start_stamp
