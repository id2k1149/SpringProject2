package org.id2k1149.controllers;

import org.id2k1149.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonsController {

    private PersonDAO personDAO;

    @Autowired
    public PersonsController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String people(Model model) {
        model.addAttribute("people", personDAO.allPeople());
        return "persons/people";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") int id,
                         Model model) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "persons/person";
    }
}
