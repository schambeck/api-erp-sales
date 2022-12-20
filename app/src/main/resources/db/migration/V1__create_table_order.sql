CREATE TABLE "order"
(
    id         UUID PRIMARY KEY,
    client_id  UUID           NOT NULL,
    status     VARCHAR(10)    NOT NULL,
    total_cost NUMERIC(12, 2) NOT NULL
);

CREATE TABLE order_line
(
    id         UUID PRIMARY KEY,
    order_id   UUID           NOT NULL REFERENCES "order" (id),
    product_id UUID           NOT NULL,
    quantity   NUMERIC(12, 2) NOT NULL,
    price      NUMERIC(12, 2) NOT NULL,
    cost       NUMERIC(12, 2) NOT NULL
);
