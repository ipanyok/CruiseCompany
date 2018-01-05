package dao.daofactory;

import entity.Order;
import java.util.List;

public interface IOrderDAO {
    List<Order> findAll();
    Order findById(int id);
    List<Order> findByUserId(int idUser);
    void deleteOrderById(int id);
    String createOrder(Order order);
    String updateBonus(int orderID, String value);
}
