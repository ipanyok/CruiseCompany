package entity;

import java.io.Serializable;

public class Ship implements Serializable {

    private int id;
    private String name;
    private int countPassengers;

    public Ship() {
    }

    public Ship(String name, int countPassengers) {
        this.name = name;
        this.countPassengers = countPassengers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountPassengers(int countPassengers) {
        this.countPassengers = countPassengers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        return name != null ? name.equals(ship.name) : ship.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", countPassengers=" + countPassengers +
                "}";
    }
}
