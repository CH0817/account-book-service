INSERT INTO account (id, name, account_type_id, currency_id, create_date) SELECT 66, 'test_bank', type.id, c.id, NOW()  FROM account_type type, currency c WHERE type.id = 66 AND c.id = 66;