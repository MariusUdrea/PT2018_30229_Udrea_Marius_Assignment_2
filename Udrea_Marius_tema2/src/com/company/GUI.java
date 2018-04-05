package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame{

    private static final long serialVersionUID = 1L;
    private Client customer;
    private JButton start = new JButton("Start Sim");//incepe sinularea
    private JButton statistica = new JButton("Statistica");//afiseaza la final timpul mediu de asteptare
    private JPanel PanSt  = new JPanel();
    private JButton newClient = new JButton("Client Nou");//adaugam client nou

    private JTextArea textArea = new JTextArea();
    private JTextArea comment = new JTextArea();
    private JTextField clock = new JTextField();

    Font font = textArea.getFont();
    Font fo = comment.getFont();

    public GUI(Client client){

        customer = client;

        this.setSize(1000,800);
        this.setTitle("Magazin");
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0));
        textArea.setFont(new Font("Serif",Font.PLAIN,30));
        textArea.setBackground(new Color(204, 204, 255));
        comment.setFont(new Font("Serif",Font.ITALIC,15));


        PanSt.setBounds(0,0,120,430);
        PanSt.setLayout(null);
        start.setBounds(15, 250, 100, 15);
        statistica.setBounds(15, 300, 100, 15);
        newClient.setBounds(15,200,100,15);
        newClient.setEnabled(false);
        PanSt.add(newClient);
        PanSt.add(start);
        PanSt.add(statistica);
        clock.setBounds(30, 400, 60, 20);
        PanSt.add(clock);
        JScrollPane scrollPane = new JScrollPane(comment);
        scrollPane.setBounds(165,15,this.getWidth()- 180, this.getHeight()-400);

        JScrollPane scrollPanes = new JScrollPane(textArea);
        scrollPanes.setBounds(15, 430, 970, 320);

        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        textArea.setForeground(new Color(0,0,0));

        this.add(PanSt);
        this.add(scrollPane);
        this.add(scrollPanes);

        this.setVisible(false);
    }
    //
    public JButton getStatistica(){
        return this.statistica;
    }

    public JButton getStart(){
        return this.start;
    }


    public JTextArea getTA(){
        return this.textArea;
    }
    public JTextArea getComm(){
        return this.comment;
    }
    public JButton getBut(){
        return this.newClient;
    }

    public JTextField getCLK(){
        return clock;
    }

    public void addButstatListener(ActionListener act){
        statistica.addActionListener(act);

    }

    public void addMenuListener(ActionListener act){
        start.addActionListener(act);
    }

    public void addButListener(ActionListener act){
        newClient.addActionListener(act);
    }



}
