package com.rahimov.lawfirmmanagementdeploy.repository;

import com.rahimov.lawfirmmanagementdeploy.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case,Long> {

}
