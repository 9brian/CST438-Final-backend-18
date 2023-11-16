CREATE TABLE user_table (
    id            INT AUTO_INCREMENT,
    email         VARCHAR(45) UNIQUE,
    full_name     VARCHAR(25) NOT NULL,
    password      VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE flights_table (
    flight_id       INT,
    carrier_name    VARCHAR(100),
    departure_time  DATETIME NOT NULL,
    arrival_time    DATETIME NOT NULL,
    location        VARCHAR(100) NOT NULL,
    destination     VARCHAR(100) NOT NULL,
    price           DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (flight_id, carrier_name)
);

CREATE TABLE scheduled_flights_table (
    booking_number  INT AUTO_INCREMENT,
    email           VARCHAR(45),
    flight_no       INT,
    carrier_name    VARCHAR(100),
    PRIMARY KEY (booking_number),
    FOREIGN KEY (email) REFERENCES user_table(email) ON DELETE CASCADE,
    FOREIGN KEY (flight_no, carrier_name) REFERENCES flights_table(flight_id, carrier_name) ON DELETE CASCADE
);
