package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Team entity.
 */
@SuppressWarnings("unused")
public interface TeamRepository extends JpaRepository<Team,Long> {

}
