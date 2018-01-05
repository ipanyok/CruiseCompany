package dao.daofactory;

import entity.Ship;
import java.util.List;

public interface IShipDAO {
    List<Ship> findAll();
    Ship findById(int id);
    Ship findByName(String name);
    void deleteShipById(int id);
    void deleteShip(String name);
    void createShip(Ship ship);
}
