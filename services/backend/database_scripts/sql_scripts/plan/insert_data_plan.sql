/*###########################################################################
 ##################### INSERT DATA INTO PLAN TABLE ##########################
 ###########################################################################*/

INSERT INTO plan(ID_user, plan_days, description) VALUES (1, 3, 'test user data');
INSERT INTO plan(ID_user, plan_days, description) VALUES (2, 5, 'alex user data');
INSERT INTO plan(ID_user, plan_days, description) VALUES (3, 5, 'alex user data');
INSERT INTO plan(ID_user, plan_days, description) VALUES (4, 7, 'vasile user data');
INSERT INTO plan(ID_user, plan_days, description) VALUES (5, 7, 'bogdan user data');
INSERT INTO plan(ID_user, plan_days, description) VALUES (6, 3, 'gabriel user data');


/*###########################################################################
 ##################### INSERT DATA INTO MENU TABLE ##########################
 ###########################################################################*/

INSERT INTO menu(ID_plan, day) VALUES (1, 1);
INSERT INTO menu(ID_plan, day) VALUES (1, 2);
INSERT INTO menu(ID_plan, day) VALUES (1, 3);
INSERT INTO menu(ID_plan, day) VALUES (2, 1);
INSERT INTO menu(ID_plan, day) VALUES (2, 2);
INSERT INTO menu(ID_plan, day) VALUES (2, 3);
INSERT INTO menu(ID_plan, day) VALUES (2, 4);
INSERT INTO menu(ID_plan, day) VALUES (2, 5);
INSERT INTO menu(ID_plan, day) VALUES (3, 1);
INSERT INTO menu(ID_plan, day) VALUES (3, 2);
INSERT INTO menu(ID_plan, day) VALUES (3, 3);
INSERT INTO menu(ID_plan, day) VALUES (3, 4);
INSERT INTO menu(ID_plan, day) VALUES (3, 5);
INSERT INTO menu(ID_plan, day) VALUES (4, 1);
INSERT INTO menu(ID_plan, day) VALUES (4, 2);
INSERT INTO menu(ID_plan, day) VALUES (4, 3);
INSERT INTO menu(ID_plan, day) VALUES (4, 4);
INSERT INTO menu(ID_plan, day) VALUES (4, 5);
INSERT INTO menu(ID_plan, day) VALUES (4, 6);
INSERT INTO menu(ID_plan, day) VALUES (4, 7);
INSERT INTO menu(ID_plan, day) VALUES (5, 1);
INSERT INTO menu(ID_plan, day) VALUES (5, 2);
INSERT INTO menu(ID_plan, day) VALUES (5, 3);
INSERT INTO menu(ID_plan, day) VALUES (5, 4);
INSERT INTO menu(ID_plan, day) VALUES (5, 5);
INSERT INTO menu(ID_plan, day) VALUES (5, 6);
INSERT INTO menu(ID_plan, day) VALUES (5, 7);
INSERT INTO menu(ID_plan, day) VALUES (6, 1);
INSERT INTO menu(ID_plan, day) VALUES (6, 2);
INSERT INTO menu(ID_plan, day) VALUES (6, 3);

/*###########################################################################
 ##################### INSERT DATA INTO MEAL TABLE ##########################
 ###########################################################################*/
