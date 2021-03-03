package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer invoice_id;
	
	
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="minutes_in")
	private int minutes_in;
	@Column(name="minutes_out")
	private int minutes_out;
	@Column(name="total_sms_in")
	private int total_sms_in;
	@Column(name="total_sms_out")
	private int total_sms_out;
	@Column(name="minutes_traffic")
	private int minutes_traffic;
	
	@Column(name="monthly_cost")
	private double monthly_cost;
	@Column(name="network_minutes_charge")
	private double network_minutes_charge;
	@Column(name="minutes_charge")
	private double minutes_charge;
	
	@Column(name="network_sms_charge")
	private double network_sms_charge;
	@Column(name="sms_charge")
	private double sms_charge;
	@Column(name="traffic_charge")
	private double traffic_charge;
	
	@Column(name="current_month")
	private Integer current_month;
	
	public Invoice() {
		
	}

	public Integer getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}



	public int getMinutes_in() {
		return minutes_in;
	}

	public void setMinutes_in(int minutes_in) {
		this.minutes_in = minutes_in;
	}

	public int getMinutes_out() {
		return minutes_out;
	}

	public void setMinutes_out(int minutes_out) {
		this.minutes_out = minutes_out;
	}

	public int getTotal_sms_in() {
		return total_sms_in;
	}

	public void setTotal_sms_in(int total_sms_in) {
		this.total_sms_in = total_sms_in;
	}

	public int getTotal_sms_out() {
		return total_sms_out;
	}

	public void setTotal_sms_out(int total_sms_out) {
		this.total_sms_out = total_sms_out;
	}

	public int getMinutes_traffic() {
		return minutes_traffic;
	}

	public void setMinutes_traffic(int minutes_traffic) {
		this.minutes_traffic = minutes_traffic;
	}

	public double getMonthly_cost() {
		return monthly_cost;
	}

	public void setMonthly_cost(double monthly_cost) {
		this.monthly_cost = monthly_cost;
	}

	public double getNetwork_minutes_charge() {
		return network_minutes_charge;
	}

	public void setNetwork_minutes_charge(double network_minutes_charge) {
		this.network_minutes_charge = network_minutes_charge;
	}

	public double getMinutes_charge() {
		return minutes_charge;
	}

	public void setMinutes_charge(double minutes_charge) {
		this.minutes_charge = minutes_charge;
	}

	public double getNetwork_sms_charge() {
		return network_sms_charge;
	}

	public void setNetwork_sms_charge(double network_sms_charge) {
		this.network_sms_charge = network_sms_charge;
	}

	public double getSms_charge() {
		return sms_charge;
	}

	public void setSms_charge(double sms_charge) {
		this.sms_charge = sms_charge;
	}

	public double getTraffic_charge() {
		return traffic_charge;
	}

	public void setTraffic_charge(double traffic_charge) {
		this.traffic_charge = traffic_charge;
	}
	
	

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	

	public Integer getCurrent_month() {
		return current_month;
	}

	public void setCurrent_month(Integer current_month) {
		this.current_month = current_month;
	}

	@Override
	public String toString() {
		return "Invoice [invoice_id=" + invoice_id + ", minutes_in=" + minutes_in
				+ ", minutes_out=" + minutes_out + ", total_sms_in=" + total_sms_in + ", total_sms_out=" + total_sms_out
				+ ", minutes_traffic=" + minutes_traffic + ", monthly_cost=" + monthly_cost
				+ ", network_minutes_charge=" + network_minutes_charge + ", minutes_charge=" + minutes_charge
				+ ", network_sms_charge=" + network_sms_charge + ", sms_charge=" + sms_charge + ", traffic_charge="
				+ traffic_charge + "]";
	}
	
	
	

	
}
