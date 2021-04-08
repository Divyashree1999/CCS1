package com.cg.css.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.css.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName(String username);

	public User findByUserNameAndPassword(String username, String password);
}
