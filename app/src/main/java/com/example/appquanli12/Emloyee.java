package com.example.appquanli12;

public class Emloyee {
    int ID;
    String Name;
    long SDT;
    String Diachi;
    String Chucvu;
    long Money;

    public Emloyee(int ID, String name, long SDT, String diachi, String chucvu, long money) {
        this.ID = ID;
        Name = name;
        this.SDT = SDT;
        Diachi = diachi;
        Chucvu = chucvu;
        Money = money;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getSDT() {
        return SDT;
    }

    public void setSDT(long SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getChucvu() {
        return Chucvu;
    }

    public void setChucvu(String chucvu) {
        Chucvu = chucvu;
    }

    public long getMoney() {
        return Money;
    }

    public void setMoney(long money) {
        Money = money;
    }
}
