package services.beans;

import entity.Cruise;

/**
 * This view is used to show correct information in main page.
 * By default many rows there are ID's of entities
 *
 * */

public class CruiseInfoView {
        private Cruise cruise;
        private String countryFrom;
        private String countryTo;
        private String cityFrom;
        private String cityTo;
        private String ship;

        public CruiseInfoView(Cruise cruise, String countryFrom, String countryTo, String cityFrom, String cityTo, String ship) {
            this.cruise = cruise;
            this.countryFrom = countryFrom;
            this.countryTo = countryTo;
            this.cityFrom = cityFrom;
            this.cityTo = cityTo;
            this.ship = ship;
        }

        public String getShip() {
            return ship;
        }

        public void setShip(String ship) {
            this.ship = ship;
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

        public Cruise getCruise() {
            return cruise;
        }

        public void setCruise(Cruise cruise) {
            this.cruise = cruise;
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
}
