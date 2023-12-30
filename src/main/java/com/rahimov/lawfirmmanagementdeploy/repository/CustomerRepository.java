package com.rahimov.lawfirmmanagementdeploy.repository;

import com.rahimov.lawfirmmanagementdeploy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
