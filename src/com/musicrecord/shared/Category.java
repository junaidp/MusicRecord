package com.musicrecord.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryid")
    private int categoryid;

    @Column(name = "name")
    private String categoryname;

    public String getCategoryname() {
	return categoryname;
    }

    public void setCategoryname(String categoryname) {
	this.categoryname = categoryname;
    }

    public int getCategoryid() {
	return categoryid;
    }

    public void setCategoryid(int categoryid) {
	this.categoryid = categoryid;
    }
}
