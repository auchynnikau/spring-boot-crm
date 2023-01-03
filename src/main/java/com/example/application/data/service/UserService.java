package com.example.application.data.service;

import com.example.application.data.entity.Users;
import com.example.application.data.entity.Role;
import com.example.application.data.repository.RoleRepository;
import com.example.application.data.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    public UserService(UsersRepository usersRepository, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    public List<Users> findAllUsers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return usersRepository.findAll();
        } else {
            return usersRepository.search(stringFilter);
        }
    }

    public void deleteUser(Users user) {
        usersRepository.delete(user);
    }

    public void saveUser(Users user) {
        if (user == null) {
            System.err.println("User is null. Are you sure you have connected your form to the application?");
            return;
        }
        usersRepository.save(user);
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
