package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_subscription;
	
	@Column(name="name")
	private String name;
			
	@Column(name="monthly_cost")
	private double monthly_cost;
			
	@Column(name="network_minutes_included")
	private int network_minutes_included;
			
	@Column(name="network_sms_included")
	private int network_sms_included;
			
	@Column(name="minutes_included")
	private int minutes_included;
			
	@Column(name="sms_included")
	private int sms_included;
			
	@Column(name="traffic_included")
	private double traffic_included;
	
	

	public Subscription() {
		
	}

	public Subscription(String name, double monthly_cost, int network_minutes_included, int network_sms_included,
			int minutes_included, int sms_included, double traffic_included) {
		super();
		this.name = name;
		this.monthly_cost = monthly_cost;
		this.network_minutes_included = network_minutes_included;
		this.network_sms_included = network_sms_included;
		this.minutes_included = minutes_included;
		this.sms_included = sms_included;
		this.traffic_included = traffic_included;
	}

	public Integer getId_subscription() {
		return id_subscription;
	}

	public void setId_subscription(Integer id_subscription) {
		this.id_subscription = id_subscription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMonthly_cost() {
		return monthly_cost;
	}

	public void setMonthly_cost(double monthly_cost) {
		this.monthly_cost = monthly_cost;
	}

	public int getNetwork_minutes_included() {
		return network_minutes_included;
	}

	public void setNetwork_minutes_included(int network_minutes_included) {
		this.network_minutes_included = network_minutes_included;
	}

	public int getNetwork_sms_included() {
		return network_sms_included;
	}

	public void setNetwork_sms_included(int network_sms_included) {
		this.network_sms_included = network_sms_included;
	}

	public int getMinutes_included() {
		return minutes_included;
	}

	public void setMinutes_included(int minutes_included) {
		this.minutes_included = minutes_included;
	}

	public int getSms_included() {
		return sms_included;
	}

	public void setSms_included(int sms_included) {
		this.sms_included = sms_included;
	}

	public double getTraffic_included() {
		return traffic_included;
	}

	public void setTraffic_included(double traffic_included) {
		this.traffic_included = traffic_included;
	}

	@Override
	public String toString() {
		return "Subscriber [id_sub=" + id_subscription + ", name=" + name + ", monthly_cost=" + monthly_cost
				+ ", network_minutes_included=" + network_minutes_included + ", network_sms_included="
				+ network_sms_included + ", minutes_included=" + minutes_included + ", sms_included=" + sms_included
				+ ", traffic_included=" + traffic_included + "]";
	}
	
	
	
	
	
}
