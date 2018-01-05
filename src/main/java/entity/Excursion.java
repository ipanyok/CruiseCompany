package entity;

import java.io.Serializable;

public class Excursion implements Serializable {

    private int id;
    private int cruiseID;
    private String name;
    private double price;

    public Excursion() {
    }

    public Excursion(int cruiseID, String name, double price) {
        this.cruiseID = cruiseID;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCruiseID() {
        return cruiseID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCruiseID(int cruiseID) {
        this.cruiseID = cruiseID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Excursion excursion = (Excursion) o;

        if (cruiseID != excursion.cruiseID) return false;
        return name != null ? name.equals(excursion.name) : excursion.name == null;
    }

    @Override
    public int hashCode() {
        int result = cruiseID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", price=" + price +
                "}";
    }
}
