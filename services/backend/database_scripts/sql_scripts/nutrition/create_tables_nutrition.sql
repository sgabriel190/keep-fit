CREATE TABLE IF NOT EXISTS macronutrients(
                                             ID integer PRIMARY KEY,
                                             carbohydrates text,
                                             proteins text,
                                             fats text,
                                             saturated_fats text,
                                             fibers text,
                                             sugars text
);

CREATE TABLE IF NOT EXISTS nutrients(
                                        ID integer PRIMARY KEY,
                                        calories text NOT NULL,
                                        ID_macronutrients integer NOT NULL,
                                        FOREIGN KEY (ID_macronutrients) references macronutrients(ID)
);

CREATE TABLE IF NOT EXISTS time_total(
                                         ID integer PRIMARY KEY,
                                         prep_time text,
                                         cook_time text,
                                         total_time text
);

CREATE TABLE IF NOT EXISTS recipes(
                                      ID integer PRIMARY KEY ,
                                      ID_nutrients integer NOT NULL,
                                      ID_time_total integer,
                                      name text NOT NULL,
                                      description text NOT NULL,
                                      keywords text NOT NULL,
                                      FOREIGN KEY(ID_nutrients) REFERENCES nutrients(ID),
                                      FOREIGN KEY(ID_time_total) REFERENCES time_total(ID)
);

CREATE TABLE IF NOT EXISTS images(
                                     ID integer PRIMARY KEY,
                                     image_path text NOT NULL,
                                     ID_recipe integer NOT NULL,
                                     FOREIGN KEY (ID_recipe) REFERENCES recipes(ID)
);

CREATE TABLE IF NOT EXISTS categories(
                                         ID integer PRIMARY KEY,
                                         category text NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe_to_category(
                                                 ID_category integer NOT NULL,
                                                 ID_recipe integer NOT NULL,
                                                 FOREIGN KEY (ID_recipe) REFERENCES recipes(ID),
                                                 FOREIGN KEY (ID_category) REFERENCES categories(ID),
                                                 CONSTRAINT unique_const unique (ID_category, ID_recipe)
);

CREATE TABLE IF NOT EXISTS instructions(
                                           ID integer PRIMARY KEY,
                                           instruction text NOT NULL,
                                           ID_recipe integer NOT NULL,
                                           FOREIGN KEY (ID_recipe) references recipes(ID)
);

CREATE TABLE IF NOT EXISTS activity_type(
                                            ID integer PRIMARY KEY,
                                            name text NOT NULL
);

CREATE TABLE IF NOT EXISTS user_details(
                                           ID integer PRIMARY KEY,
                                           age integer NOT NULL,
                                           height integer NOT NULL,
                                           weight integer NOT NULL,
                                           calories integer NOT NULL,
                                           bmi integer NOT NULL,
                                           ID_activity_type integer NOT NULL,
                                           FOREIGN KEY (ID_activity_type) REFERENCES activity_type (ID)
);