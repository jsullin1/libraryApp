package model;

import java.util.Date;

/**
 * This class represents a Checkout object in the DB
 */
public class Checkout {
    private final int checkoutID;
    private final Date checkoutDate;
    private Date dueDate;
    private Date returnDate;
    private final int memberID;
    private final int employeeID;
    private final int bookID;

    public Checkout(int checkoutID, Date checkoutDate, Date dueDate, int memberID, int employeeID, int bookID) {
        this.checkoutID = checkoutID;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.memberID = memberID;
        this.employeeID = employeeID;
        this.bookID = bookID;
    }


    public int getCheckoutID() {
        return checkoutID;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getBookID() {
        return bookID;
    }
}
