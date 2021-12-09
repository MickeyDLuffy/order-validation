-- Create schema
CREATE TYPE  side AS ENUM(
    'BUY',
    'SELL'
 );
CREATE TYPE portfolio_status AS ENUM (
    'OPEN',
    'CLOSED'
);
CREATE TABLE IF NOT EXISTS "order" (
    id UUID NOT NULL PRIMARY KEY,
--     priority_id UUID NOT NULL,
    cumulative_quantity int,
    client_id UUID NOT NULL,
    status VARCHAR(15),
    ticker VARCHAR(10), -- Product id will b the ticker
    side SIDE,
    created_timestamp TIMESTAMPTZ NOT NULL
    );


CREATE TABLE IF NOT EXISTS order_execution (
    id UUID NOT NULL PRIMARY KEY,
    order_id UUID NOT NULL,
    status VARCHAR(15) NOT NULL,
    exchange VARCHAR(15) NOT NULL,
    quantity int,
    created_timestamp TIMESTAMPTZ NOT NULL,--
    unit_price decimal, -- cost of one product item
    total_price decimal -- quantity * unit_price
);
CREATE TABLE IF NOT EXISTS asset (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    ticker VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS portfolio (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    client_id UUID,
    created_timestamp TIMESTAMPTZ NOT NULL,
    status PORTFOLIO_STATUS DEFAULT 'OPEN'
);
CREATE TABLE IF NOT EXISTS asset_portfolio (
   asset_id UUID NOT NULL,
   portfolio_id UUID NOT NULL ,
   asset_quantity int,
   asset_unit_price decimal,
   PRIMARY KEY (asset_id, portfolio_id)
);
CREATE TABLE IF NOT EXISTS balance (
   id UUID NOT NULL PRIMARY KEY,
   last_updated TIMESTAMPTZ NOT NULL,
   balance decimal DEFAULT 0.0 NOT NULL
);

CREATE TABLE IF NOT EXISTS client (
    id UUID NOT NULL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    balance_id UUID,
    created_at TIMESTAMPTZ NOT NULL,
    email VARCHAR(100) UNIQUE,
    status int DEFAULT 1
);
CREATE TABLE IF NOT EXISTS "user" (
   id UUID NOT NULL PRIMARY KEY,
   client_id  UUID ,
   password VARCHAR(50) NOT NULL,
   role_id UUID
);
CREATE TABLE IF NOT EXISTS role (
   id UUID NOT NULL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS report (
    id UUID NOT NULL PRIMARY KEY,
    details JSONB,
    description TEXT,
    event_type VARCHAR(100),
    event_timestamp TIMESTAMPTZ
);

-- Create indexes
-- CREATE UNIQUE INDEX priority_name_idx ON priority(name);


-- Foreign keys

-- If a client is deleted, delete all orders the client had in his name
ALTER TABLE "order" ADD CONSTRAINT order_client_id_fk FOREIGN KEY(client_id) REFERENCES client(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE order_execution ADD CONSTRAINT order_execution_order_id_fk FOREIGN KEY(order_id) REFERENCES "order"(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
-- ALTER TABLE portfolio ADD CONSTRAINT portfolio_ticker_fk FOREIGN KEY(asset_id) REFERENCES asset(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE portfolio ADD CONSTRAINT portfolio_client_id_fk FOREIGN KEY(client_id) REFERENCES client(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE asset_portfolio ADD CONSTRAINT asset_portfolio_asset_id_fk FOREIGN KEY(asset_id) REFERENCES asset(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE asset_portfolio ADD CONSTRAINT asset_portfolio_portfolio_id_fk FOREIGN KEY(portfolio_id) REFERENCES portfolio(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE client ADD CONSTRAINT client_balance_id_fk FOREIGN KEY(balance_id) REFERENCES balance(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE "user" ADD CONSTRAINT user_client_id_fk FOREIGN KEY(client_id) REFERENCES client(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;
ALTER TABLE "user" ADD CONSTRAINT user_role_id_fk FOREIGN KEY(role_id) REFERENCES role(id) ON UPDATE  CASCADE ON DELETE  CASCADE ;