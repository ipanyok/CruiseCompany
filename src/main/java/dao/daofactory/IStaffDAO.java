package dao.daofactory;

import entity.Staff;
import java.util.List;

public interface IStaffDAO {
    List<Staff> findAll();
    List<Staff> findByShipId(int idShip);
    Staff findByName(String name);
    Staff findById(int id);
    void deleteStaffById(int id);
    void deleteStaff(String name);
    void createStaff(Staff staff);
}
