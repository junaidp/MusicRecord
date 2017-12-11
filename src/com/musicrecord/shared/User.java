package com.musicrecord.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "roleid")
     @ManyToOne(fetch = FetchType.LAZY)
     private Roles roleId;

    public User() {
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int id) {
	this.userId = id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

	public Roles getRoleId() {
		return roleId;
	}

}
