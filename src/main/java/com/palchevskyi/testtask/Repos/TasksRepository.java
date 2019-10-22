package com.palchevskyi.testtask.Repos;

import com.palchevskyi.testtask.Domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {
    Task findTaskById(Long id);
    void deleteTaskById(Long id);
}
