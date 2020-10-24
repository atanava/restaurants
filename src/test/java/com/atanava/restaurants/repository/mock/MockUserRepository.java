package com.atanava.restaurants.repository.mock;

import com.atanava.restaurants.model.User;
import com.atanava.restaurants.repository.UserRepository;

import java.util.List;

public class MockUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getWithVotes(int id) {
        return null;
    }
}
