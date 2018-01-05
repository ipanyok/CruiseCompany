package dao.daofactory;

import entity.Country;
import java.util.List;

public interface ICountryDAO {
    List<Country> findAll();
    Country findById(int id);
    Country findByName(String name);
    void deleteCountryById(int id);
    void deleteCountry(String name);
    void createCountry(Country country);
}
