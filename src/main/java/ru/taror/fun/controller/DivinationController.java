package ru.taror.fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.taror.fun.service.Divination;
import ru.taror.fun.service.DivinationWithRiderWaiteDeck;

@RestController
public class DivinationController {

    @Autowired
    DivinationWithRiderWaiteDeck divinationWithRiderWaiteDeck;

    @ResponseBody
    @GetMapping("/raider-waite-deck/")
    public Divination.DivinationResult divinationWithRaiderWaiteDeck(@RequestParam("request") String request) {
        return divinationWithRiderWaiteDeck.divination(request);
    }
}
