-- Inserting data in Client table
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(1, 'EMMANUEL', 'JEANS', 'MALE', '418-888-9988');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(2, 'POLO', 'PAUL', 'MALE', '418-666-4800');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(3, 'EMILE', 'ROMAIN', 'MALE', '418-444-9972');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(4, 'MICHELLE', 'OBAMA', 'FEMALE', '514-222-0072');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(5, 'STEPHEN', 'CURRY', 'MALE', '418-261-4872');

-- Inserting data in DressType table
INSERT INTO dress_type(id, name) VALUES(1,'PANTS');
INSERT INTO dress_type(id, name) VALUES(2, 'SHIRT');
INSERT INTO dress_type(id, name) VALUES(3, 'SMOKING');
INSERT INTO dress_type(id, name) VALUES(4, 'T-SHIRT');

-- Inserting data in ModelType table
INSERT INTO model_type(id, name) VALUES(1,'SHERPA');
INSERT INTO model_type(id, name) VALUES(2, 'JACKET');
INSERT INTO model_type(id, name) VALUES(3, 'SWEAT');
INSERT INTO model_type(id, name) VALUES(4, 'TINY');

-- Inserting data in MeasureType table
INSERT INTO measure_type(id, name) VALUES(1,'SHOULDER');
INSERT INTO measure_type(id, name) VALUES(2, 'HIPS');
INSERT INTO measure_type(id, name) VALUES(3, 'HAND');
INSERT INTO measure_type(id, name) VALUES(4, 'LEG');

-- Inserting data in MaterialType table
INSERT INTO material_type(id, name, image) VALUES(1, 'COTTON', '/image/path');
INSERT INTO material_type(id, name, image) VALUES(2, 'POLYESTER', '/image/path');
INSERT INTO material_type(id, name, image) VALUES(3, 'SILK', '/image/path');

-- Inserting data in Dress table
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(1, 500, 1, 3, 3);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(2, 1000, 2, 2, 3);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(3, 750, 1, 3, 2);
INSERT INTO dress(id, amount, dress_type_id, material_type_id, model_type_id) VALUES(4, 1500, 1, 2, 1);

-- Inserting data in Measure table
INSERT INTO measure(id, `value`, dress_id, measure_type_id) VALUES(1, 5, 1, 3);
INSERT INTO measure(id, `value`, dress_id, measure_type_id) VALUES(2, 5, 1, 3);
INSERT INTO measure(id, `value`, dress_id, measure_type_id) VALUES(3, 5, 1, 3);
INSERT INTO measure(id, `value`, dress_id, measure_type_id) VALUES(4, 5, 1, 3);

-- Inserting data in Login table
INSERT INTO login(id, username, password, login_category) VALUES(1, 'username_1', 'password_1', 'ADMINISTRATOR');
INSERT INTO login(id, username, password, login_category) VALUES(2, 'username_2', 'password_2', 'EMPLOYEE');
INSERT INTO login(id, username, password, login_category) VALUES(3, 'username_3', 'password_3', 'MANAGER');
INSERT INTO login(id, username, password, login_category) VALUES(4, 'username_4', 'password_4', 'ADMINISTRATOR');

-- Inserting data in Employee table
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(1, 'KOKOU', 'KOFFI', 'MALE', '99990262', 1);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(2, 'EMMANUEL', 'SAMI', 'MALE', '99880210', 2);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(3, 'JEANNE', 'SOSSOU', 'FEMALE', '99110952', 3);
INSERT INTO employee(id, first_name, last_name, gender, telephone, login_id) VALUES(4, 'ERIC', 'CANTONA', 'MALE', '92990252', 4);

-- Inserting data in Order table
INSERT INTO "order"(id, number, date, delivery_date, client_id) VALUES(1, 'order-00001', '2021-09-20 18:00:00.69', '2021-09-30 18:00:00.69', 1);
INSERT INTO "order"(id, number, date, delivery_date, client_id) VALUES(2, 'order-00002', '2021-06-07 18:00:00.69', '2021-06-20 18:00:00.69', 1);
INSERT INTO "order"(id, number, date, delivery_date, client_id) VALUES(3, 'order-00003', '2021-09-20 18:00:00.69', '2021-09-30 18:00:00.69', 2);
INSERT INTO "order"(id, number, date, delivery_date, client_id) VALUES(4, 'order-00004', '2021-09-20 18:00:00.69', '2021-09-30 18:00:00.69', 3);