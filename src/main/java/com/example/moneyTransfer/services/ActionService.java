package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.repositories.ActionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private final ActionRepository actionRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Transactional
    public void save(Action action) {
        actionRepository.save(action);
    }
}
