from sqlite3 import Connection
from typing import List, Dict

import isodate

from Logger import Logger

logger = Logger()


def execute_script_on_table(connection: Connection, input_file: str) -> None:
    file = open(input_file, 'r')
    connection.executescript(file.read())
    file.close()


def insert_values(connection: Connection, values: tuple) -> None:
    sql_query = 'INSERT INTO recipes(name, description, keywords, instructions) VALUES(?, ?, ?, ?)'
    connection.execute(sql_query, values)
    connection.commit()


def create_activities(connection: Connection) -> None:
    logger.log('Create activity types.')
    values: list = [('Sedentary', 1), ('Light Active', 1.1), ('Active', 1.2), ('Very Active', 1.3)]
    sql_query: str = 'INSERT INTO activity_types(name, calories) VALUES(?, ?)'
    for value in values:
        connection.execute(sql_query, (value[0], value[1]))
        connection.commit()


def create_diet_types(connection: Connection) -> None:
    logger.log('Create diet types.')
    values: list = [('Loss weight', 25), ('Maintenance', 30), ('Gain weight', 35)]
    sql_query: str = 'INSERT INTO diet_types(name, calories) VALUES(?, ?)'
    for value in values:
        connection.execute(sql_query, (value[0], value[1]))
        connection.commit()


def create_genders(connection: Connection) -> None:
    logger.log('Create gender.')
    values: list = ['Male', 'Female']
    sql_query: str = 'INSERT INTO genders(name) VALUES(?)'
    for value in values:
        connection.execute(sql_query, (value, ))
        connection.commit()


def insert_data(connection: Connection, data: List[Dict]) -> None:
    logger.log('Insert data into tables.')
    idx = 1
    list_categories = []
    for item in data:
        ok_time = True
        # Create queries
        query_list = [idx]
        table_list = ['ID']
        if 'prepTime' in item:
            query_list.append(str(isodate.parse_duration(item['prepTime'])))
            table_list.append('prep_time')
        if 'cookTime' in item:
            table_list.append('cook_time')
            query_list.append(str(isodate.parse_duration(item['cookTime'])))
        if 'totalTime' in item:
            table_list.append('total_time')
            query_list.append(str(isodate.parse_duration(item['totalTime'])))
        if len(query_list) == 0:
            ok_time = False
        # Total time TABLE
        sql_query_time_total = 'INSERT INTO time_total{} VALUES({})' \
            .format(str(tuple(table_list) if len(table_list) > 1 else "(" + table_list[0] + ")"),
                    ("?, " * len(query_list))[:-2])
        sql_parameters_time_total = tuple(query_list)

        query_list = [idx]
        table_list = ['ID']
        if 'nutrition' not in item:
            continue
        temp_data = item['nutrition']
        if 'fatContent' in temp_data:
            table_list.append('fats')
            query_list.append(temp_data['fatContent'])
        if 'proteinContent' in temp_data:
            table_list.append('proteins')
            query_list.append(temp_data['proteinContent'])
        if 'carbohydrateContent' in temp_data:
            table_list.append('carbohydrates')
            query_list.append(temp_data['carbohydrateContent'])
        if 'saturatedFatContent' in temp_data:
            table_list.append('saturated_fats')
            query_list.append(temp_data['saturatedFatContent'])
        if 'fiberContent' in temp_data:
            table_list.append('fibers')
            query_list.append(temp_data['fiberContent'])
        if 'sugarContent' in temp_data:
            table_list.append('sugars')
            query_list.append(temp_data['sugarContent'])
        # Macronutrients TABLE
        sql_query_macronutrients = 'INSERT INTO macronutrients{} VALUES({})' \
            .format(str(tuple(table_list) if len(table_list) > 1 else "(" + table_list[0] + ")"),
                    ("?, " * len(query_list))[:-2])
        sql_parameters_macronutrients = tuple(query_list)

        # Nutrients TABLE
        if 'calories' in temp_data:
            sql_query_nutrients = 'INSERT INTO nutrients(ID, calories, ID_macronutrients) VALUES(?, ?, ?)'
            sql_parameters_nutrients = (idx, float(temp_data['calories'].split(" ")[0]), idx)
        else:
            continue

        # Recipes TABLE
        if ok_time:
            sql_query_recipes = 'INSERT INTO recipes(ID, ID_nutrients, ID_time_total, name, description, keywords) ' \
                                'VALUES(?, ?, ?, ?, ?, ?)'
            sql_parameters_recipes = (idx, idx, idx, item['name'], item['description'], item['keywords'])
        else:
            sql_query_recipes = 'INSERT INTO recipes(ID, ID_nutrients, name, description, keywords) ' \
                                'VALUES(?, ?, ?, ?, ?, ?)'
            sql_parameters_recipes = (idx, idx, item['name'], item['description'], item['keywords'])

        # Execute queries
        if ok_time:
            connection.execute(sql_query_time_total, sql_parameters_time_total)
        connection.execute(sql_query_macronutrients, sql_parameters_macronutrients)
        connection.execute(sql_query_nutrients, sql_parameters_nutrients)
        connection.execute(sql_query_recipes, sql_parameters_recipes)

        # Images TABLE
        for token in item['image']:
            sql_query_image = 'INSERT INTO images(image_path, ID_recipe) ' \
                              'VALUES(?, ?)'
            sql_parameters_image = (item['name'] + "/" + token.split("/")[-1], idx)
            connection.execute(sql_query_image, sql_parameters_image)

        # Images TABLE
        for token in item['recipeIngredient']:
            sql_query_image = 'INSERT INTO ingredients(name, ID_recipe) ' \
                              'VALUES(?, ?)'
            sql_parameters_image = (token, idx)
            connection.execute(sql_query_image, sql_parameters_image)

        # Instructions TABLE
        for token in item['recipeInstructions']:
            sql_query_instruction = 'INSERT INTO instructions(instruction, ID_recipe) ' \
                                    'VALUES(?, ?)'
            sql_parameters_instruction = (token, idx)
            connection.execute(sql_query_instruction, sql_parameters_instruction)
        # Categories TABLE
        if type(item['recipeCategory']) == list:
            tmp_cat = item['recipeCategory']
        else:
            tmp_cat = list(map(lambda x: x.strip(), item['recipeCategory'].split(",")))
        for token in tmp_cat:
            if token in list_categories:
                idx_cat = list_categories.index(token)
            else:
                list_categories.append(token)
                idx_cat = len(list_categories)
                sql_query_category = 'INSERT INTO categories(ID, category) VALUES (?, ?)'
                sql_parameters_category = (idx_cat, token)
                connection.execute(sql_query_category, sql_parameters_category)

            sql_query_recipe_to_cat = 'INSERT INTO recipe_to_category(ID_recipe, ID_category) VALUES(?, ?)'
            sql_parameters_recipe_to_cat = (idx, idx_cat)
            connection.execute(sql_query_recipe_to_cat, sql_parameters_recipe_to_cat)

        idx += 1
    connection.commit()
