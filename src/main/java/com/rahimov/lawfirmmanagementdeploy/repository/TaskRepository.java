package com.rahimov.lawfirmmanagementdeploy.repository;

import com.rahimov.lawfirmmanagementdeploy.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
