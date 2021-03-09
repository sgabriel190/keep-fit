DATABASE_NAME = 'nutrition.db'

create_tables_list = [
    'CREATE TABLE IF NOT EXISTS nutrients('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'calories integer NOT NULL, '
    'carbohydrates integer NOT NULL, '
    'proteins integer NOT NULL, '
    'fats integer NOT NULL, '
    'saturated_fats integer NOT NULL, '
    'fiber integer NOT NULL, '
    'sugar integer NOT NULL)',

    'CREATE TABLE IF NOT EXISTS time_total('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'prep_time integer NOT NULL, '
    'cook_time integer NOT NULL, '
    'total_time integer NOT NULL)',

    'CREATE TABLE IF NOT EXISTS recipes('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'ID_nutrients integer NOT NULL, '
    'ID_time_total integer NOT NULL, '
    'name text NOT NULL, '
    'description text NOT NULL, '
    'image blob NOT NULL, '
    'keywords text NOT NULL, '
    'instructions text NOT NULL, '
    'FOREIGN KEY(ID_nutrients) REFERENCES nutrients(ID), '
    'FOREIGN KEY(ID_time_total) REFERENCES time_total(ID) )',

    'CREATE TABLE IF NOT EXISTS diet_plan('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'from_date text NOT NULL, '
    'to_date text NOT NULL, '
    'target_calories integer NOT NULL)',

    'CREATE TABLE IF NOT EXISTS user_diets('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'calories integer NOT NULL, '
    'bmi integer NOT NULL)',

    'CREATE TABLE IF NOT EXISTS activity_type('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'name text NOT NULL)',

    'CREATE TABLE IF NOT EXISTS user_details('
    'ID integer PRIMARY KEY AUTOINCREMENT, '
    'ID_activity_type integer NOT NULL, '
    'age integer NOT NULL, '
    'height integer NOT NULL, '
    'weight integer NOT NULL, '
    'FOREIGN KEY (ID_activity_type) REFERENCES activity_type (ID) )'
]

delete_tables_list = [
    'DROP TABLE IF EXISTS nutrients',
    'DROP TABLE IF EXISTS time_total',
    'DROP TABLE IF EXISTS recipes',
    'DROP TABLE IF EXISTS diet_plan',
    'DROP TABLE IF EXISTS user_diets',
    'DROP TABLE IF EXISTS activity_type',
    'DROP TABLE IF EXISTS user_details'
]
