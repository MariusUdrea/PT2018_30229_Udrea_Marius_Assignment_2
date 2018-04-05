package com.company;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MyFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private JLabel maxC = new JLabel("Numarul maxim de case de marcat:");
    private JLabel startC = new JLabel("Numarul de case de marcat deschise:");
    private JLabel timpMin = new JLabel("Timp minim asteptare:");
    private JLabel timpMax = new JLabel("Timp maxim asteptare:");
    private JLabel intervalSimulare = new JLabel("Interval de simulare");

    private JTextField txtmaxC = new JTextField();
    private JTextField txtstartC = new JTextField();
    private JTextField txtTimpMin = new JTextField();
    private JTextField txtTimpMax = new JTextField();
    private JTextField intervalSimulareValue = new JTextField();

    private JButton but =  new JButton("OK");

    private Client cust;

    public MyFrame(Client client){

        cust = client;

        this.setLayout(null);
        this.setSize(400,300);

        maxC.setBounds(40, 40, 220, 25);
        startC.setBounds(40, 80, 220, 25);
        timpMin.setBounds(40, 120, 220, 25);
        timpMax.setBounds(40, 160, 220, 25);
        intervalSimulare.setBounds(40, 200, 220, 25);

        txtmaxC.setBounds(260, 40, 50, 25);
        txtstartC.setBounds(260, 80, 50, 25);
        txtTimpMin.setBounds(260, 120, 50, 25);
        txtTimpMax.setBounds(260, 160, 50, 25);
        intervalSimulareValue.setBounds(260, 200, 50, 25);

        but.setBounds(165, 225, 70, 20);



        this.add(maxC);
        this.add(startC);
        this.add(timpMin);
        this.add(timpMax);
        this.add(intervalSimulare);

        this.add(txtmaxC);
        this.add(txtstartC);
        this.add(txtTimpMin);
        this.add(txtTimpMax);
        this.add(intervalSimulareValue);

        this.add(but);
        this.setVisible(true);
    }

    public JTextField getmaxC(){
        return txtmaxC;
    }
    public JTextField getstartC(){
        return txtstartC;
    }
    public JTextField getTimpMin(){
        return txtTimpMin;
    }
    public JTextField getTimpMax(){
        return txtTimpMax;
    }
    public JTextField getIntervalSimulareValue(){
        return intervalSimulareValue;
    }

    public JButton getBut(){
        return this.but;
    }


    public void addButListener(ActionListener act){
        but.addActionListener(act);
    }
}











