package com.rtcomm.service;

import com.rtcomm.domain.User;

import java.util.List;

public interface UserService {

    //User create(User user);
    User save(User user);

    //User read(String id);
    String update(User user);
    String delete(String id);

    List<User> getList();

}
