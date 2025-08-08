CREATE TABLE tariffs (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title varchar(64) UNIQUE NOT NULL,
    description varchar(1024),
    promotion_count int NOT NULL,
    boost_factor DOUBLE PRECISION NOT NULL,
    base_price decimal(10, 2) NOT NULL,
    duration_days decimal(10, 2) NOT NULL,
    active boolean DEFAULT TRUE NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);

CREATE TABLE user_promotions  (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id bigint NOT NULL,
    promotion_count int NOT NULL,
    used_count int DEFAULT 0,
    tariff_id bigint NOT NULL,
    base_price decimal(10, 2) NOT NULL,
    status varchar(20) NOT NULL,

    started_at timestamptz NOT NULL,
    expired_at timestamptz NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,

    CONSTRAINT fk_tariff_id FOREIGN KEY (tariff_id) REFERENCES tariffs (id)
);

CREATE INDEX idx_tariff_id
    ON user_promotions(tariff_id);
