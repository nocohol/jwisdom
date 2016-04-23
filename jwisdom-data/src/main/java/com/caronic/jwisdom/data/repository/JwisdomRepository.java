package com.caronic.jwisdom.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by caronic on 2016/3/26.
 */
@NoRepositoryBean
public interface JwisdomRepository<T, ID extends Serializable> extends Repository<T, ID> {

    enum REPOSITORY_TYPE {
        H2, MySql, Oracle, MongoDB, GemFire;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    T findOne(ID id);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */
    <S extends T> S save(S entity);

}
