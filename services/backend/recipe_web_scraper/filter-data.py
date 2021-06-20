import json
import re
import sys
from typing import List, Dict
from helper_functions import MeasureTime

READ_FILE_LOCATION = './result/bulk_data.json'
WRITE_FILE_LOCATION = './result/filtered_data.json'


def read_data(filename: str) -> List[Dict]:
    file = open(filename, 'r')
    tmp = file.read()
    file.close()
    return json.loads(tmp)


def filter_dict(dict_: Dict) -> Dict:
    searched_keys = ['name', 'description', 'image', 'datePublished', 'prepTime', 'cookTime', 'totalTime',
                     'recipeIngredient', 'recipeInstructions', 'recipeCategory', 'keywords', 'nutrition', '@id']
    tmp = {token: dict_[token] for token in searched_keys if token in dict_}
    tmp['nutrition'].pop('@type')
    if isinstance(tmp['image'], dict):
        tmp['image'] = [tmp['image']['url']]
    tmp['recipeIngredient'] = list(filter(lambda x: re.compile("[0-9]+").match(x.split(" ")[0][0]),
                                          tmp['recipeIngredient']))
    tmp['recipeInstructions'] = list(map(lambda x: x.pop('text'), tmp['recipeInstructions']))
    tmp['source'] = tmp['@id']
    tmp.pop('@id')
    return tmp


def filter_data(data: List[Dict]) -> List[Dict]:
    tmp = list(filter(lambda x: 'nutrition' in x, data))
    return list(map(lambda item: filter_dict(item), tmp))


def write_data(filename: str, data: List[Dict]) -> None:
    file = open(filename, 'w')
    file.writelines(json.dumps(data, indent=4))
    file.close()


def main() -> None:
    data = read_data(READ_FILE_LOCATION)
    filtered_data = filter_data(data)
    print("[INFO] - Recipe count: {}".format(len(filtered_data)))
    write_data(WRITE_FILE_LOCATION, filtered_data)


if __name__ == "__main__":
    mt = MeasureTime.MeasureTime()
    mt.start()
    main()
    mt.stop()
    print(f"[INFO] - script executed in {mt.get_seconds():0.2f} seconds.")
    sys.exit(0)
