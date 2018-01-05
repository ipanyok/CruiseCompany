package entity;

import java.io.Serializable;

public class Bucket implements Serializable {

    private int id;
    private int userID;
    private int cruiseID;
    private String ticket;
    private double price;

    public Bucket() {
    }

    public Bucket(int userID, int cruiseID, String ticket, double price) {
        this.userID = userID;
        this.cruiseID = cruiseID;
        this.ticket = ticket;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCruiseID(int cruiseID) {
        this.cruiseID = cruiseID;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getCruiseID() {
        return cruiseID;
    }

    public String getTicket() {
        return ticket;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bucket bucket = (Bucket) o;

        if (userID != bucket.userID) return false;
        if (cruiseID != bucket.cruiseID) return false;
        return ticket != null ? ticket.equals(bucket.ticket) : bucket.ticket == null;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + cruiseID;
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "ticket='" + ticket + '\'' +
                ", price=" + price +
                "}";
    }
}
