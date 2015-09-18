package com.model;

import java.sql.Timestamp;


/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Users() {
    }

	/** minimal constructor */
    public Users(String userId, String password, Double balance, String ip, String vip, String defunct) {
        super(userId, password, balance, ip, vip, defunct);        
    }
    
    /** full constructor */
    public Users(String userId, String password, String nick, String telephone, String email, String icon, Double balance, Timestamp accesstime, Timestamp regTime, String ip, String address, String vip, String defunct) {
        super(userId, password, nick, telephone, email, icon, balance, accesstime, regTime, ip, address, vip, defunct);        
    }
   
}
