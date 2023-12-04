-- Inserting data into the 'user' table
INSERT INTO user_table (id, email, full_name, password) VALUES
                                                            (1, 'john.doe@example.com', 'John Doe', 'password_1'),
                                                            (2, 'jane.smith@example.com', 'Jane Smith', 'password_2');

---- Inserting data into the 'flights' table -WILL BE USING API-
--INSERT INTO flights_table (id, flight_no, carrier_name, departure_date, departure_time, arrival_date, arrival_time, location, destination, price) VALUES
--                                                                                                                    (1, 1000, 'Delta', '2023-11-20', '12:00:00', '2023-11-20', '15:00:00', 'Los Angeles', 'Atlanta', 250.00),
--                                                                                                                    (2, 2000, 'AeroMexico', '2023-11-21', '10:30:00', '2023-11-21', '13:30:00', 'Nicks House', 'Mexico City', 300.00);

-- Inserting data into the 'scheduled_flights' table
INSERT INTO scheduled_flights_table (booking_number, email, flight_id) VALUES
                                                                                         (1,'john.doe@example.com', 1),
                                                                                         (2,'jane.smith@example.com', 2);
