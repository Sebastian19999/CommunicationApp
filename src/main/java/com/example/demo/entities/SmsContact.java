package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_sms")
public class SmsContact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_sms;

	@Column(name="appellant_number_sms")
	private String appellant_number_sms;
	@Column(name="receiver_number_sms")
	private String receiver_number_sms;
	@Column(name="sms_number")
	private int sms_number=0;
	
	
	private boolean sameNetwork=false;
	
	@Column(name="start_date_sms")
	private String start_date_sms;
	@Column(name="end_date_sms")
	private String end_date_sms;
	
	
	@Column(name="current_month")
	private String current_month;


	public SmsContact() {
		
	}

	public SmsContact(String appellant_number_sms) {
		this.appellant_number_sms = appellant_number_sms;
	}

	public Integer getId_sms() {
		return id_sms;
	}

	public void setId_sms(Integer id_sms) {
		this.id_sms = id_sms;
	}

	public String getAppellant_number_sms() {
		return appellant_number_sms;
	}

	public void setAppellant_number_sms(String appellant_number_sms) {
		this.appellant_number_sms = appellant_number_sms;
	}

	public String getReceiver_number_sms() {
		return receiver_number_sms;
	}

	public void setReceiver_number_sms(String receiver_number_sms) {
		this.receiver_number_sms = receiver_number_sms;
	}

	public int getSms_number() {
		return sms_number;
	}

	public void setSms_number(int sms_number) {
		this.sms_number = sms_number;
	}

	public boolean isSameNetwork() {
		return sameNetwork;
	}

	public void setSameNetwork(boolean sameNetwork) {
		this.sameNetwork = sameNetwork;
	}
	
	public String getStart_date_sms() {
		return start_date_sms;
	}

	public void setStart_date_sms(String start_date_sms) {
		this.start_date_sms = start_date_sms;
	}

	public String getEnd_date_sms() {
		return end_date_sms;
	}

	public void setEnd_date_sms(String end_date_sms) {
		this.end_date_sms = end_date_sms;
	}
	
	

	public String getCurrent_month() {
		return current_month;
	}

	public void setCurrent_month(String current_month) {
		this.current_month = current_month;
	}

	@Override
	public String toString() {
		return "SmsContact [id_sms=" + id_sms + ", appellant_number_sms=" + appellant_number_sms
				+ ", receiver_number_sms=" + receiver_number_sms + ", sms_number=" + sms_number + ", sameNetwork="
				+ sameNetwork + "]";
	}
	
	
	
	

}
