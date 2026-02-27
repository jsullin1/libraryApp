SER322 – Database Management Systems
Deliverable 4 – Application Implementation
Group 15 – Library Database Application

------------------------------------------------------------
1. Project Overview
------------------------------------------------------------

This project is a Java-based Command Line Interface (CLI)
application that connects to a PostgreSQL database using JDBC.

The system models a Library Management System with the
following entities:

• Book
• Author
• Topic
• Member
• Employee
• Checkout
• Book_Author (Many-to-Many)
• Book_Topic (Many-to-Many)

The application supports:

✔ Insert operations  
✔ Update operations  
✔ Delete operations  
✔ Querying operations  
✔ Relationship-based queries using JOIN  
✔ Menu-driven interface that loops until exit

The application returns to the main menu after each action
without restarting the program.

------------------------------------------------------------
2. Environment Requirements
------------------------------------------------------------

Operating System:
• Windows 10/11 (Tested)

Software:
• Java JDK 21
• Maven 3.9+
• PostgreSQL 18.x
• pgAdmin 4 (for database management)

JDBC Driver:
• PostgreSQL JDBC Driver (managed via Maven dependency)

------------------------------------------------------------
3. Database Setup Instructions
------------------------------------------------------------

Step 1 – Install PostgreSQL and pgAdmin.

Step 2 – Create the database:

    CREATE DATABASE librarydb_team15;

Step 3 – Connect to the database in pgAdmin.

Step 4 – Run the following SQL scripts in order:

    librarydb_team_15_create.sql
    librarydb_team15_insert.sql

The first script:
• Creates all tables
• Defines foreign keys
• Defines constraints

The second script:
• Inserts dummy data for testing

------------------------------------------------------------
4. Application Setup Instructions
------------------------------------------------------------

1. Open the project in IntelliJ IDEA.
2. Ensure Maven dependencies are downloaded.
3. Verify database credentials inside:

   src/main/java/db/Db.java

Update the following if necessary:

    jdbc:postgresql://localhost:5432/librarydb_team15
    Username: postgres
    Password: <your password>

------------------------------------------------------------
5. Running the Application
------------------------------------------------------------

Option A – Run from IntelliJ:
• Run ui.Menu.java

Option B – Run using Maven:

    mvn compile
    mvn exec:java -Dexec.mainClass="ui.Menu"

The application will display a menu that allows CRUD
operations and relationship-based queries.

------------------------------------------------------------
6. Application Structure
------------------------------------------------------------

UI Layer:
• ui.Menu.java
- Displays menu
- Handles user input
- Calls DAO methods

DAO Layer:
• BookDao.java
• MemberDao.java
• CheckoutDao.java
- Execute SQL statements
- Handle insert/update/delete/select logic

Database Layer:
• PostgreSQL database
• Tables created via SQL script

JDBC Connectivity:
• db.Db.java
- Manages database connections

------------------------------------------------------------
7. Implemented Functionalities
------------------------------------------------------------

Books:
• Add Book
• List Books
• Delete Book
• Update Book Copies

Checkout:
• Checkout Book
• Return Book

Advanced Query:
• Overdue Books (JOIN across Checkout, Book, and Member)

All operations demonstrate database state changes before
and after execution.

------------------------------------------------------------
8. Project Presentation Video
------------------------------------------------------------

YouTube Link:
<INSERT YOUTUBE LINK HERE>

The presentation demonstrates:
• ER Diagram overview
• Database schema
• Insert operation
• Update operation
• Delete operation
• Query operation
• Relationship-based JOIN query

