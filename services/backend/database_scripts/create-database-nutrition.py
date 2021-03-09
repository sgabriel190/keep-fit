import sqlite3
import sys
from sqlite3 import Connection

from Logger import Logger
from database_metadata import create_tables_list, DATABASE_NAME, delete_tables_list

logger = Logger()


def drop_table(connection: Connection) -> None:
    for sql_query in delete_tables_list:
        connection.execute(sql_query)
        connection.commit()


def create_tables(connection: Connection) -> None:
    for sql_query in create_tables_list:
        connection.execute(sql_query)
        connection.commit()


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


def main() -> None:
    try:
        conn = sqlite3.connect(DATABASE_NAME)
        logger.log('Connected to the database successfully.')
        drop_table(conn)
        create_tables(conn)
        create_activities(conn)
        conn.close()
        logger.log('Closed the database connection successfully.')
    except Exception as exc:
        logger.log(str(exc), is_error=True)
    finally:
        del logger


if __name__ == "__main__":
    main()
    sys.exit(0)
