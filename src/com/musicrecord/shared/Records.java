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

@Table(name = "records")
public class Records implements Serializable {
   
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRecords")
    private int idRecords;

    @Column(name = "title")
    private String title;
    @Column(name = "artist")
    private String artist;
    
    @Column(name = "category")
    private int category;
	
    @JoinColumn(name = "idCategory")
    @ManyToOne(fetch = FetchType.EAGER)

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
