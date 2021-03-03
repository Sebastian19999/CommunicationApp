package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "extracharge")
public class ExtraCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_extra;
	
	@Column(name="name_extra")
	private String name_extra;
	
	@Column(name="network_call")
	private double network_call;
	
	@Column(name="network_sms")
	private double network_sms;
	
	@Column(name="basic_call")
	private double basic_call;
	
	@Column(name="sms")
	private double sms;
	
	@Column(name="internet_traffic")
	private double internet_traffic;

	public ExtraCharge() {
		
	}
	
	public ExtraCharge(String name_extra, double network_call, double network_sms, double basic_call, double sms,
			double internet_traffic) {
		super();
		this.name_extra = name_extra;
		this.network_call = network_call;
		this.network_sms = network_sms;
		this.basic_call = basic_call;
		this.sms = sms;
		this.internet_traffic = internet_traffic;
	}

	public Integer getId_extra() {
		return id_extra;
	}

	public void setId_extra(Integer id_extra) {
		this.id_extra = id_extra;
	}

	

	public double getBasic_call() {
		return basic_call;
	}

	public void setBasic_call(double basic_call) {
		this.basic_call = basic_call;
	}

	public String getName_extra() {
		return name_extra;
	}

	public void setName_extra(String name_extra) {
		this.name_extra = name_extra;
	}

	public double getNetwork_call() {
		return network_call;
	}

	public void setNetwork_call(double network_call) {
		this.network_call = network_call;
	}

	public double getNetwork_sms() {
		return network_sms;
	}

	public void setNetwork_sms(double network_sms) {
		this.network_sms = network_sms;
	}

	public double getCall() {
		return basic_call;
	}

	public void setCall(double basic_call) {
		this.basic_call = basic_call;
	}

	public double getSms() {
		return sms;
	}

	public void setSms(double sms) {
		this.sms = sms;
	}

	public double getInternet_traffic() {
		return internet_traffic;
	}

	public void setInternet_traffic(double internet_traffic) {
		this.internet_traffic = internet_traffic;
	}

	@Override
	public String toString() {
		return "ExtraCharge [id_extra=" + id_extra + ", name=" + name_extra + ", network_call=" + network_call
				+ ", network_sms=" + network_sms + ", basic_call=" + basic_call + ", sms=" + sms + ", internet_traffic="
				+ internet_traffic + "]";
	}
	
	
	
	
}
