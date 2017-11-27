package com.musicrecord.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Record implements Serializable {
   // @idRecords
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRecords")
    private int idRecords;

    @Column(name = "title")
    private String title;
    @Column(name = "artist")
    private String artist;
    
    @Column(name = "category")
    private int category;
	

    public int getIdRecords() {
		return idRecords;
	}

	public void setIdRecords(int idRecords) {
		this.idRecords = idRecords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}


}
