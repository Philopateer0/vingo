package com.education.user.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.education.user.Model.User;

@Service
public class UserRegistrationService {

     Map<Integer, User> Users = new HashMap<>();
    
        public Boolean addPerson(User u) {
            if(Users.get(u.getId()) != null) {
                return false;
            }
            Users.put(u.getId(), u);
            return true;
        }
    
        
        public Boolean deleteUser(int id) {
            if(Users.get(id) == null) {
                return false;
            }
            Users.remove(id);
            return true;
        }
    
        
        public User getUser(int id) {
            return Users.get(id);
        }
    
        public User isUser(int id , String password){
            User u ;
            if(Users.get(id) != null) {
                u = Users.get(id);
                if(u.getPassword().equals(password))
                    return u;
            }

            return null;
    }

     public List<User> getAllUsers() {
        Set<Integer> ids = Users.keySet();
        List<User> u = new ArrayList<>(ids.size());
        for(Integer id : ids){
            u.add(Users.get(id));
        }
        return u;
    }
}
