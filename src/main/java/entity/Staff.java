package entity;

import java.io.Serializable;

public class Staff implements Serializable {

    private int id;
    private int shipID;
    private String name;
    private String speciality;

    public Staff() {
    }

    public Staff(int shipID, String name, String speciality) {
        this.shipID = shipID;
        this.name = name;
        this.speciality = speciality;
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

    public String getSpeciality() {
        return speciality;
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

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        return speciality != null ? speciality.equals(staff.speciality) : staff.speciality == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                "}";
    }
}
