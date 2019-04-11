DROP TABLE IF EXISTS `account_type`;

CREATE TABLE IF NOT EXISTS account_type
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL UNIQUE,
    create_date DATE        NOT NULL,
    update_date DATE,
    PRIMARY KEY (id)
);