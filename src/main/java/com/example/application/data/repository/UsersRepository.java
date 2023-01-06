package com.example.application.data.repository;

import com.example.application.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("select u from Users u where lower(u.login) like lower(concat('%', :searchTerm, '%'))")
    List<Users> search(@Param("searchTerm") String searchTerm);
}
