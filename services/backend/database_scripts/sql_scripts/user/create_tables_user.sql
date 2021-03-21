CREATE TABLE IF NOT EXISTS users(
ID integer PRIMARY KEY,
username text NOT NULL,
password text NOT NULL,
email text NOT NULL,
ID_user_details int,
ID_diet_plan int);