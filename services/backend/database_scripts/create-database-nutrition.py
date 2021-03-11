import sqlite3
import sys

from Logger import Logger
from database_connector.sqlite_connector_methods import drop_table, create_tables, create_activities, insert_data
from database_metadata import DATABASE_NAME
from io_helper.io_methods import read_file

FILENAME = './data_source/filtered_data.json'
logger = Logger()


def main() -> None:
    global logger
    try:
        conn = sqlite3.connect(DATABASE_NAME)
        logger.log('Connected to the database successfully.')
        data = read_file(FILENAME)
        drop_table(conn)
        create_tables(conn)
        create_activities(conn)
        insert_data(conn, data)
        conn.close()
        logger.log('Closed the database connection successfully.')
    except Exception as exc:
        logger.log(str(exc), is_error=True)
    finally:
        del logger


if __name__ == "__main__":
    main()
    sys.exit(0)
