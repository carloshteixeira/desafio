INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (name, email, phone, password) VALUES ('Maria Brown', 'maria@gmail.com', '988888888', '$2a$10$/gVixez0Gl50f8PgFTarMuwbRMT.TmCYJ/8QOr1NudFOr1dvGSca2');
INSERT INTO tb_user (name, email, phone, password) VALUES ('Alex Green', 'alex@gmail.com', '977777777', '$2a$10$/gVixez0Gl50f8PgFTarMuwbRMT.TmCYJ/8QOr1NudFOr1dvGSca2');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);