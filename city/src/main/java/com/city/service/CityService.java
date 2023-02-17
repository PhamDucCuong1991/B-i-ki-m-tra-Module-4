package com.city.service;

import com.city.dao.CityDAO;
import com.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityService {
    @Autowired
    CityDAO cityDAO;

    public List<City> getAll() {
        return cityDAO.getAll();
    }

    public void delete(int id){
        cityDAO.delete(cityDAO.findById(id));
    }

    public void edit(City city){
        cityDAO.edit(city);
    }

    public void save(City city){
        cityDAO.save(city);
    }

    public List<City> findListByName(String name) {
        name = name.trim();
        name = name.toUpperCase();
        name = cityDAO.removeAccent(name);
        return cityDAO.findListCityByName(name);
    }

    public int findIndexById(int id){
        for (City city : getAll()) {
            if (city.getId()==id){
                return getAll().indexOf(city);
            }
        }
        return -1;
    }

    public City findCityById(int id) {
        for (City city : getAll()) {
            if (city.getId()==id){
                return city;
            }
        }
        return null;
    }
}
