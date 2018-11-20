package org.marcinski.chickenHouse.configuration;

import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.mapper.UserMapper;
import org.marcinski.chickenHouse.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;

public class ApplicationUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public ApplicationUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        org.marcinski.chickenHouse.entity.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found"));

        UserDto userDto = UserMapper.INSTANCE.mapTo(user);
        String role = userDto.getRoleDto().getRole();

        return User.builder()
                .username(userDto.getEmail())
                .password(userDto.getPassword())

                .roles(role)
                .build();
    }
}
