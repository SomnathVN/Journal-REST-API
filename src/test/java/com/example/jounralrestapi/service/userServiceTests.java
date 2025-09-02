package com.example.jounralrestapi.service;

import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userServiceTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void beforeAll() {}
    @BeforeEach
    public void beforeEach() {}
    @AfterAll
    public static void afterAll() {}
    @AfterEach
    public void afterEach() {}

    @Disabled
    @Test
    public void findByUserName(){
        //assertEquals(4,2+2);
        //assertNotNull(userRepository.findByUserName("somnathnalawade"));
        User user =  userRepository.findByUserName("somnathnalawade");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "1,2,2",
            "5,7,35"
    })
    public void sampleParameterizedTest(int a, int b, int expected){
        assertEquals(expected,a*b);
    }

    @ParameterizedTest
    //@ValueSource(strings = {
    @CsvSource({
            "somnathnalawade",
            "noah_204",
            "somnathnalawade2003"
    })
    public void sampleParameterizedTest1(String userName){
        assertNotNull(userRepository.findByUserName(userName));
        //assertNotNull(userRepository.findByUserName(userName),"Failed for " + userName);
    }
}
