package services;

import dao.daofactory.*;
import entity.Country;
import entity.Cruise;
import services.beans.CruiseInfoView;
import services.beans.PagesBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainInfoService {

    private ICruiseDAO cruiseDAO;
    private ICountryDAO countryDAO;
    private IShipDAO shipDAO;

    public MainInfoService() {
        AbstractFactory factory = new DAOFactory();
        cruiseDAO = factory.createCruiseDAO();
        countryDAO = factory.createCountryDAO();
        shipDAO = factory.createShipDAO();
    }

    /**
     * Find all cruises with parameters
     * */
    public List<CruiseInfoView> findAllCruises(String countryFrom, String countryTo, String category, String date) {
        List<CruiseInfoView> listCruiseInfo = new ArrayList<>();
        if (countryFrom.equals("") && countryTo.equals("") && category.equals("") && date.equals("")) {
            for (Cruise buf : cruiseDAO.findAll()) {
                listCruiseInfo.add(new CruiseInfoView(buf,
                        countryDAO.findById(buf.getCityFromID()).getName(),
                        countryDAO.findById(buf.getCityToID()).getName(),
                        countryDAO.findById(buf.getCityFromID()).getCity(),
                        countryDAO.findById(buf.getCityToID()).getCity(),
                        shipDAO.findById(buf.getShipID()).getName()));
            }
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("countryFrom", countryFrom);
            map.put("countryTo", countryTo);
            map.put("category", category);
            map.put("date", date);
            List<CruiseInfoView> allList = cruiseDAO.findWithParameters(map);
            for (CruiseInfoView elem : allList) {
                listCruiseInfo.add(elem);
            }
        }
        return listCruiseInfo;
    }

    public List<Country> findAllCountries() {
        return countryDAO.findAll();
    }

    public List<CruiseInfoView> printResult(List<CruiseInfoView> list, int start, int total) {
        int lenght = start + total;
        List<CruiseInfoView> result = new ArrayList<>();
        for (int i = start; i < lenght; i++) {
            if (i < list.size()) {
                result.add(list.get(i));
            } else {
                break;
            }
        }
        return result;
    }
}
