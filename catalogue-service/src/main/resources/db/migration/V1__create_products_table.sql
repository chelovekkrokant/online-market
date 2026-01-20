
CREATE SCHEMA IF NOT EXISTS catalogue;

CREATE TABLE catalogue.t_product
(
    id      SERIAL PRIMARY KEY,
    c_title VARCHAR(50) NOT NULL CHECK (LENGTH(TRIM(c_title)) >= 3),
    c_details VARCHAR(1000)
);