package com.caronic.jwisdom.data.repository;

import com.caronic.jwisdom.data.domain.CarInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by caronic on 2016/3/28.
 */
@Transactional
public interface CarInfoRepository extends JwisdomRepository<CarInfo, Long> {
    @Modifying
    @Query("update CarInfo c set c.imageUrl=?1 where c.id=?2")
    int update(String imageUrl, Long id);
}
