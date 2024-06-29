-- Insert 10 records into the Hotels table
INSERT INTO Hotels (name, address, city, state, country, phone, email)
VALUES
    ('Hotel A', '123 Main St', 'City A', 'State A', 'Country A', '1234567890', 'hotelA@example.com'),
    ('Hotel B', '456 Elm St', 'City B', 'State B', 'Country B', '2345678901', 'hotelB@example.com'),
    ('Hotel C', '789 Oak St', 'City C', 'State C', 'Country C', '3456789012', 'hotelC@example.com'),
    ('Hotel D', '321 Pine St', 'City D', 'State D', 'Country D', '4567890123', 'hotelD@example.com'),
    ('Hotel E', '654 Maple St', 'City E', 'State E', 'Country E', '5678901234', 'hotelE@example.com'),
    ('Hotel F', '987 Cedar St', 'City F', 'State F', 'Country F', '6789012345', 'hotelF@example.com'),
    ('Hotel G', '159 Birch St', 'City G', 'State G', 'Country G', '7890123456', 'hotelG@example.com'),
    ('Hotel H', '753 Walnut St', 'City H', 'State H', 'Country H', '8901234567', 'hotelH@example.com'),
    ('Hotel I', '246 Spruce St', 'City I', 'State I', 'Country I', '9012345678', 'hotelI@example.com'),
    ('Hotel J', '528 Sycamore St', 'City J', 'State J', 'Country J', '0123456789', 'hotelJ@example.com');

-- Insert 10 records into the Rooms table, associating them with hotels
INSERT INTO Rooms (hotel_id, room_number, room_type, price_per_night)
VALUES
    (1, 101, 'Single', 100.00),
    (1, 102, 'Double', 150.00),
    (2, 201, 'Single', 120.00),
    (2, 202, 'Double', 180.00),
    (3, 301, 'Single', 110.00),
    (3, 302, 'Double', 160.00),
    (4, 401, 'Single', 130.00),
    (4, 402, 'Double', 190.00),
    (5, 501, 'Single', 115.00),
    (5, 502, 'Double', 170.00);

-- Insert 10 records into the Customers table
INSERT INTO Customers (first_name, last_name, address, city, state, country, phone, email)
VALUES
    ('John', 'Doe', '123 Main St', 'City A', 'State A', 'Country A', '1234567890', 'johndoe@example.com'),
    ('Jane', 'Smith', '456 Elm St', 'City B', 'State B', 'Country B', '2345678901', 'janesmith@example.com'),
    ('Michael', 'Johnson', '789 Oak St', 'City C', 'State C', 'Country C', '3456789012', 'michaeljohnson@example.com'),
    ('Emily', 'Williams', '321 Pine St', 'City D', 'State D', 'Country D', '4567890123', 'emilywilliams@example.com'),
    ('Daniel', 'Brown', '654 Maple St', 'City E', 'State E', 'Country E', '5678901234', 'danielbrown@example.com'),
    ('Olivia', 'Jones', '987 Cedar St', 'City F', 'State F', 'Country F', '6789012345', 'oliviajones@example.com'),
    ('Sophia', 'Taylor', '159 Birch St', 'City G', 'State G', 'Country G', '7890123456', 'sophiataylor@example.com'),
    ('David', 'Anderson', '753 Walnut St', 'City H', 'State H', 'Country H', '8901234567', 'davidanderson@example.com'),
    ('Emma', 'Thomas', '246 Spruce St', 'City I', 'State I', 'Country I', '9012345678', 'emmathomas@example.com'),
    ('William', 'Clark', '528 Sycamore St', 'City J', 'State J', 'Country J', '0123456789', 'williamclark@example.com');

-- Insert 10 records into the Bookings table, associating them with customers and rooms
INSERT INTO Bookings (customer_id, room_id, check_in_date, check_out_date, num_of_guests, total_price)
VALUES
    (1, 1, '2023-06-01', '2023-06-05', 1, 400.00),
    (2, 2, '2023-06-02', '2023-06-06', 2, 600.00),
    (3, 3, '2023-06-03', '2023-06-07', 1, 480.00),
    (4, 4, '2023-06-04', '2023-06-08', 2, 720.00),
    (5, 5, '2023-06-05', '2023-06-09', 1, 460.00),
    (6, 6, '2023-06-06', '2023-06-10', 2, 680.00),
    (7, 7, '2023-06-07', '2023-06-11', 1, 440.00),
    (8, 8, '2023-06-08', '2023-06-12', 2, 760.00),
    (9, 9, '2023-06-09', '2023-06-13', 1, 500.00),
    (10, 10, '2023-06-10', '2023-06-14', 2, 740.00);
