package entity;

import java.io.Serializable;
import java.sql.Date;

public class Cruise implements Serializable {

    private int id;
    private int shipID;
    private String name;
    private int cityFromID;
    private int cityToID;
    private Date dateStart;
    private Date dateFinish;
    private int portsCount;
    private String category;
    private double price;

    public Cruise() {
    }

    public Cruise(int shipID, String name, int cityFromID, int cityToID, Date dateStart, Date dateFinish, int portsCount, String category, double price) {
        this.shipID = shipID;
        this.name = name;
        this.cityFromID = cityFromID;
        this.cityToID = cityToID;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.portsCount = portsCount;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getShipID() {
        return shipID;
    }

    public String getName() {
        return name;
    }

    public int getCityFromID() {
        return cityFromID;
    }

    public int getCityToID() {
        return cityToID;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public int getPortsCount() {
        return portsCount;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityFromID(int cityFromID) {
        this.cityFromID = cityFromID;
    }

    public void setCityToID(int cityToID) {
        this.cityToID = cityToID;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setPortsCount(int portsCount) {
        this.portsCount = portsCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cruise cruise = (Cruise) o;

        return name != null ? name.equals(cruise.name) : cruise.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                "}";
    }
}
