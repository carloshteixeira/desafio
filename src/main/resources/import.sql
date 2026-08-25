INSERT INTO tb_category (name) VALUES ('Livros');
INSERT INTO tb_category (name) VALUES ('Eletrônicos');
INSERT INTO tb_category (name) VALUES ('Computadores');

INSERT INTO tb_product (name, description, price, img_url) VALUES ('The Lord of the Rings', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 90.50, 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg');
INSERT INTO tb_product (name, description, price, img_url) VALUES ('Smart TV', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 2190.00, 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg');
INSERT INTO tb_product (name, description, price, img_url) VALUES ('Macbook Pro', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1250.00, 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg');
INSERT INTO tb_product (name, description, price, img_url) VALUES ('PC Gamer', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1200.00, 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/4-big.jpg');
INSERT INTO tb_product (name, description, price, img_url) VALUES ('Rails for Dummies', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 100.99, 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/5-big.jpg');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 1);

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (name, email, phone, password) VALUES ('Maria Brown', 'maria@gmail.com', '988888888', '$2a$10$/gVixez0Gl50f8PgFTarMuwbRMT.TmCYJ/8QOr1NudFOr1dvGSca2');
INSERT INTO tb_user (name, email, phone, password) VALUES ('Alex Green', 'alex@gmail.com', '977777777', '$2a$10$/gVixez0Gl50f8PgFTarMuwbRMT.TmCYJ/8QOr1NudFOr1dvGSca2');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);