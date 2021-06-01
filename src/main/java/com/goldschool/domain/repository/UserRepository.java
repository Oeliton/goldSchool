package com.goldschool.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.goldschool.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
