package com.example.lab7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDY_GROUPS")
public class StudyGroup {

    /** Идентификатор учебной группы. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Название учебной группы. */
    @NotBlank(message = "Введите название группы")
    @Size(max = 20, message = "Название не длиннее 20 символов")
    @Column(name = "name", length = 20)
    private String name;

    /** Факультет учебной группы. */
    @NotBlank(message = "Введите факультет")
    @Size(max = 20, message = "Факультет не длиннее 20 символов")
    @Column(name = "faculty", length = 20)
    private String faculty;

    /** Курс обучения. */
    @NotNull(message = "Введите курс")
    @Min(value = 1, message = "Курс должен быть положительным")
    @Column(name = "course")
    private Integer course;
}