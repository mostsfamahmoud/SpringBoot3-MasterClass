package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

    @Override
    public String getFortune() {

        // Simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Return a fortune
        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean activateExp) {

        if (activateExp) {
            throw new RuntimeException("Major Accident! High way is closed!");
        }

        // Return a fortune
        return getFortune();
    }
}
