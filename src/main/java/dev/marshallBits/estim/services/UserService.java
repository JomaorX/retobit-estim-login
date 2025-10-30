package dev.marshallBits.estim.services;

import dev.marshallBits.estim.dto.CreateUserDTO;
import dev.marshallBits.estim.dto.LoginResponseDTO;
import dev.marshallBits.estim.dto.SignupResponseDTO;

public interface UserService {

    SignupResponseDTO registerUser(CreateUserDTO createUserDTO);

    LoginResponseDTO authenticateUser(String email, String password);
}
