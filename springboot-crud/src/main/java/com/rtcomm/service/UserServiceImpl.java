package com.rtcomm.service;

import com.rtcomm.service.exception.UserAlreadyExistsException;
import com.rtcomm.domain.User;
import com.rtcomm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional
    public String delete(@NotNull @Valid final String id) {
        LOGGER.debug("deleting user by id = {}", id);
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new UserAlreadyExistsException(
                    String.format("There are no user with id=%s", id));
        }
        repository.delete(id);
        return "user id: "+id+" has been deleted" ;
    }

    @Override
    @Transactional
    public String update(@NotNull @Valid final User user) {
        LOGGER.debug("updating user by id = {}", user.getId());

        if (repository.exists(user.getId()) == false) {
            throw new UserAlreadyExistsException(
                    String.format("There are no user with id=%s", user.getId()));
        }
        //repository.delete(user.getId());
        repository.save(user);
        return "user id: "+user.getId()+" has been updated " + user.toString();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

}
