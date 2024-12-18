package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRegistrationService implements UserDetailsService {

    private final Map<String, User> users = new HashMap<>();

    public void addPerson(User u) {
        if (users.containsKey(u.getUsername())) return;
        users.put(u.getUsername(), u);
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of());
    }
}
