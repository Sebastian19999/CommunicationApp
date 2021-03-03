package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_contact")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_contact;

	@Column(name="appellant_number")
	private String appellant_number;
	@Column(name="receiver_number")
	private String receiver_number;
	
	private boolean sameNetwork=false;
	
	@Column(name="start_date")
	private String start_date;
	
	@Column(name="end_date")
	private String end_date;
	
	public Contact() {
		
	}
	
	public Contact(String appellant_number, String receiver_number) {
		this.appellant_number = appellant_number;
		this.receiver_number = receiver_number;
	}
	
	
	public Integer getId_contact() {
		return id_contact;
	}

	public void setId_contact(Integer id_contact) {
		this.id_contact = id_contact;
	}


	public String getAppellant_number() {
		return appellant_number;
	}

	public void setAppellant_number(String appellant_number) {
		this.appellant_number = appellant_number;
	}

	public String getReceiver_number() {
		return receiver_number;
	}

	public void setReceiver_number(String receiver_number) {
		this.receiver_number = receiver_number;
	}

	


	public boolean isSameNetwork() {
		return sameNetwork;
	}

	public void setSameNetwork(boolean sameNetwork) {
		this.sameNetwork = sameNetwork;
	}
	
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	


	
	
	
	
	
	
}
