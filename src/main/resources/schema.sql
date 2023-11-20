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
                               departure_date  DATE NOT NULL,
                               departure_time  TIME NOT NULL,
                               arrival_date    DATE NOT NULL,
                               arrival_time    TIME NOT NULL,
                               location        VARCHAR(100) NOT NULL,
                               destination     VARCHAR(100) NOT NULL,
                               price           DECIMAL(10,2) NOT NULL,
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
