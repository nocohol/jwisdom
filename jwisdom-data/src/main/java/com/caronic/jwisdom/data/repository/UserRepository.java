package com.caronic.jwisdom.data.repository;

import com.caronic.jwisdom.data.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by caronic on 2016/5/8.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
