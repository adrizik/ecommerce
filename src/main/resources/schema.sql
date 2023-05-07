CREATE TABLE account (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(255),
    balance FLOAT
);

CREATE TABLE product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    price FLOAT
);

CREATE TABLE account_products (
    account_id INTEGER,
    products_id INTEGER,
    PRIMARY KEY (account_id, products_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (products_id) REFERENCES product(id)
);