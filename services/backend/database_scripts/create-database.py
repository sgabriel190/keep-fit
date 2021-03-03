import sqlite3
import sys
from sqlite3 import Connection
from database_metadata import create_tables_list, DATABASE_NAME, delete_tables_list


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


def main() -> None:
    conn = sqlite3.connect(DATABASE_NAME)
    drop_table(conn)
    create_tables(conn)
    conn.close()


if __name__ == "__main__":
    main()
    sys.exit(0)
