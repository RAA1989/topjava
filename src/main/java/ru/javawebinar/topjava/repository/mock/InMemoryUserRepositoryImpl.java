package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        if (repository.get(id)==null) return false;
        else {
            repository.remove(id);
            return true;
        }
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isEnabled()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> res = ((List<User>) repository.values());
        return res;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        for (Map.Entry<Integer,User> entry : repository.entrySet()){
            if (entry.getValue().getEmail().equals(email)){
                return entry.getValue();
            }
        }
        return null;
    }
}
