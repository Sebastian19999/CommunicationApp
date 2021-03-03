package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chrono {

	@Id
	private Integer id_chrono;
	
	private double no_minutes_in;
	
	private double no_minutes_out;
	
	private int no_sms_in;
	
	private int no_sms_out;
	
	private double no_traffic;
	
	private double no_balance;

	public Chrono() {
		super();
	}

	public Chrono(Integer id_chrono, double no_minutes_in, double no_minutes_out, int no_sms_in, int no_sms_out,
			double no_traffic, double no_balance) {
		super();
		this.id_chrono = id_chrono;
		this.no_minutes_in = no_minutes_in;
		this.no_minutes_out = no_minutes_out;
		this.no_sms_in = no_sms_in;
		this.no_sms_out = no_sms_out;
		this.no_traffic = no_traffic;
		this.no_balance = no_balance;
	}



	public Integer getId_chrono() {
		return id_chrono;
	}

	public void setId_chrono(Integer id_chrono) {
		this.id_chrono = id_chrono;
	}

	public double getNo_minutes_in() {
		return no_minutes_in;
	}

	public void setNo_minutes_in(double no_minutes_in) {
		this.no_minutes_in = no_minutes_in;
	}

	public double getNo_minutes_out() {
		return no_minutes_out;
	}

	public void setNo_minutes_out(double no_minutes_out) {
		this.no_minutes_out = no_minutes_out;
	}

	public int getNo_sms_in() {
		return no_sms_in;
	}

	public void setNo_sms_in(int no_sms_in) {
		this.no_sms_in = no_sms_in;
	}

	public int getNo_sms_out() {
		return no_sms_out;
	}

	public void setNo_sms_out(int no_sms_out) {
		this.no_sms_out = no_sms_out;
	}

	public double getNo_traffic() {
		return no_traffic;
	}

	public void setNo_traffic(double no_traffic) {
		this.no_traffic = no_traffic;
	}

	public double getNo_balance() {
		return no_balance;
	}

	public void setNo_balance(double no_balance) {
		this.no_balance = no_balance;
	}
	
	
}
