import json
import multiprocessing
import os
import sys
from typing import List, Dict

import requests

READ_FILE_LOCATION = "./result/filtered_data.json"


def read_data(filename: str) -> List[Dict]:
    file = open(filename, 'r')
    tmp = json.loads(file.read())
    file.close()
    return tmp


def write_photo(photo_: bytes, path: str) -> None:
    file = open('{}'.format(path), 'wb+')
    file.write(photo_)
    file.close()


def get_images(images: list, path: str):
    for image_url in images:
        path_photo = path + "/{}".format(image_url.split('/')[-1])
        response = requests.get(image_url, stream=True)
        if response.status_code == 200:
            write_photo(response.content, path_photo)
        else:
            print("[INFO] - Request was not ok for url: {}.".format(image_url))


def main() -> None:
    data = read_data(READ_FILE_LOCATION)
    for recipe_idx in range(0, len(data), 50):
        jobs = []
        for token in range(recipe_idx, recipe_idx + 50):
            if token >= len(data):
                break
            path = "./images/{}".format(data[token]['name'])
            try:
                os.mkdir(path)
            except Exception as exception:
                print("[EXCEPTION] - {}".format(exception))
            process = multiprocessing.Process(target=get_images, args=(data[token]['image'], path, ))
            jobs.append(process)
            process.start()
        for process in jobs:
            process.join()
            print("[INFO] - {} finished.".format(process.name))


if __name__ == "__main__":
    main()
    sys.exit(0)
