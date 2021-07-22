package org.id2k1149.controllers;

import org.id2k1149.dao.PersonDAO;
import org.id2k1149.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    // Form for new Person #1
    @GetMapping("/new1")
    public String newPerson1(Model model) {
        model.addAttribute("person", new Person());
        return "persons/new1";
    }

    // Form for new Person #2
    @GetMapping("/new2")
    public String newPerson2(@ModelAttribute("person") Person person) {
        return "persons/new2";
    }

    // create new Person
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "persons/new2";
        }
        personDAO.save(person);
        return "redirect:/persons";
    }

    // edit person
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "persons/edit";
    }

    // update person
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "persons/edit";
        }
        personDAO.update(id, person);
        return "redirect:/persons";
    }

    // delete person
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/persons";
    }

}
