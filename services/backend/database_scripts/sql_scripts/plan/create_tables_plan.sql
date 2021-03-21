CREATE TABLE IF NOT EXISTS diet_plan(
ID integer PRIMARY KEY,
active_plans integer NOT NULL,
target_calories integer NOT NULL);

CREATE TABLE IF NOT EXISTS user_plan(
ID integer PRIMARY KEY,
ID_user integer NOT NULL,
ID_plan integer NOT NULL);

CREATE TABLE IF NOT EXISTS recipes_plan(
ID integer PRIMARY KEY,
ID_plan integer NOT NULL,
ID_recipe integer NOT NULL,
FOREIGN KEY(ID_plan) REFERENCES user_plan(ID));