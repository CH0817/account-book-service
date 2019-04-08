DROP TABLE IF EXISTS `trade`;
DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `account_type`;
DROP TABLE IF EXISTS `currency`;

CREATE TABLE IF NOT EXISTS account_type
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL UNIQUE,
    create_date DATE        NOT NULL,
    update_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS currency
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL UNIQUE,
    create_date DATE        NOT NULL,
    update_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account
(
    id               BIGINT         NOT NULL AUTO_INCREMENT,
    name             VARCHAR(10) NOT NULL UNIQUE,
    account_type_id  BIGINT         NOT NULL,
    currency_id      BIGINT         NOT NULL,
    init_money       DECIMAL(9, 2),
    current_money    DECIMAL(9, 2),
    closing_date     DATE,
    payment_due_date DATE,
    note             VARCHAR(150),
    create_date      DATE        NOT NULL,
    update_date      DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (account_type_id) REFERENCES account_type (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);

CREATE TABLE IF NOT EXISTS category
(
    id            BIGINT         NOT NULL AUTO_INCREMENT,
    name          VARCHAR(10) NOT NULL UNIQUE,
    category_type BIGINT         NOT NULL,
    create_date   DATE        NOT NULL,
    update_date   DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS item
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL UNIQUE,
    category_id BIGINT         NOT NULL,
    create_date DATE        NOT NULL,
    update_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS trade
(
    id            BIGINT     NOT NULL AUTO_INCREMENT,
    account_id    BIGINT     NOT NULL,
    item_id       BIGINT     NOT NULL,
    transact_date DATE    NOT NULL,
    cost          DECIMAL(9, 2) NOT NULL,
    note          VARCHAR(150),
    create_date   DATE    NOT NULL,
    update_date   DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);