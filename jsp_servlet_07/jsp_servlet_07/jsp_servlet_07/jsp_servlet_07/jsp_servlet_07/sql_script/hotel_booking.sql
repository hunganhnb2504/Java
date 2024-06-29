CREATE DATABASE hotel_booking_system;

CREATE TABLE Hotels (
                        hotel_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        address VARCHAR(100) NOT NULL,
                        city VARCHAR(100) NOT NULL,
                        state VARCHAR(100) NOT NULL,
                        country VARCHAR(100) NOT NULL,
                        phone VARCHAR(20) NOT NULL,
                        email VARCHAR(100) NOT NULL
);

CREATE TABLE Rooms (
                       room_id INT AUTO_INCREMENT PRIMARY KEY,
                       hotel_id INT,
                       room_number INT NOT NULL,
                       room_type VARCHAR(50) NOT NULL,
                       price_per_night DECIMAL(10, 2) NOT NULL,
                       FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id)
);

CREATE TABLE Customers (
                           customer_id INT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           address VARCHAR(100) NOT NULL,
                           city VARCHAR(100) NOT NULL,
                           state VARCHAR(100) NOT NULL,
                           country VARCHAR(100) NOT NULL,
                           phone VARCHAR(20) NOT NULL,
                           email VARCHAR(100) NOT NULL
);

CREATE TABLE Bookings (
                          booking_id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_id INT,
                          room_id INT,
                          check_in_date DATE NOT NULL,
                          check_out_date DATE NOT NULL,
                          num_of_guests INT NOT NULL,
                          total_price DECIMAL(10, 2) NOT NULL,
                          FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
                          FOREIGN KEY (room_id) REFERENCES Rooms(room_id)
);
