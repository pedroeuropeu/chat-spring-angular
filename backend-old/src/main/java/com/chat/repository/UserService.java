package com.chat.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chat.model.User;

@Repository
public interface UserService extends JpaRepository<User, UUID>{

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);

}
