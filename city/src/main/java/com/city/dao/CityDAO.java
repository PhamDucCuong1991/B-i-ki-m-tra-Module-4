package com.city.dao;

import com.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CityDAO {
    @Autowired
    EntityManager entityManager;

    public List<City> getAll() {
        String sql = "Select p from City p";
        List<City> cityList = entityManager.createQuery(sql).getResultList();
        return cityList;
    }
    //
    public void save(City city) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(city);
        txn.commit();
    }

    public void delete(City city) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(city);
        txn.commit();
    }
    //
    public void edit(City city) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(city);
        txn.commit();
    }

    public City findById(int id) {
        String sql = "Select p from City p where p.id = " + id;
        City city = (City) entityManager.createQuery(sql).getSingleResult();
        return city;
    }

    public String removeAccent(String s) {
        //removeAccent - hàm loại bỏ dấu tiếng Việt
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
    }

    public List<City> findListCityByName(String name) {
        List<City> newList = new ArrayList<>();
        String sql = "Select p from City p";
        List<City> cityList = entityManager.createQuery(sql).getResultList();
        for (City city : cityList) {
            if (removeAccent(city.getCity()).toUpperCase().contains(name)) {
                newList.add(city);
            }
        }
        return newList;
    }


}
