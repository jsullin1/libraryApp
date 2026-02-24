package ui;

import dao.BookDao;
import dao.MemberDao;
import dao.CheckoutDao;

import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    private static final BookDao bookDao = new BookDao();
    private static final MemberDao memberDao = new MemberDao();
    private static final CheckoutDao checkoutDao = new CheckoutDao();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int choice = readInt("Choose option: ");

            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> listBooks();
                    case 3 -> deleteBook();
                    case 4 -> checkoutBook();
                    case 5 -> returnBook();
                    case 6 -> updateBookCopies();
                    case 9 -> {
                        System.out.println("Goodbye.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("""
            ===== Library Menu =====
            1) Add Book
            2) List Books
            3) Delete Book
            4) Checkout Book
            5) Return Book
            6) Update Book Copies
            9) Exit
            """);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Enter a number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // ===== ACTION METHODS =====

    private static void addBook() throws Exception {
        String title = readLine("Title: ");
        int year = readInt("Year: ");
        int copies = readInt("Copies: ");

        bookDao.insertBook(title, year, copies);
        System.out.println("Book added.");
    }

    private static void listBooks() throws Exception {
        bookDao.listAllBooks();
    }

    private static void deleteBook() throws Exception {
        int id = readInt("Book ID to delete: ");
        bookDao.deleteBook(id);
        System.out.println("Deleted.");
    }

    private static void checkoutBook() throws Exception {
        int bookId = readInt("Book ID: ");
        int memberId = readInt("Member ID: ");
        int employeeId = readInt("Employee ID: ");

        checkoutDao.checkoutBook(bookId, memberId, employeeId);
        System.out.println("Checkout successful.");
    }

    private static void returnBook() throws Exception {
        int checkoutId = readInt("Checkout ID: ");
        checkoutDao.returnBook(checkoutId);
        System.out.println("Book returned.");
    }

    private static void updateBookCopies() throws Exception {
        int id = readInt("Book ID: ");
        int copies = readInt("New total copies: ");

        bookDao.updateBookCopies(id, copies);
        System.out.println("Book updated.");
    }

}