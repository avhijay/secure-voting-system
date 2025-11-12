package com.votingSystem.secureVote.dsa;


import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class VerificationQueue {


    private final ConcurrentLinkedQueue<Long> verificationQueue = new ConcurrentLinkedQueue<>();

    public void enqueueVerification(Long userId) {
        verificationQueue.offer(userId);

    }
    public Long pollNextUser(){
        return verificationQueue.poll();
    }


    public void processNextVerification() {
        Long userId = verificationQueue.poll();
        if (userId != null) {
            System.out.println("Verifying user : " + userId);
        } else {
            System.out.println("No more users left for verificationQueue");
        }
    }

    public int getPendingCount() {
        return verificationQueue.size();
    }


    public boolean isEmpty() {
        return verificationQueue.isEmpty();
    }


    public Long peekNext() {
    return verificationQueue.peek();

    }




}
