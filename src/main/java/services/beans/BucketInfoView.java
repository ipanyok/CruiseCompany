package services.beans;

import entity.Bucket;
import entity.Cruise;
import entity.Excursion;

import java.util.List;

/**
 * This view is used to show correct information in bucket section.
 * By default many rows there are ID's of entities
 *
 * */

public class BucketInfoView {
    private Bucket bucket;
    private Cruise cruise;
    private String ticket;
    private double price;
    private String countryFrom;
    private String countryTo;
    private String cityFrom;
    private String cityTo;
    private String ship;
    private List<Excursion> excursion;
    private String excursionNote;
    private String bonus;

    public BucketInfoView(Bucket bucket, Cruise cruise, String ticket, double price, String countryFrom, String countryTo, String cityFrom, String cityTo, String ship, List<Excursion> excursion, String excursionNote, String bonus) {
        this.bucket = bucket;
        this.cruise = cruise;
        this.ticket = ticket;
        this.price = price;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.ship = ship;
        this.excursion = excursion;
        this.excursionNote = excursionNote;
        this.bonus = bonus;
    }

    public String getExcursionNote() {
        return excursionNote;
    }

    public void setExcursionNote(String excursionNote) {
        this.excursionNote = excursionNote;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public List<Excursion> getExcursion() {
        return excursion;
    }

    public void setExcursion(List<Excursion> excursion) {
        this.excursion = excursion;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(String countryTo) {
        this.countryTo = countryTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }
}
