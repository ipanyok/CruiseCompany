package entity;

import java.io.Serializable;

public class Card implements Serializable {

    private int id;
    private int userID;
    private String identificator;
    private String cardNumber;

    public Card() {
    }

    public Card(int userID, String identificator, String cardNumber) {
        this.userID = userID;
        this.identificator = identificator;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getIdentificator() {
        return identificator;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (identificator != null ? !identificator.equals(card.identificator) : card.identificator != null)
            return false;
        return cardNumber != null ? cardNumber.equals(card.cardNumber) : card.cardNumber == null;
    }

    @Override
    public int hashCode() {
        int result = identificator != null ? identificator.hashCode() : 0;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "identificator='" + identificator + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                "}";
    }
}
