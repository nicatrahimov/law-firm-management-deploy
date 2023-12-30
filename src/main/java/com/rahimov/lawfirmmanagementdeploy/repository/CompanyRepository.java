package com.rahimov.lawfirmmanagementdeploy.repository;

import com.rahimov.lawfirmmanagementdeploy.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
