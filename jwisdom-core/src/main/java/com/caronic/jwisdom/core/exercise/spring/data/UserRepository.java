package com.caronic.jwisdom.core.exercise.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by caronic on 2016/10/5.
 */
@Repository
public class UserRepository {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public void saveUser(final User user) {
        redisTemplate.execute((RedisConnection connection) -> {
            connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
                    redisTemplate.getStringSerializer().serialize(user.getName()));
            return null;
        });
    }

    public User getUser(final long id) {
        return redisTemplate.execute((RedisConnection connection) -> {
            byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
            if (connection.exists(key)) {
                byte value[] = connection.get(key);
                String name = redisTemplate.getStringSerializer().deserialize(value);
                User user = new User();
                user.setId(id);
                user.setName(name);
                return user;
            }
            return null;
        });
    }

}
