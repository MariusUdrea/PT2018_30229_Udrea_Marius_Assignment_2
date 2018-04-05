package com.company;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Random;

public class Server extends Thread implements ActionListener, Runnable {
    Casa[] rand = new Casa[100];
    Thread clock;
    private int maxTime;
    private int minTime;
    private int maxCase, startCase, crtCase;
    private int intervalSimulare;
    private int count = 0;
    private int sec = 0, min = 0, secunde = 0;
    private boolean isFull = false;
    private Client cust;
    private MyFrame view;
    private GUI gUI;

    public Server(Client client, MyFrame gui1, GUI gui2) {
        cust = client;
        view = gui1;
        gUI = gui2;
        view.addButListener(this);
        gUI.addMenuListener(this);
        gUI.addButListener(this);
        gUI.addButstatListener(this);
    }

    public String getTimeText() {
        return (min + ":" + sec);
    }

    public int casaChoose() {
        int minim = 100000;
        int index = 1;
        for (int i = 1; i <= crtCase; i++) {
            if (rand[i].getLast() != null) {
                cust = rand[i].getLast().info;
                if (cust.getTT() < minim) {
                    minim = cust.getTT();
                    index = i;
                }
                if (minim - secunde > 20)
                    isFull = true;

                if (isFull) {
                    addCasa();
                    isFull = false;
                    index = crtCase;
                }
            } else {
                index = i;
                break;
            }
        }
        System.out.println(minim + "\n");
        return index;
    }

    public void addCasa() {
        crtCase++;
        rand[crtCase] = new Casa();
    }

    public void actionPerformed(ActionEvent e) {
        boolean firstTime = true;
        if (e.getSource() == view.getBut()) {
            maxTime = Integer.parseInt(view.getTimpMax().getText());
            minTime = Integer.parseInt(view.getTimpMin().getText());
            maxCase = Integer.parseInt(view.getmaxC().getText());
            startCase = Integer.parseInt(view.getstartC().getText());
            intervalSimulare = Integer.parseInt(view.getIntervalSimulareValue().getText());
            view.dispose();
            gUI.setVisible(true);
        }
        if (e.getSource() == gUI.getStart()) {
            System.out.println("START");
            for (int i = 1; i <= maxCase; i++)
                rand[i] = new Casa();
            gUI.getTA().setText(refreshDisplay());
            crtCase = startCase;
            gUI.getBut().setEnabled(true);
            clock = new Thread(this);
            clock.start();
            gUI.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    stopTimer();
                    System.exit(0);
                }
            });

        }
        if (e.getSource() == gUI.getBut()) {
            count++;
            Random rando = new Random();
            int servTime = minTime + rando.nextInt(maxTime);
            int timpSosire = secunde;
            int indexQ = casaChoose();
            if (rand[indexQ].getLast() != null)
                cust = rand[indexQ].getLast().info;
            cust = new Client(count, servTime, timpSosire);
            if (rand[indexQ].getLast() == rand[indexQ].getFirst())
                cust.setTT(cust.getTimpSosire() + cust.getTimpServire());
            else {
                Client prevCust = rand[indexQ].getLast().prev.info;
                int total = (prevCust.getTT() + cust.getTimpServire());
                cust.setTT(total);
            }
            if (rand[indexQ] != null) {
                rand[indexQ].coadaIn(cust);
                rand[indexQ].setTimpTotalCasa(rand[indexQ].getTimpTotalCasa() + cust.getTT());
                rand[indexQ].setTimpMediuAsteptare(rand[indexQ].getTimpTotalCasa() / rand[indexQ].getLength(count));
            }
            System.out.println(cust.getTT() + "\n");
            printComment(1, cust, indexQ);


            gUI.getTA().setText(refreshDisplay());

        }
        if (e.getSource() == gUI.getStatistica()) {
            for (int i = 1; i < rand.length; i++) {
                printTimpMediuAsteptare(rand[i], i);
            }
        }

    }

    public String refreshDisplay() {
        String s = "";
        for (int k = 1; k <= crtCase; k++) {
            s = s + " CASA [" + k + "]  ";
            for (int i = 1; i <= rand[k].getSize(); i++) {
                cust = (Client) rand[k].getInfo(i);
                s = s + "Client(" + cust.getID() + ")~--|_" + cust.getTimpServire() + "_|    ";
            }
            s = s + "\n";
        }
        return s;
        //deseneaza
    }

    public String comment(int io, Client cust, int nrCasa) {
        String s = "";
        if (io == 1)
            s = " -->  Clientul cu ID  " + cust.getID() +
                    "a intrat in casa de marcat cu numarul:  " + nrCasa + "  la timpul  " + getTimeText() + "   minutes\n";
        else
            s = " <--  Clientul cu ID  " + cust.getID() + "  ,de la casa de marcat  " + nrCasa +
                    "  , a iesit de la casa la  " + getTimeText() +
                    "  asteptand  " + (secunde - cust.getTimpSosire()) + "  secunde\n  ";
        return s;
        //log de evenimente
    }

    public void printComment(int io, Client cust, int nrCasa) {
        gUI.getComm().append(comment(io, cust, nrCasa));
    }

    public void printTimpMediuAsteptare(Casa casa, int houseNumber) {
        gUI.getComm().append("timpul mediu de asteptare pentru casa " + houseNumber + " este " + casa.getTimpMediuAsteptare() + "\n");
    }

    public void stopTimer() {
        clock = null;
    }

    public void makeCoadaDeq() {
        for (int i = 1; i <= crtCase; i++) {
            if (rand[i].getFirst() != null) {
                cust = rand[i].getFirst().info;
                if (cust.getTT() == sec) {
                    rand[i].CoadaOut();
                    printComment(0, cust, i);
                }
            }
        }
    }

    public void run() {

        while (clock != null && secunde <= intervalSimulare) {
            gUI.getCLK().setText(getTimeText());
            try {
                clock.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (sec < 59)
                sec++;
            else {
                min++;
                sec = 0;
            }
            secunde++;
            makeCoadaDeq();
            refreshDisplay();
            gUI.getTA().setText(refreshDisplay());
        }

    }
}
