import json
from typing import List, Dict


def read_file(filename: str) -> List[Dict]:
    file = open(filename, 'r+')
    result = json.loads(file.read())
    file.close()
    return result
