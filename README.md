Hotel Management System

A console-based Java application for managing hotel room reservations using MySQL database. 
Features include reserving rooms, viewing all reservations, retrieving room numbers by ID and guest name, updating reservations, and deleting by ID.
​

Features
Reserve Room: Add new reservations with guest name, room number, and contact.
​
View Reservations: Display all current reservations with ID, guest, room, contact, and date.

Get Room Number: Fetch room by reservation ID and guest name.

Update Reservation: Modify guest name, room, or contact for a given ID.

Delete Reservation: Remove reservation by ID.

​
Technologies

Java (core application with JDBC)

MySQL (database storage)

Scanner for user input

​
Prerequisites

Java Development Kit (JDK 8 or higher)

MySQL Server running locally

MySQL Connector/J (JDBC driver)


Database Setup

--> Create a database named hotel_db:

CREATE DATABASE hoteldb;

--> Use this table structure (inferred from code):

USE hoteldb;

CREATE TABLE reservations (
    reservationid INT AUTO_INCREMENT PRIMARY KEY,
    guestname VARCHAR(255),
    roomno INT,
    contactno VARCHAR(20),
    reservationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
​

Configuration
Update these constants in HotelManagementSystem.java:

url: jdbc:mysql://localhost:3306/hoteldb

username: root (default)

password: Admin@123 (change for security)
​

Installation

Clone the repository.

Add MySQL JDBC driver to your classpath (e.g., mysql-connector-java-8.0.x.jar).

Set up the database as above.


Usage
Compile: javac -cp "path/to/mysql-connector.jar:." HotelManagementSystem.java

Run: java -cp "path/to/mysql-connector.jar:." HotelManagementSystem


Follow the menu options (1-5 for operations, 0 to exit).​

Menu Example

HOTEL MANAGEMENT SYSTEM

1. Reserve a Room

2. View Reservations

3. Get Room Number

4. Update Reservations

5. Delete Reservations

0. Exit

Choose an Option:


Known Limitations

Hardcoded DB credentials (update for production).

No input validation for room availability or duplicates.

Console-only; no GUI.
​

Future Enhancements

Add room availability checks.

GUI with Swing/JavaFX.

REST API with Spring Boot.

User authentication.


Contributing

Fork, create a branch, make changes, and submit a pull request.


License

MIT License - feel free to use and modify.
