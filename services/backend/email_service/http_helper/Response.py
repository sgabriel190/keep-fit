from typing import Any


class Response:
    code: int
    message: str
    data: Any

    def __init__(self, code: int, message: str, data: Any):
        self.code = code
        self.data = data
        self.message = message
