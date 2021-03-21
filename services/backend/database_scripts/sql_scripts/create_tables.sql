CREATE TABLE IF NOT EXISTS nutrients(
ID integer PRIMARY KEY,
calories integer NOT NULL,
carbohydrates integer NOT NULL,
proteins integer NOT NULL,
fats integer NOT NULL,
saturated_fats integer NOT NULL,
fiber integer NOT NULL,
sugar integer NOT NULL);

CREATE TABLE IF NOT EXISTS time_total(
ID integer PRIMARY KEY,
prep_time integer,
cook_time integer,
total_time integer);

CREATE TABLE IF NOT EXISTS recipes(
ID integer PRIMARY KEY ,
ID_nutrients integer NOT NULL,
ID_time_total integer NOT NULL,
name text NOT NULL,
description text NOT NULL,
image blob NOT NULL,
keywords text NOT NULL,
instructions text NOT NULL,
FOREIGN KEY(ID_nutrients) REFERENCES nutrients(ID),
FOREIGN KEY(ID_time_total) REFERENCES time_total(ID) );

CREATE TABLE IF NOT EXISTS diet_plan(
ID integer PRIMARY KEY,
from_date text NOT NULL,
to_date text NOT NULL,
target_calories integer NOT NULL);

CREATE TABLE IF NOT EXISTS user_diets(
ID integer PRIMARY KEY,
calories integer NOT NULL,
bmi integer NOT NULL);

CREATE TABLE IF NOT EXISTS activity_type(
ID integer PRIMARY KEY,
name text NOT NULL);

CREATE TABLE IF NOT EXISTS user_details(
ID integer PRIMARY KEY,
ID_activity_type integer NOT NULL,
age integer NOT NULL,
height integer NOT NULL,
weight integer NOT NULL,
FOREIGN KEY (ID_activity_type) REFERENCES activity_type (ID) );