INSERT INTO trade (id, account_id, item_id, transact_date, cost, create_date) SELECT 'a', a.id, i.id, NOW(), 100, NOW() FROM account a, item i WHERE a.id = 'a' AND i.id = 'a';
INSERT INTO trade (id, account_id, item_id, transact_date, cost, create_date) SELECT 'b', a.id, i.id, NOW(), 300, NOW() FROM account a, item i WHERE a.id = 'b' AND i.id = 'b';