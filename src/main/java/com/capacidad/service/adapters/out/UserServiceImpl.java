package com.capacidad.service.adapters.out;

import com.capacidad.service.adapters.out.persistence.UserMapper;
import com.capacidad.service.adapters.out.persistence.UserRepository;
import com.capacidad.service.domain.User;
import com.capacidad.service.domain.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);  // Cambia la referencia por una lambda
    }
}

