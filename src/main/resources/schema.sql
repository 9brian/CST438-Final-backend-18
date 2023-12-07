CREATE TABLE user_table (
    id            INT AUTO_INCREMENT,
    email         VARCHAR(45) UNIQUE,
    full_name     VARCHAR(25) NOT NULL,
    password      VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE flights_table (
    id              INT AUTO_INCREMENT,
    flight_no       INT NOT NULL,
    carrier_name    VARCHAR(100),
    price           DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE segments (
    id INT AUTO_INCREMENT,
    flight_id INT NOT NULL,
    flight_number INT NOT NULL,
    departure_airport_code VARCHAR(45) NOT NULL,
    arrival_airport_code VARCHAR(45) NOT NULL,
    departure_date VARCHAR(45),
    arrival_date VARCHAR(45),
    departure_time VARCHAR(45),
    arrival_time VARCHAR(45),
    PRIMARY KEY (id)
);


CREATE TABLE scheduled_flights_table (
    booking_number  INT AUTO_INCREMENT,
    email           VARCHAR(45),
    flight_id       INT,
    PRIMARY KEY (booking_number),
    FOREIGN KEY (email) REFERENCES user_table(email) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES flights_table(id) ON DELETE CASCADE
);
