import sqlite3
import sys

from Logger import Logger
from database_connector.sqlite_connector_methods import execute_script_on_table, create_activities, insert_data, \
    create_diet_types, create_genders
from io_helper.io_methods import read_file

FILENAME_SOURCE = './data_source/filtered_data.json'
logger = Logger()


def create_database_nutrition() -> None:
    conn = sqlite3.connect('nutrition.db')
    logger.log('Connected to the NUTRITION database successfully.')
    files = {
        'delete': './sql_scripts/nutrition/delete_tables_nutrition.sql',
        'create': './sql_scripts/nutrition/create_tables_nutrition.sql',
        'insert': './sql_scripts/nutrition/insert_data_nutrition.sql'
    }
    data = read_file(FILENAME_SOURCE)
    execute_script_on_table(conn, files['delete'])
    logger.log('Deleted successfully all the tables in database NUTRITION.')
    execute_script_on_table(conn, files['create'])
    logger.log('Created successfully all the tables in database NUTRITION.')
    create_activities(conn)
    create_diet_types(conn)
    create_genders(conn)
    insert_data(conn, data)
    execute_script_on_table(conn, files['insert'])
    logger.log('Inserted successfully data in tables in database NUTRITION.')
    conn.close()
    logger.log('Closed the NUTRITION database connection successfully.')


def create_database_user() -> None:
    conn = sqlite3.connect('user.db')
    logger.log('Connected to the USER database successfully.')
    files = {
        'delete': './sql_scripts/user/delete_tables_user.sql',
        'create': './sql_scripts/user/create_tables_user.sql',
        'insert': './sql_scripts/user/insert_data_user.sql'
    }
    execute_script_on_table(conn, files['delete'])
    logger.log('Deleted successfully all the tables in database USER.')
    execute_script_on_table(conn, files['create'])
    logger.log('Created successfully all the tables in database USER.')
    execute_script_on_table(conn, files['insert'])
    logger.log('Inserted successfully data in tables in database USER.')
    conn.close()
    logger.log('Closed the USER database connection successfully.')


def create_database_plan() -> None:
    conn = sqlite3.connect('plan.db')
    logger.log('Connected to the PLAN database successfully.')
    files = {
        'delete': './sql_scripts/plan/delete_tables_plan.sql',
        'create': './sql_scripts/plan/create_tables_plan.sql'
    }
    execute_script_on_table(conn, files['delete'])
    logger.log('Deleted successfully all the tables in database PLAN.')
    execute_script_on_table(conn, files['create'])
    logger.log('Created successfully all the tables in database PLAN.')
    conn.close()
    logger.log('Closed the PLAN database connection successfully.')


def main() -> None:
    global logger
    try:
        create_database_nutrition()
        create_database_user()
        create_database_plan()
    except Exception as exc:
        logger.log(str(exc), is_error=True)
    finally:
        del logger


if __name__ == "__main__":
    main()
    sys.exit(0)
