CREATE TABLE IF NOT EXISTS users(
                                    ID integer PRIMARY KEY,
                                    username text NOT NULL,
                                    password text NOT NULL,
                                    email text NOT NULL,
                                    ID_user_details integer,
                                    CONSTRAINT unique_constr_username UNIQUE (username),
                                    CONSTRAINT unique_constr_username UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS role(
                                   ID integer PRIMARY KEY,
                                   role text NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role(
                                        ID_role integer NOT NULL,
                                        ID_user integer NOT NULL
);

