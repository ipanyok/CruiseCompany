package services.beans;

import entity.*;

import java.util.List;

/**
 * This view is used to show correct information in order section.
 * By default many rows there are ID's of entities
 *
 * */

public class OrdersInfoView {
    private Order order;
    private User user;
    private Cruise cruise;
    private Country countryFrom;
    private Country countryTo;
    private Ship ship;

    public OrdersInfoView(Order order, User user, Cruise cruise, Country countryFrom, Country countryTo, Ship ship) {
        this.order = order;
        this.user = user;
        this.cruise = cruise;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.ship = ship;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Country getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(Country countryFrom) {
        this.countryFrom = countryFrom;
    }

    public Country getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(Country countryTo) {
        this.countryTo = countryTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}
