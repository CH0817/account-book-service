INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 66, 'test_1', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 66 AND c.id = 66;
INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 77, 'test_2', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 66 AND c.id = 66;
INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 88, 'test_3', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 66 AND c.id = 66;