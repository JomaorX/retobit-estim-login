package dev.marshallBits.estim.services;

import dev.marshallBits.estim.dto.CreateUserDTO;
import dev.marshallBits.estim.dto.SignupResponseDTO;

public interface UserService {

    SignupResponseDTO registerUser(CreateUserDTO createUserDTO);

    Object authenticateUser(String a, String b);
}
