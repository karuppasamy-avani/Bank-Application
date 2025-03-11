package com.bankofindia.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofindia.user_service.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
