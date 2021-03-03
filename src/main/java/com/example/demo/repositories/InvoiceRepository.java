package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer>{

	@Query(value="select * from invoice e where e.phone_number like %:keyword%", nativeQuery=true)
	List<Invoice> findInvoiceByKeyword(String keyword);
	
}
