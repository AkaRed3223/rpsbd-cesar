-- GPS Tracking System — PostgreSQL Schema Reference
-- Hibernate manages DDL in dev (ddl-auto: update).
-- Use this as the canonical schema reference for migrations.

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Satellites that report user positions
CREATE TABLE satellites (
    id        UUID           PRIMARY KEY DEFAULT gen_random_uuid(),
    name      VARCHAR(100)   NOT NULL UNIQUE,
    latitude  DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL
);

-- Devices carried by users
CREATE TABLE devices (
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    imei VARCHAR(15)  NOT NULL UNIQUE,
    type VARCHAR(20)  NOT NULL CHECK (type IN ('PHONE', 'TABLET', 'COMPUTER'))
);

-- Users tracked by the system
CREATE TABLE users (
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name      VARCHAR(255) NOT NULL,
    device_id UUID UNIQUE REFERENCES devices (id) ON DELETE SET NULL
);

-- Every location event reported by a satellite
CREATE TABLE user_locations (
    id           UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id      UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    latitude     DECIMAL(10, 8) NOT NULL,
    longitude    DECIMAL(11, 8) NOT NULL,
    reported_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    satellite_id UUID        REFERENCES satellites (id) ON DELETE SET NULL
);

-- Optimises last-known-location and history queries
CREATE INDEX idx_user_locations_user_reported
    ON user_locations (user_id, reported_at DESC);
