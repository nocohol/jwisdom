package com.caronic.jwisdom.data.repository;

import com.caronic.jwisdom.DataJpaApplicationTest;
import com.caronic.jwisdom.data.domain.CarInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;

/**
 * Created by caronic on 2016/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DataJpaApplicationTest.class)
public class JwisdomRepositoryTest {

    @Autowired
    CarInfoRepository carInfoRepository;

    @Test
    public void saveCarInfoTest() throws Exception{
        CarInfo newCarInfo = new CarInfo(new URL("test.png"), 5.0f);
        newCarInfo = carInfoRepository.save(newCarInfo);
        System.out.println(newCarInfo.getId());
    }

}
