package dao.daofactory;

import entity.Cruise;
import services.MainInfoService;
import services.beans.CruiseInfoView;

import java.util.HashMap;
import java.util.List;

public interface ICruiseDAO {
    List<Cruise> findAll();
    Cruise findById(int id);
    Cruise findByName(String name);
    List<Cruise> findByDestination(String city_from, String city_to);
    void deleteCruiseById(int id);
    void deleteCruise(String name);
    void createCruise(Cruise cruise);
    List<CruiseInfoView> findWithParameters(HashMap<String, String> map);
}
