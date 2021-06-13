from typing import Any


class Response:
    __code: int
    __message: str
    __data: Any

    def __init__(self, code: int, message: str, data: Any):
        self.__code = code
        self.__data = data
        self.__message = message

    def to_dict(self) -> dict:
        return { "code": self.__code, "message": self.__message, "data": self.__data }
