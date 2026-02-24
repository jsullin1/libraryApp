-- librarydb_team15_select.sql
USE librarydb_team15;

-- 1) Select all authors
SELECT * FROM Author;

-- 2) Select all books
SELECT * FROM Book;

-- 3) Total copies of the book titled "Algorithms"
SELECT Title, TotalCopies
FROM Book
WHERE Title = 'Algorithms';

-- 4) Total count of books currently checked out (active loans)
SELECT COUNT(*) AS `Total Books Out`
FROM Checkout
WHERE ReturnDate IS NULL;

-- 5) Books checked out by "Zachary Davis" (history)
SELECT m.Name, b.Title, c.CheckoutDate, c.DueDate, c.ReturnDate
FROM Checkout c
JOIN Member m ON m.MemberID = c.MemberID
JOIN Book b ON b.BookID = c.BookID
WHERE m.Name = 'Zachary Davis'
ORDER BY c.CheckoutDate DESC;

-- 6) Overdue checkouts (active + due date passed)
SELECT c.CheckoutID, m.Name AS Member, b.Title, c.DueDate
FROM Checkout c
JOIN Member m ON m.MemberID = c.MemberID
JOIN Book b ON b.BookID = c.BookID
WHERE c.ReturnDate IS NULL
  AND c.DueDate < CURRENT_DATE()
ORDER BY c.DueDate ASC;

-- 7) Search books by title keyword
SELECT BookID, Title, PublicationYear
FROM Book
WHERE Title LIKE '%Work%';

-- 8) Search books by author name (via Book_Author)
SELECT DISTINCT b.BookID, b.Title, b.PublicationYear
FROM Book b
JOIN Book_Author ba ON ba.BookID = b.BookID
JOIN Author a ON a.AuthorID = ba.AuthorID
WHERE a.Name LIKE '%Orwell%';

-- 9) Search books by topic (via Book_Topic)
SELECT DISTINCT b.BookID, b.Title
FROM Book b
JOIN Book_Topic bt ON bt.BookID = b.BookID
JOIN Topic t ON t.TopicID = bt.TopicID
WHERE t.Name = 'Computer Science';

-- 10) Borrowing history for a member (example: Kelvin Hoang)
SELECT m.Name, b.Title, c.CheckoutDate, c.DueDate, c.ReturnDate
FROM Checkout c
JOIN Member m ON m.MemberID = c.MemberID
JOIN Book b ON b.BookID = c.BookID
WHERE m.Name = 'Kelvin Hoang'
ORDER BY c.CheckoutDate DESC;

-- 11) Most borrowed books
SELECT b.Title, COUNT(*) AS TimesBorrowed
FROM Checkout c
JOIN Book b ON b.BookID = c.BookID
GROUP BY b.BookID, b.Title
ORDER BY TimesBorrowed DESC, b.Title ASC;

-- 12) Most borrowed topics
SELECT t.Name AS Topic, COUNT(*) AS TimesBorrowed
FROM Checkout c
JOIN Book_Topic bt ON bt.BookID = c.BookID
JOIN Topic t ON t.TopicID = bt.TopicID
GROUP BY t.TopicID, t.Name
ORDER BY TimesBorrowed DESC, t.Name ASC;

-- 13) Available copies for "1984" (active checkouts only)
SELECT b.Title,
       b.TotalCopies - COUNT(c.CheckoutID) AS `Available Copies`
FROM Book b
LEFT JOIN Checkout c
  ON b.BookID = c.BookID
 AND c.ReturnDate IS NULL
WHERE b.Title = '1984'
GROUP BY b.BookID, b.Title, b.TotalCopies;
