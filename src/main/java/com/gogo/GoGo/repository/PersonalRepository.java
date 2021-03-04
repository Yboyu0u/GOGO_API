package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository
        extends JpaRepository<Personal, Long> , JpaSpecificationExecutor<Personal> {

    Personal save(Personal personal);

}
