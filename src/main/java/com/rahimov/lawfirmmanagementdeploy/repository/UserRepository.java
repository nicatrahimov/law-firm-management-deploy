package com.rahimov.lawfirmmanagementdeploy.repository;


import com.rahimov.lawfirmmanagementdeploy.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
  User findByUsername(String username);
  User findByEmail(String email);
}