package dev.marshallBits.estim.services;

import dev.marshallBits.estim.dto.CreateUserDTO;
import dev.marshallBits.estim.dto.SignupResponseDTO;
import dev.marshallBits.estim.models.User;
import dev.marshallBits.estim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SignupResponseDTO registerUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByUsername(createUserDTO.getUsername()) || userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        // TODO: Mandar la info del nuevo user pero sin la contraseña
        return SignupResponseDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }
}
