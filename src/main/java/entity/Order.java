package entity;

import java.io.Serializable;

public class Order implements Serializable{

    private int id;
    private int userID;
    private int cruiseID;
    private String ticket;
    private double price;
    private String bonus;
    private String excursion;

    public Order() {
    }

    public Order(int userID, int cruiseID, String ticket, double price, String bonus, String excursion) {
        this.userID = userID;
        this.cruiseID = cruiseID;
        this.ticket = ticket;
        this.price = price;
        this.bonus = bonus;
        this.excursion = excursion;
    }

    public String getExcursion() {
        return excursion;
    }

    public void setExcursion(String excursion) {
        this.excursion = excursion;
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

    public String getBonus() {
        return bonus;
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

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (userID != order.userID) return false;
        if (cruiseID != order.cruiseID) return false;
        return ticket != null ? ticket.equals(order.ticket) : order.ticket == null;
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
                ", bonus='" + bonus + '\'' +
                "}";
    }
}
