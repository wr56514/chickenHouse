package org.marcinski.chickenHouse.service;

import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.entity.Role;
import org.marcinski.chickenHouse.entity.User;
import org.marcinski.chickenHouse.mapper.UserMapper;
import org.marcinski.chickenHouse.repository.RoleRepository;
import org.marcinski.chickenHouse.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<UserDto> findUserByEmail(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        UserDto userDto = null;
        if (userByEmail.isPresent()) {
            userDto = UserMapper.INSTANCE.mapTo(userByEmail.get());
        }
        return Optional.ofNullable(userDto);
    }

    public void saveUser(UserDto userDto) {
        if (userDto != null) {
            String email = userDto.getEmail();
            if (!userRepository.findByEmail(email).isPresent()) {
                User user = createNewUser(userDto);
                userRepository.save(user);
            }
        }
    }

    private User createNewUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapTo(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(true);

        Role userRole = roleRepository.findByRole("USER");
        user.setRole(userRole);
        return user;
    }
}
