package com.caronic.jwisdom.data.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caronic.jwisdom.data.domain.CarInfo;

/**
 * Created by caronic on 2016/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(DataJpaApplicationTest.class)
@SpringBootTest
@ActiveProfiles("test")
public class JwisdomRepositoryTest {

    @Autowired
    CarInfoRepository carInfoRepository;

    @Test
    public void saveCarInfoTest() throws Exception{
        CarInfo newCarInfo = new CarInfo("test.png", 5.0f);
        newCarInfo = carInfoRepository.save(newCarInfo);
        assertTrue(newCarInfo.getId() != null);
    }

    @Test
    public void updateTest() {
        int result = carInfoRepository.update("test2.png", 1L);
        CarInfo carInfo = carInfoRepository.findOne(1L);
        assertTrue(result > 0);
        assertTrue("test2.png".equalsIgnoreCase(carInfo.getImageUrl()));
    }

}
