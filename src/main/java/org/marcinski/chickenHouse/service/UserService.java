package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.model.Role;
import org.marcinski.chickenHouse.model.User;
import org.marcinski.chickenHouse.repository.RoleRepository;
import org.marcinski.chickenHouse.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("There is no user with given email"));
    }

    public void saveUser(User user){
        if (!userRepository.findByEmail(user.getEmail()).isPresent()){
            user.setPassword(encoder.encode(user.getPassword()));
            user.setActive(true);

            Role userRole = roleRepository.findByRole("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);
        }
    }
}
