package com.caronic.jwisdom.jersey;

import com.caronic.jwisdom.Application;
import com.caronic.jwisdom.data.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by caronic on 2016/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
@ActiveProfiles(value = {"test", "web"})
public class UserControllerTest {

    private RestTemplate restTemplate =  new TestRestTemplate();

    @Test
    public void user() {
        User user = new User();
        user.setName("Nicholas");
        user.setAge(30);
        user.setGender("M");

        //Act
        URI uri = restTemplate.postForLocation("http://localhost:9000/user", user);

        //Assert
        ResponseEntity<User> responseEntity =  restTemplate.getForEntity(uri, User.class);
        User u = responseEntity.getBody();
        assertTrue(u.getName().equals("Nicholas"));
        assertTrue(u.getAge() == 30);
    }
}
