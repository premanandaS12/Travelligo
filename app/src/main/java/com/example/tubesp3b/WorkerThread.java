package com.example.tubesp3b;

public class WorkerThread implements Runnable {

    public void startThread(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

    }
}
