-- librarydb_team15_create.sql

-- =========================
-- Core tables
-- =========================

CREATE TABLE Book (
  BookID SERIAL PRIMARY KEY,
  ISBN VARCHAR(20) UNIQUE,
  Title VARCHAR(255) NOT NULL,
  PublicationYear INT,
  TotalCopies INT NOT NULL DEFAULT 1,
  CHECK (TotalCopies >= 0),
  CHECK (PublicationYear IS NULL OR PublicationYear >= 0)
);

CREATE TABLE Author (
  AuthorID Serial PRIMARY KEY,
  Name VARCHAR(255) NOT NULL
);

CREATE TABLE Topic (
  TopicID Serial PRIMARY KEY,
  Name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Member (
  MemberID Serial PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  Email VARCHAR(255) UNIQUE
);

CREATE TABLE Employee (
  EmployeeID Serial PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  Role VARCHAR(100) NOT NULL,
  Email VARCHAR(255) UNIQUE
);

-- =========================
-- Associative tables for M:N
-- =========================

CREATE TABLE Book_Author (
  BookID INT NOT NULL,
  AuthorID INT NOT NULL,
  PRIMARY KEY (BookID, AuthorID),
  FOREIGN KEY (BookID) REFERENCES Book(BookID)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Book_Topic (
  BookID INT NOT NULL,
  TopicID INT NOT NULL,
  PRIMARY KEY (BookID, TopicID),
  FOREIGN KEY (BookID) REFERENCES Book(BookID)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (TopicID) REFERENCES Topic(TopicID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================
-- Transaction table
-- =========================

CREATE TABLE Checkout (
  CheckoutID Serial PRIMARY KEY,
  CheckoutDate DATE NOT NULL,
  DueDate DATE NOT NULL,
  ReturnDate DATE NULL,
  MemberID INT NOT NULL,
  EmployeeID INT NOT NULL,
  BookID INT NOT NULL,
  FOREIGN KEY (MemberID) REFERENCES Member(MemberID)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (BookID) REFERENCES Book(BookID)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CHECK (DueDate >= CheckoutDate),
  CHECK (ReturnDate IS NULL OR ReturnDate >= CheckoutDate)
);
