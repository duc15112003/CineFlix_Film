package com.cineflix.vn.service.modelService.serviceiplm;

import com.cineflix.vn.model.User;
import com.cineflix.vn.model.dao.UserDao;
import com.cineflix.vn.model.dto.request.UserRequest;
import com.cineflix.vn.model.dto.response.UserResponse;
import com.cineflix.vn.model.mapper.UserMapper;
import com.cineflix.vn.service.modelService.service.UserService;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceIplm implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userDao.findAll();
        return UserMapper.INSTANCE.toListUserResponse(users);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User us = UserMapper.INSTANCE.TranferToUserRequestfromUser(userRequest);
        if(CheckExist(us)){
           return new UserResponse();
        }else{
            us.setCreateAt(LocalDate.now());
            userDao.save(us);
        }
        return UserMapper.INSTANCE.toUserResponse(us);
    }



    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User us = UserMapper.INSTANCE.TranferToUserRequestfromUser(userRequest);
        userDao.save(us);
        return UserMapper.INSTANCE.toUserResponse(us);
    }

    @Override
    public Boolean deleteUser(String username) {
        User us = userDao.findByUsername(username);
        us.setDeletedAt(LocalDate.now());
        userDao.save(us);
        return CheckStatus(username);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean CheckExist(User user){
        boolean ret = false;
        ret = userDao.existsByUsername(user.getUsername());
        return ret;
    }

    public boolean CheckStatus(String username){
        boolean ret = false;
        User us = userDao.findByUsername(username);
        ret = us.getDeletedAt() != null;
        return ret;
    }

}
