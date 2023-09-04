package com.ssafy.triplet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.triplet.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
