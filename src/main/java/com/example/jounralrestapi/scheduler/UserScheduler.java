package com.example.jounralrestapi.scheduler;

import com.example.jounralrestapi.cache.AppCache;
import com.example.jounralrestapi.entity.JournalEntry;
import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.repository.UserRepository;
import com.example.jounralrestapi.repository.UserRepositoryImpl;
import com.example.jounralrestapi.service.EmailService;
import com.example.jounralrestapi.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join("",filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(),"Sentiment for last 7 days",sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    //@Scheduled(cron = "* * * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}
