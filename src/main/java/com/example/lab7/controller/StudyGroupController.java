package com.example.lab7.controller;

import com.example.lab7.entity.StudyGroup;
import com.example.lab7.repository.StudyGroupRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class StudyGroupController {

    private final StudyGroupRepository studyGroupRepository;

    public StudyGroupController(
            StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    /** Страница списка учебных групп. */
    @GetMapping("/groups")
    public String listGroups(Model model) {
        model.addAttribute(
                "groups",
                studyGroupRepository.findAll(Sort.by("id"))
        );
        return "list-groups";
    }

    /** Форма добавления учебной группы. */
    @GetMapping("/groups/new")
    public String addGroupForm(Model model) {
        model.addAttribute("group", new StudyGroup());
        return "add-group-form";
    }

    /** Сохранение новой или изменённой группы. */
    @PostMapping("/groups/save")
    public String saveGroup(
            @Valid @ModelAttribute("group") StudyGroup group,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-group-form";
        }

        if (group.getId() != null) {
            findGroup(group.getId());
        }

        studyGroupRepository.save(group);
        return "redirect:/groups";
    }

    /** Форма изменения учебной группы. */
    @GetMapping("/groups/edit")
    public String showUpdateForm(
            @RequestParam("groupId") Integer groupId,
            Model model) {

        model.addAttribute("group", findGroup(groupId));
        return "add-group-form";
    }

    /** Удаление учебной группы. */
    @PostMapping("/groups/delete")
    public String deleteGroup(
            @RequestParam("groupId") Integer groupId) {

        StudyGroup group = findGroup(groupId);
        studyGroupRepository.delete(group);
        return "redirect:/groups";
    }

    /** Поиск группы с проверкой существования записи. */
    private StudyGroup findGroup(Integer id) {
        return studyGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Учебная группа не найдена"
                ));
    }
}