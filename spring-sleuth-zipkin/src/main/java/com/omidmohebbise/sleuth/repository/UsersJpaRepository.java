package com.omidmohebbise.sleuth.repository;

import com.omidmohebbise.sleuth.model.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<UserEO, Integer> {
}
