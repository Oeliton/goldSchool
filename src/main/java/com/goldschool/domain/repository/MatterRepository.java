package com.goldschool.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.goldschool.domain.model.Matter;

public interface MatterRepository extends JpaRepository<Matter, Long>{

}
