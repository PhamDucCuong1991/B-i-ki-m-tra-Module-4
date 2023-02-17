package com.city.controller;

import com.city.model.City;
import com.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {
    @Autowired
    CityService cityService;



    @GetMapping("/home")
    public String showEmployeesList(Model model) {
        model.addAttribute("cityList", cityService.getAll());
        return "home";
    }


    @PostMapping("/search")
    public String search(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
        model.addAttribute("employees", cityService.findListByName(name));
        return "home";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        cityService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/create")
    public String showCreate() {
        return "create";
    }

    @PostMapping("/create")
    public String create(City city) {
        cityService.save(city);
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        City city = cityService.findCityById(id);
        model.addAttribute("city", city);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(City city) {
        int index = cityService.findIndexById(city.getId());
        cityService.getAll().set(index, city);
        cityService.edit(city);
        return "redirect:/home";
    }

    @GetMapping("/information")
    public String showInfor(@RequestParam int id, Model model) {
        City city = cityService.findCityById(id);
        model.addAttribute("city", city);
        return "information";
    }

}
