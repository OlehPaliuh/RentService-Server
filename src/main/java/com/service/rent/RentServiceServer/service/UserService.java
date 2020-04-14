package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.User;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import com.service.rent.RentServiceServer.repository.UserRepo;
import com.service.rent.RentServiceServer.repository.UserRoleRepo;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    public void createUser(User user) throws Exception {
        if (getByUsername(user.getUsername()) != null) {
            throw new Exception(String.format("User with username %s already exist.", user.getUsername()));
        }
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
//        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(userRoleRepo.getUserRoleByName(RoleName.ADMIN));
        newUser.setDisabled(false);
        newUser.setRefreshTokenKey(jwtTokenUtil.generateTokenKey());
        newUser.setLocked(false);
        saveUser(newUser);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getByUsername(String username) {
        return userRepo.getUserByUsername(username).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepo.delete(getById(id));
    }

    public int updateUserRefreshToken(String refreshTokenKey, Long id) {
        return userRepo.updateUserRefreshToken(refreshTokenKey, id);
    }
}
