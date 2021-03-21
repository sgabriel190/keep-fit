from sqlite3 import Connection
from typing import List, Dict

import isodate

from Logger import Logger

logger = Logger()


def drop_table(connection: Connection) -> None:
    file = open('sql_scripts/delete_tables.sql', 'r')
    connection.executescript(file.read())
    file.close()


def create_tables(connection: Connection) -> None:
    file = open('sql_scripts/create_tables.sql', 'r')
    connection.executescript(file.read())
    file.close()


def insert_values(connection: Connection, values: tuple) -> None:
    sql_query = 'INSERT INTO recipes(name, description, keywords, instructions) VALUES(?, ?, ?, ?)'
    connection.execute(sql_query, values)
    connection.commit()


def create_activities(connection: Connection) -> None:
    logger.log('Create activity types.')
    values: list = ['Sedentary', 'Light Active', 'Active', 'Very Active']
    sql_query: str = 'INSERT INTO activity_type(name) VALUES(?)'
    for value in values:
        connection.execute(sql_query, (value, ))
        connection.commit()


def insert_data(connection: Connection, data: List[Dict]) -> None:
    logger.log('Insert data into tables.')
    for item in data:
        query_list = []
        table_list = []
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
            continue
        sql_query = 'INSERT INTO time_total{} VALUES({})'\
            .format(str(tuple(table_list) if len(table_list) > 1 else "(" + table_list[0] + ")"), ("?, " * len(query_list))[:-2])
        connection.execute(sql_query, tuple(query_list))
    connection.commit()
