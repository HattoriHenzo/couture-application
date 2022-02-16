-- Inserting data in Client table
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(1, 'EMMANUEL', 'JEANS', 'MALE', '418-261-4872');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(2, 'STEPHEN', 'CURRY', 'MALE', '418-000-9090');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(3, 'LEBRON', 'JAMES', 'MALE', '514-261-4210');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(4, 'EMILIE', 'ROSE', 'FEMALE', '615-777-6030');
INSERT INTO client (id, first_name, last_name, gender, telephone) VALUES(5, 'JAQUELINE', 'KENT', 'FEMALE', '418-555-2040');

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