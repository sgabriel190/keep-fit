INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('gabriel', '31b3f135a23b9806800e38b371e9ad98e3d0c4d4e7c8f63c98e5239bc76329be', 'gabriel.strilciuc@gmail.com', null, 1800); -- strilciuic
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('alex1', '1c4ae08f794b755099f2c63ede569ac296ff539fee4eeecd4307315c4e53db22', 'alex.muraru@gmail.com', null, 2600); -- muraru
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('alex2', '3c05990671d1d93cd294b0832aa196c0df20a9ca1dc143edfe17265f02c84965', 'alex.ilioi@gmail.com', null, 2100); -- ilioi
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('vasile', '0ddfa0ceac0ad1443a0c263309881712ee86de39592a0ac82da3fb42c4d04d8f', 'vasile.enachi@gmail.com', null, 2000); -- enachi
INSERT INTO users(username, password, email, ID_user_details, target_calories)
VALUES('bogdan', 'cd1b3ac5bd40dc384d14e522dfda396fa9a20c07bdcc2e4d89ef66f96ed6f55d', 'bogdan.cojocariu@gmail.com', null, 1900); -- cojocariu


INSERT INTO role(role) VALUES ('USER');
INSERT INTO role(role) VALUES ('ADMIN');
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 1);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 2);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 3);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 4);
INSERT INTO user_role(ID_role, ID_user) VALUES (1, 5);