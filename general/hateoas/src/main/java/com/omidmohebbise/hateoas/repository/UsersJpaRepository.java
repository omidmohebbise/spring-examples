package com.omidmohebbise.hateoas.repository;

import com.omidmohebbise.hateoas.model.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<UserEO, Integer> {
}
