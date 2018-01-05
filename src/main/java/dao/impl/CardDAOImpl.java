package dao.impl;

import connection.MyDataSource;
import dao.daofactory.Utils;
import dao.daofactory.ICardDAO;
import entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDAOImpl extends Utils implements ICardDAO {

    private static final Logger logger = Logger.getLogger(CardDAOImpl.class.getName());
    private static final String FIND_ALL_CARDS_QUERY = "SELECT * FROM CRUISE.CARD";
    private static final String FIND_CARDS_BY_USER_ID_QUERY = "SELECT * FROM CRUISE.CARD WHERE ID_USER = ?";
    private static final String FIND_CARDS_BY_ID_QUERY = "SELECT * FROM CRUISE.CARD WHERE ID = ?";
    private static final String FIND_CARDS_BY_NUMBER_QUERY = "SELECT * FROM CRUISE.CARD WHERE CARD_NUMBER = ?";
    private static final String DELETE_CARDS_BY_ID_QUERY = "DELETE FROM CRUISE.CARD WHERE ID = ?";
    private static final String DELETE_CARDS_BY_NUMBER_QUERY = "DELETE FROM CRUISE.CARD WHERE CARD_NUMBER = ?";
    private static final String MAX_CARDS_ID_QUERY = "SELECT max(id) FROM CRUISE.CARD";
    private static final String CREATE_CARDS_QUERY = "INSERT INTO CRUISE.CARD VALUES(?, ?, ?, ?)";

    @Override
    public List<Card> findAll() {
        List<Card> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CARDS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Card card = new Card();
                    card.setId(resultSet.getInt("id"));
                    card.setUserID(resultSet.getInt("id_user"));
                    card.setIdentificator(resultSet.getString("identificator"));
                    card.setCardNumber(resultSet.getString("card_number"));
                    list.add(card);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return list;
    }

    @Override
    public List<Card> findByUserId(int idUser) {
        List<Card> cardList = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARDS_BY_USER_ID_QUERY);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Card card = new Card();
                    card.setId(resultSet.getInt("id"));
                    card.setUserID(resultSet.getInt("id_user"));
                    card.setIdentificator(resultSet.getString("identificator"));
                    card.setCardNumber(resultSet.getString("card_number"));
                    cardList.add(card);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return cardList;
    }

    @Override
    public Card findByNumber(String number) {
        Card card = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARDS_BY_NUMBER_QUERY);
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    card = new Card();
                    card.setId(resultSet.getInt("id"));
                    card.setUserID(resultSet.getInt("id_user"));
                    card.setIdentificator(resultSet.getString("identificator"));
                    card.setCardNumber(resultSet.getString("card_number"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return card;
    }

    @Override
    public Card findById(int id) {
        Card card = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARDS_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    card = new Card();
                    card.setId(resultSet.getInt("id"));
                    card.setUserID(resultSet.getInt("id_user"));
                    card.setIdentificator(resultSet.getString("identificator"));
                    card.setCardNumber(resultSet.getString("card_number"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return card;
    }

    @Override
    public void deleteCardById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            String cardNumber = findById(id).getCardNumber();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARDS_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Card " + cardNumber + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void deleteCard(String number) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARDS_BY_NUMBER_QUERY);
            preparedStatement.setString(1, number);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Card " + number + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void createCard(Card card) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CARDS_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_CARDS_ID_QUERY) + 1);
            preparedStatement.setInt(2, card.getUserID());
            preparedStatement.setString(3, card.getIdentificator());
            preparedStatement.setString(4, card.getCardNumber());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Card " + card.getCardNumber() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
}
