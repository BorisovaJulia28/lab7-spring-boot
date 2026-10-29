package com.example.lab7.repository;

import com.example.lab7.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository
        extends JpaRepository<StudyGroup, Integer> {
}