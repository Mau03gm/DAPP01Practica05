package org.uv.DAPP01Practica05.controllers;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uv.DAPP01Practica05.entity.User;
import org.uv.DAPP01Practica05.entity.UserRepository;

/**
 *
 * @author yodoeaoffi06
 */

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public User get(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }
    
    public User put(Long id, User user) {
        User actualUser = get(id);
        if(actualUser != null) {
            actualUser.setUsername(user.getUsername());
            actualUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(actualUser);
            return actualUser;
        }
        
        return null;
    }
    
    public boolean delete(Long id) {
        User user = get(id);
        if(user != null) {
            userRepository.delete(user);
            return true;
        }
        
        return false;
        
    }
}
