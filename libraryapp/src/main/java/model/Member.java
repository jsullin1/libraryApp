package model;

/**
 * This class represents a Member object in the DB
 */
public class Member {
    private final int memberId;
    private final String name;
    private final String email;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
