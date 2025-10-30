package dev.marshallBits.estim.services;

import dev.marshallBits.estim.dto.CreateUserDTO;
import dev.marshallBits.estim.dto.LoginResponseDTO;
import dev.marshallBits.estim.dto.SignupResponseDTO;
import dev.marshallBits.estim.models.User;
import dev.marshallBits.estim.repositories.UserRepository;
import dev.marshallBits.estim.security.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

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

    @Override
    public LoginResponseDTO authenticateUser(String email, String password) {

        User existUser = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario o contraseña incorrecto"));

        if (!passwordEncoder.matches(password, existUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(existUser.getUsername(), existUser.getRole().name());

        return LoginResponseDTO.builder()
                .id(existUser.getId())
                .username(existUser.getUsername())
                .email(existUser.getEmail())
                .token(token)
                .build();
    }
}
