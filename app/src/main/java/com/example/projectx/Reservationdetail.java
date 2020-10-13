package com.example.projectx;

public class Reservationdetail {
    String user_Name;
    String phone_Number;
    String txnid;
    String duration;
    public Reservationdetail(String user_Name, String phone_Number, String txnid, String duration) {
        this.user_Name = user_Name;
        this.phone_Number = phone_Number;
        this.txnid = txnid;
        this.duration = duration; }
    public String getUser_Name() { return user_Name; }
    public void setUser_Name(String user_Name) { this.user_Name = user_Name; }
    public String getPhone_Number() { return phone_Number; }
    public void setPhone_Number(String phone_Number) { this.phone_Number = phone_Number; }
    public String getTxnid() { return txnid; }
    public void setTxnid(String txnid) { this.txnid = txnid; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
