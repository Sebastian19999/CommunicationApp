package com.example.demo.ehcache;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.Subscription;

@Entity
@Table(name = "students")
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_student;

	@Column(name="name_student")
	private String name_student;

	@Column(name="age")
	private String age;
	
	@Column(name="address")
	private String address;

	@Column(name="major")
	private String major;
	
	@Column(name="year")
	private int year;
	
	@Column(name="student_phone")
	private String student_phone;
	
	@Column(name="balance")
	private double balance;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "sub_fid", referencedColumnName = "id_subscription")
	private Subscription subscription;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "pc_fid", referencedColumnName = "id_extra")
	private ExtraCharge extraCharge;

	public Student() {
		super();
	}

	public Student(String name_student, String age, String address, String major, int year, String student_phone,
			double balance) {
		super();
		this.name_student = name_student;
		this.age = age;
		this.address = address;
		this.major = major;
		this.year = year;
		this.student_phone = student_phone;
		this.balance = balance;
	}

	public Integer getId_student() {
		return id_student;
	}

	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}

	public String getName_student() {
		return name_student;
	}

	public void setName_student(String name_student) {
		this.name_student = name_student;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public ExtraCharge getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(ExtraCharge extraCharge) {
		this.extraCharge = extraCharge;
	}

	@Override
	public String toString() {
		return "Student [id_student=" + id_student + ", name_student=" + name_student + ", age=" + age + ", address="
				+ address + ", major=" + major + ", year=" + year + ", student_phone=" + student_phone + ", balance="
				+ balance + "]";
	}
	
}
