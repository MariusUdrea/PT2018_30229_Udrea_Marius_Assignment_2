package com.company;





public class Casa {
     private Node first;
     private Node last;
    private int length;
    private int timpMediuAsteptare;
    private int timpTotalCasa;
    //cream o structura de tip coada implementata cu o lista simpla inlantuita
    public int getLength() {
        return length;
    }
    public int getLength(int i) {
        return i;
    }

    public int getTimpTotalCasa() {
        return timpTotalCasa;
    }

    public void setTimpTotalCasa(int timpTotalCasa) {
        this.timpTotalCasa = timpTotalCasa;
    }

    public int getTimpMediuAsteptare() {
        return timpMediuAsteptare;
    }

    public void setTimpMediuAsteptare(int timpMediuAsteptare) {
        this.timpMediuAsteptare = timpMediuAsteptare;
    }

    public Casa(){
        first = null;
        last = null;
        length = 0;
    }


    public int getSize(){
        return length;
    }

    public Node getFirst(){
        return first;
    }
    public Node getLast(){
        return last;
    }

    public Client getInfo(int i) {
        if (i>=0)
        {
            Node n = first;
            for (int j=1; j<i; j++)
                n = n.next;
            return n.info;
        }
        else
            return null;
    }



    public void coadaIn (Client info){
        // incrementam coada
        assert info != null ;
        int sizePre = getSize();
        if (sizePre == 0)
        { // daca coada e goala inseram la inceput si incrementam lungimea
            Node node = new Node();
            node.info  = info;
            node.next = null;
            node.prev = null;
            first = node;
            last = node;
            length++;
        }
        else
        {	//daca nu e goala , inseram in continuare si incrementam lungimea
            Node n = last;
            Node node = new Node();
            node.info = info;
            node.next = null;
            node.prev = n;
            n.next = node;
            last = node;
            length ++;
        }
        int sizePost = getSize();
        assert sizePost == sizePre + 1;
    }


    public void CoadaOut(){
        //decrementam coada
        assert getSize() > 0;
        if (this.first != this.last)
        {//daca coada nu e goala , stergem ultimul element,si decrementam lungimea
            Object info = getInfo(0);
            Node n = first;
            first = n.next;
            first.prev = null;
            length--;
            assert n.info == info;
        }
        else
        { //coada e nula
            first = null;
            last = null;
            length = 0;
        }
    }



}
