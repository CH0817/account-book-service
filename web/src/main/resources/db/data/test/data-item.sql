INSERT INTO item (id, name, category_id, create_date) SELECT 'a', '薪資', id, NOW() FROM category WHERE id = 'a';
INSERT INTO item (id, name, category_id, create_date) SELECT 'b', '活存利息', id, NOW() FROM category WHERE id = 'a';
INSERT INTO item (id, name, category_id, create_date) SELECT 'c', '早餐', id, NOW() FROM category WHERE id = 'b';
INSERT INTO item (id, name, category_id, create_date) SELECT 'd', '午餐', id, NOW() FROM category WHERE id = 'b';
INSERT INTO item (id, name, category_id, create_date) SELECT 'e', '晚餐', id, NOW() FROM category WHERE id = 'b';