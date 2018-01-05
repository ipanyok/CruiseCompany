package dao.daofactory;

import entity.Excursion;
import java.util.List;

public interface IExcursionDAO {
    List<Excursion> findAll();
    List<Excursion> findByCruiseId(int idCruise);
    Excursion findByName(String name);
    Excursion findById(int id);
    void deleteExcursionById(int id);
    void deleteExcursion(String name);
    void createExcursion(Excursion excursion);
}
