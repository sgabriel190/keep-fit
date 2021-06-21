CREATE TABLE IF NOT EXISTS plan(
                                   ID integer PRIMARY KEY,
                                   ID_user integer NOT NULL,
                                   plan_days integer NOT NULL,
                                   description text,
                                   CONSTRAINT unique_constr_meal_user UNIQUE (ID_user)
);

CREATE TABLE IF NOT EXISTS menu(
                                   ID integer PRIMARY KEY,
                                   ID_plan integer NOT NULL,
                                   day integer NOT NULL,
                                   FOREIGN KEY (ID_plan) REFERENCES plan(ID)
);

CREATE TABLE IF NOT EXISTS meal(
                                   ID integer PRIMARY KEY,
                                   ID_menu integer NOT NULL,
                                   time_of_day text NOT NULL,
                                   FOREIGN KEY (ID_menu) REFERENCES menu(ID)
);

CREATE TABLE IF NOT EXISTS meal_recipe(
                                          ID_meal integer NOT NULL,
                                          ID_recipe integer NOT NULL,
                                          FOREIGN KEY (ID_meal) REFERENCES meal(ID),
                                          CONSTRAINT unique_constr_meal UNIQUE (ID_meal, ID_recipe)
);