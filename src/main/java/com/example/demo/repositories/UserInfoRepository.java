package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.User;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);
}