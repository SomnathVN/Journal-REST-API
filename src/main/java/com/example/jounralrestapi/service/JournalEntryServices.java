package com.example.jounralrestapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.jounralrestapi.entity.JournalEntry;
import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.repository.JournalEntryRepository;

@Component
public class JournalEntryServices {

    private static final Logger log = LoggerFactory.getLogger(JournalEntryServices.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;
    
    @Transactional
    public void saveEntryWithJournal(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            //user.setUserName(null); //for transactional check
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("Exception", e);
            throw new RuntimeException("An error occured while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    public List<JournalEntry>  getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId journalEntryId) {
        return journalEntryRepository.findById(journalEntryId);
    }

    @Transactional
    public boolean deleteById(ObjectId journalEntryId, String userName) {
        boolean removed = false;
        try{
            User user  = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(journalEntryId));
            if(removed) {
                journalEntryRepository.deleteById(journalEntryId);
                userService.saveNewEntry(user);
            }
        }catch(Exception e){
            log.error("Exception", e);
            System.err.println("Exception occured while deleting the journal entry");
        }
        return removed;
    }
}
