package com.fase2.meta.service;

import com.fase2.meta.dto.LoginDTO;
import com.fase2.meta.dto.UserDTO;
import com.fase2.meta.model.User;
import com.fase2.meta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @CacheEvict(cacheNames = "goals", allEntries = true)
    public Boolean save(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
           return  false;
        }
        userRepository.save(user);
        return true;
    }

    public UserDTO findByLogin(LoginDTO loginDTO) {
        User user = userRepository.findByEmailAndPassword(loginDTO.email(), loginDTO.password());
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getEmail(), user.getName(), user.getId());
    }
    public List<User> findAll () {
        return userRepository.findAll();
    }
    public Optional<User> findById(String idUser) {
        return userRepository.findById(idUser);
    }
}
