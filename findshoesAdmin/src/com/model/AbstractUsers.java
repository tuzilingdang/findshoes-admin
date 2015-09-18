package com.model;

import java.sql.Timestamp;


/**
 * AbstractUsers entity provides the base persistence definition of the Users entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsers  implements java.io.Serializable {


    // Fields    

     private String userId;
     private String password;
     private String nick;
     private String telephone;
     private String email;
     private String icon;
     private Double balance;
     private Timestamp accesstime;
     private Timestamp regTime;
     private String ip;
     private String address;
     private String vip;
     private String defunct;


    // Constructors

    /** default constructor */
    public AbstractUsers() {
    }

	/** minimal constructor */
    public AbstractUsers(String userId, String password, Double balance, String ip, String vip, String defunct) {
        this.userId = userId;
        this.password = password;
        this.balance = balance;
        this.ip = ip;
        this.vip = vip;
        this.defunct = defunct;
    }
    
    /** full constructor */
    public AbstractUsers(String userId, String password, String nick, String telephone, String email, String icon, Double balance, Timestamp accesstime, Timestamp regTime, String ip, String address, String vip, String defunct) {
        this.userId = userId;
        this.password = password;
        this.nick = nick;
        this.telephone = telephone;
        this.email = email;
        this.icon = icon;
        this.balance = balance;
        this.accesstime = accesstime;
        this.regTime = regTime;
        this.ip = ip;
        this.address = address;
        this.vip = vip;
        this.defunct = defunct;
    }

   
    // Property accessors

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return this.nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Timestamp getAccesstime() {
        return this.accesstime;
    }
    
    public void setAccesstime(Timestamp accesstime) {
        this.accesstime = accesstime;
    }

    public Timestamp getRegTime() {
        return this.regTime;
    }
    
    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getVip() {
        return this.vip;
    }
    
    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getDefunct() {
        return this.defunct;
    }
    
    public void setDefunct(String defunct) {
        this.defunct = defunct;
    }
   








}