INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 'a', 'test_1', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 'a' AND c.id = 'a';
INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 'b', 'test_2', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 'b' AND c.id = 'b';
INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 'c', 'test_3', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 'c' AND c.id = 'c';