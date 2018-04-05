package com.company;



public class Client {
    private int id;
    private int timpServire;
    private int timpSosire;
    private int timpTotal;



    public Client(){
        this.id = 0;
        this.timpServire = 0;
        this.timpSosire = 0;
        this.timpTotal = 0;
    }
    public Client(int id, int stimp, int atimp){
        setTimp(stimp);
        setTimpS(atimp);
        setID(id);
    }

    public int getID(){
        return this.id;
    }
    public int getTimpServire(){
        return this.timpServire;
    }
    public int getTimpSosire(){
        return timpSosire;
    }
    public int getTT(){ return timpTotal; }

    public void setTimp(int timp){
        this.timpServire = timp;
    }

    public void setTimpS(int timp){
        this.timpSosire = timp;
    }

    public void setTT(int timp){
        this.timpTotal = timp;
    }

    public void setID(int  nume){
        this.id = nume;
    }
}
