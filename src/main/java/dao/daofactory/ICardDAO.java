package dao.daofactory;

import entity.Card;
import java.util.List;

public interface ICardDAO {
    List<Card> findAll();
    List<Card> findByUserId(int idUser);
    Card findByNumber(String number);
    Card findById(int id);
    void deleteCardById(int id);
    void deleteCard(String number);
    void createCard(Card card);
}
