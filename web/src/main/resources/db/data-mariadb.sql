INSERT INTO account_type (name, create_date) VALUES ('銀行', NOW());
INSERT INTO account_type (name, create_date) VALUES ('信用卡', NOW());
INSERT INTO account_type (name, create_date) VALUES ('現金', NOW());
INSERT INTO currency (name, create_date) VALUES ('新台幣', NOW());
INSERT INTO currency (name, create_date) VALUES ('美元', NOW());
INSERT INTO category (id, name, category_type, create_date) VALUES (66, '一般收入', 0, NOW());
INSERT INTO category (id, name, category_type, create_date) VALUES (77, '餐飲', 1, NOW());
INSERT INTO item (id, name, category_id, create_date) SELECT 44, '薪資', id, NOW() FROM category WHERE id = 66;
INSERT INTO item (id, name, category_id, create_date) SELECT 55, '活存利息', id, NOW() FROM category WHERE id = 66;
INSERT INTO item (id, name, category_id, create_date) SELECT 66, '早餐', id, NOW() FROM category WHERE id = 77;
INSERT INTO item (id, name, category_id, create_date) SELECT 77, '午餐', id, NOW() FROM category WHERE id = 77;
INSERT INTO item (id, name, category_id, create_date) SELECT 88, '晚餐', id, NOW() FROM category WHERE id = 77;