-- librarydb_group15_insert.sql
-- Sample data for testing + demo

-- TOPIC
INSERT INTO Topic (TopicID, Name) VALUES
(1, 'Computer Science'),
(2, 'Fiction'),
(3, 'History'),
(4, 'Math'),
(5, 'Self-Help');

-- AUTHOR
INSERT INTO Author (AuthorID, Name) VALUES
(1, 'Robert Sedgewick'),
(2, 'Kevin Wayne'),
(3, 'George Orwell'),
(4, 'Yuval Noah Harari'),
(5, 'James Stewart'),
(6, 'Cal Newport');

-- BOOK
INSERT INTO Book (BookID, ISBN, Title, PublicationYear, TotalCopies) VALUES
(101, '978-0134076423', 'Algorithms', 2011, 3),
(102, '978-0451524935', '1984', 1949, 5),
(103, '978-0062316110', 'Sapiens: A Brief History of Humankind', 2015, 2),
(104, '978-1285741550', 'Calculus: Early Transcendentals', 2015, 2),
(105, '978-0735211292', 'Atomic Habits', 2018, 4),
(106, '978-1455586691', 'Deep Work', 2016, 3);

-- BOOK_AUTHOR (M:N)
INSERT INTO Book_Author (BookID, AuthorID) VALUES
(101, 1),
(101, 2),
(102, 3),
(103, 4),
(104, 5),
(106, 6);

-- BOOK_TOPIC (M:N)
INSERT INTO Book_Topic (BookID, TopicID) VALUES
(101, 1), -- Algorithms -> CS
(101, 4), -- Algorithms -> Math (extra tag)
(102, 2), -- 1984 -> Fiction
(103, 3), -- Sapiens -> History
(104, 4), -- Calculus -> Math
(105, 5), -- Atomic Habits -> Self-Help
(106, 5), -- Deep Work -> Self-Help
(106, 1); -- Deep Work -> CS (productivity for students)

-- MEMBER
INSERT INTO Member (MemberID, Name, Email) VALUES
(201, 'Kelvin Hoang', 'kelvin.hoang@example.com'),
(202, 'Zachary Davis', 'zachary.davis@example.com'),
(203, 'John Sullins', 'john.sullins@example.com'),
(204, 'Maria Lopez', 'maria.lopez@example.com');

-- EMPLOYEE
INSERT INTO Employee (EmployeeID, Name, Role, Email) VALUES
(301, 'Alice Nguyen', 'Librarian', 'alice.nguyen@library.org'),
(302, 'Brian Kim', 'Assistant', 'brian.kim@library.org'),
(303, 'Sofia Patel', 'Manager', 'sofia.patel@library.org');


-- CHECKOUT (Postgres)
INSERT INTO Checkout (CheckoutID, CheckoutDate, DueDate, ReturnDate, MemberID, EmployeeID, BookID) VALUES
(401, '2026-01-10', '2026-01-24', '2026-01-20', 201, 301, 102),
(402, '2026-01-28', '2026-02-11', NULL,         202, 302, 101),
(403, '2026-02-05', '2026-02-19', NULL,         201, 301, 105),
(404, '2026-01-15', '2026-01-29', '2026-02-02', 203, 302, 103),
(405, '2026-02-01', '2026-02-15', NULL,         204, 303, 106),
(406, '2025-12-10', '2025-12-24', '2026-01-02', 201, 301, 104),
(407, '2025-11-01', '2025-11-15', '2025-11-10', 204, 302, 102);


-- Fix sequences after manual ID inserts (Postgres)
SELECT setval(pg_get_serial_sequence('topic', 'topicid'), (SELECT MAX(topicid) FROM topic));
SELECT setval(pg_get_serial_sequence('author', 'authorid'), (SELECT MAX(authorid) FROM author));
SELECT setval(pg_get_serial_sequence('book', 'bookid'), (SELECT MAX(bookid) FROM book));
SELECT setval(pg_get_serial_sequence('member', 'memberid'), (SELECT MAX(memberid) FROM member));
SELECT setval(pg_get_serial_sequence('employee', 'employeeid'), (SELECT MAX(employeeid) FROM employee));
SELECT setval(pg_get_serial_sequence('checkout', 'checkoutid'), (SELECT MAX(checkoutid) FROM checkout));