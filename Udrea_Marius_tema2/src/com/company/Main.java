package com.company;

public class Main {

    public static void main(String[] args) {

        Client cust = new Client();
        GUI gUI = new GUI(cust);
        MyFrame frame = new MyFrame(cust);
        gUI.setDefaultCloseOperation(gUI.EXIT_ON_CLOSE);
        Server control = new Server(cust, frame, gUI);
    }

}

