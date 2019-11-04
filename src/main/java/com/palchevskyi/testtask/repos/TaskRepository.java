package com.palchevskyi.testtask.repos;

import com.palchevskyi.testtask.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskById(Long id);
    void deleteTaskById(Long id);
}
