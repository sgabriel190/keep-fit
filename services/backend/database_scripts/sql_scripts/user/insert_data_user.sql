INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('gabriel', 'strilciuc', 'gabriel.strilciuc@gmail.com', null, 1800);
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('alex', 'muraru', 'alex.muraru@gmail.com', null, 2600);
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('alex', 'ilioi', 'alex.ilioi@gmail.com', null, 2100);
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('vasile', 'enachi', 'vasile.enachi@gmail.com', null, 2000);
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('bogdan', 'cojocariu', 'bogdan.cojocariu@gmail.com', null, 1900);


INSERT INTO role(role) VALUES ('USER');
INSERT INTO role(role) VALUES ('ADMIN');
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 1);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 2);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 3);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 4);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 5);