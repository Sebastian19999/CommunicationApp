package com.example.demo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.Invoice;
import com.example.demo.repositories.InvoiceRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public List<Invoice> getAll(){
		return invoiceRepository.findAll();
	}
	
	public Optional<Invoice> getInvoice(Integer id) {
		return invoiceRepository.findById(id);
	}
	
	public Invoice saveInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}
	
	public Invoice updateInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}
	
	
	public Invoice findInvoiceByKeyword(String keyword) {
		return invoiceRepository.findInvoiceByKeyword(keyword).get(0);
	}
	
	public List<Invoice> findInvoicesByKeyWord(String keyword){
		return invoiceRepository.findInvoiceByKeyword(keyword);
	}
	
	public void clearInvoice() {
		invoiceRepository.deleteAll();
	}
	
	
	
	public int time_difference(String dateStart,String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  

		Date d1 = null;
		Date d2 = null;
		try {
		    d1 = format.parse(dateStart);
		    d2 = format.parse(dateStop);
		} catch (ParseException e) {
		    e.printStackTrace();
		}    
		
		long diff = d2.getTime() - d1.getTime();
		
		return (int) Math.ceil((diff / 1000)/60);
	}
	
	public boolean createPdf(List<Invoice> listInvoice, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document=new Document(PageSize.A4,15,15,45,30);
		try {
			String filePath=context.getRealPath("/resources/reports");
			File file=new File(filePath);
			
			boolean exists=new File(filePath).exists();
			if(!exists) {
				new File(filePath).mkdirs();
			}
			
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"listInvoice"+".pdf"));
			document.open();
			
			Font mainFont=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			
			Paragraph paragraph=new Paragraph("Client Data", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			
			Font tableHeader=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font tableBody=FontFactory.getFont("Arial",9,BaseColor.BLACK);
		
			float[] columnWidths= {2f,2f,2f,2f,2f,2f,2f};
			table.setWidths(columnWidths);
			
			PdfPCell number=new PdfPCell(new Paragraph("Client Number",tableHeader));
			number.setBorderColor(BaseColor.BLACK);
			number.setPaddingLeft(10);
			number.setHorizontalAlignment(Element.ALIGN_CENTER);
			number.setVerticalAlignment(Element.ALIGN_CENTER);
			number.setBackgroundColor(BaseColor.GRAY);
			number.setExtraParagraphSpace(5f);
			table.addCell(number);
			
			PdfPCell month=new PdfPCell(new Paragraph("Month",tableHeader));
			month.setBorderColor(BaseColor.BLACK);
			month.setPaddingLeft(10);
			month.setHorizontalAlignment(Element.ALIGN_CENTER);
			month.setVerticalAlignment(Element.ALIGN_CENTER);
			month.setBackgroundColor(BaseColor.GRAY);
			month.setExtraParagraphSpace(5f);
			table.addCell(month);
			
			PdfPCell network_minutes_consumed=new PdfPCell(new Paragraph("Network Minutes Consumed",tableHeader));
			network_minutes_consumed.setBorderColor(BaseColor.BLACK);
			network_minutes_consumed.setPaddingLeft(10);
			network_minutes_consumed.setHorizontalAlignment(Element.ALIGN_CENTER);
			network_minutes_consumed.setVerticalAlignment(Element.ALIGN_CENTER);
			network_minutes_consumed.setBackgroundColor(BaseColor.GRAY);
			network_minutes_consumed.setExtraParagraphSpace(5f);
			table.addCell(network_minutes_consumed);
			
			PdfPCell minutes_consumed=new PdfPCell(new Paragraph("Minutes Consumed",tableHeader));
			minutes_consumed.setBorderColor(BaseColor.BLACK);
			minutes_consumed.setPaddingLeft(10);
			minutes_consumed.setHorizontalAlignment(Element.ALIGN_CENTER);
			minutes_consumed.setVerticalAlignment(Element.ALIGN_CENTER);
			minutes_consumed.setBackgroundColor(BaseColor.GRAY);
			minutes_consumed.setExtraParagraphSpace(5f);
			table.addCell(minutes_consumed);
			
			PdfPCell network_sms_consumed=new PdfPCell(new Paragraph("Network SMS Consumed",tableHeader));
			network_sms_consumed.setBorderColor(BaseColor.BLACK);
			network_sms_consumed.setPaddingLeft(10);
			network_sms_consumed.setHorizontalAlignment(Element.ALIGN_CENTER);
			network_sms_consumed.setVerticalAlignment(Element.ALIGN_CENTER);
			network_sms_consumed.setBackgroundColor(BaseColor.GRAY);
			network_sms_consumed.setExtraParagraphSpace(5f);
			table.addCell(network_sms_consumed);
			
			PdfPCell sms_consumed=new PdfPCell(new Paragraph("SMS Consumed",tableHeader));
			sms_consumed.setBorderColor(BaseColor.BLACK);
			sms_consumed.setPaddingLeft(10);
			sms_consumed.setHorizontalAlignment(Element.ALIGN_CENTER);
			sms_consumed.setVerticalAlignment(Element.ALIGN_CENTER);
			sms_consumed.setBackgroundColor(BaseColor.GRAY);
			sms_consumed.setExtraParagraphSpace(5f);
			table.addCell(sms_consumed);
			
			PdfPCell network_minutes_traffic=new PdfPCell(new Paragraph("Minutes Traffic",tableHeader));
			network_minutes_traffic.setBorderColor(BaseColor.BLACK);
			network_minutes_traffic.setPaddingLeft(10);
			network_minutes_traffic.setHorizontalAlignment(Element.ALIGN_CENTER);
			network_minutes_traffic.setVerticalAlignment(Element.ALIGN_CENTER);
			network_minutes_traffic.setBackgroundColor(BaseColor.GRAY);
			network_minutes_traffic.setExtraParagraphSpace(5f);
			table.addCell(network_minutes_traffic);
			
			for(Invoice invoice : listInvoice) {
				
				PdfPCell phoneValue=new PdfPCell(new Paragraph(invoice.getPhone_number(),tableBody));
				phoneValue.setBorderColor(BaseColor.BLACK);
				phoneValue.setPaddingLeft(10);
				phoneValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				phoneValue.setVerticalAlignment(Element.ALIGN_CENTER);
				phoneValue.setBackgroundColor(BaseColor.WHITE);
				phoneValue.setExtraParagraphSpace(5f);
				table.addCell(phoneValue);
				
				PdfPCell monthValue=new PdfPCell(new Paragraph(Integer.toString(invoice.getCurrent_month()),tableBody));
				monthValue.setBorderColor(BaseColor.BLACK);
				monthValue.setPaddingLeft(10);
				monthValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				monthValue.setVerticalAlignment(Element.ALIGN_CENTER);
				monthValue.setBackgroundColor(BaseColor.WHITE);
				monthValue.setExtraParagraphSpace(5f);
				table.addCell(monthValue);
				
				PdfPCell network_minutes=new PdfPCell(new Paragraph(Integer.toString(invoice.getMinutes_in()),tableBody));
				network_minutes.setBorderColor(BaseColor.BLACK);
				network_minutes.setPaddingLeft(10);
				network_minutes.setHorizontalAlignment(Element.ALIGN_CENTER);
				network_minutes.setVerticalAlignment(Element.ALIGN_CENTER);
				network_minutes.setBackgroundColor(BaseColor.WHITE);
				network_minutes.setExtraParagraphSpace(5f);
				table.addCell(network_minutes);
				
				PdfPCell minutes_out=new PdfPCell(new Paragraph(Integer.toString(invoice.getMinutes_out()),tableBody));
				minutes_out.setBorderColor(BaseColor.BLACK);
				minutes_out.setPaddingLeft(10);
				minutes_out.setHorizontalAlignment(Element.ALIGN_CENTER);
				minutes_out.setVerticalAlignment(Element.ALIGN_CENTER);
				minutes_out.setBackgroundColor(BaseColor.WHITE);
				minutes_out.setExtraParagraphSpace(5f);
				table.addCell(minutes_out);
				
				PdfPCell sms_in=new PdfPCell(new Paragraph(Integer.toString(invoice.getTotal_sms_in()),tableBody));
				sms_in.setBorderColor(BaseColor.BLACK);
				sms_in.setPaddingLeft(10);
				sms_in.setHorizontalAlignment(Element.ALIGN_CENTER);
				sms_in.setVerticalAlignment(Element.ALIGN_CENTER);
				sms_in.setBackgroundColor(BaseColor.WHITE);
				sms_in.setExtraParagraphSpace(5f);
				table.addCell(sms_in);
				
				PdfPCell sms_out=new PdfPCell(new Paragraph(Integer.toString(invoice.getTotal_sms_out()),tableBody));
				sms_out.setBorderColor(BaseColor.BLACK);
				sms_out.setPaddingLeft(10);
				sms_out.setHorizontalAlignment(Element.ALIGN_CENTER);
				sms_out.setVerticalAlignment(Element.ALIGN_CENTER);
				sms_out.setBackgroundColor(BaseColor.WHITE);
				sms_out.setExtraParagraphSpace(5f);
				table.addCell(sms_out);
				
				PdfPCell trafficValue=new PdfPCell(new Paragraph(Integer.toString(invoice.getMinutes_traffic()),tableBody));
				trafficValue.setBorderColor(BaseColor.BLACK);
				trafficValue.setPaddingLeft(10);
				trafficValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				trafficValue.setVerticalAlignment(Element.ALIGN_CENTER);
				trafficValue.setBackgroundColor(BaseColor.WHITE);
				trafficValue.setExtraParagraphSpace(5f);
				table.addCell(trafficValue);
				
				
			}
			
			Paragraph paragraphCharge=new Paragraph("Client Charges", mainFont);
			paragraphCharge.setAlignment(Element.ALIGN_CENTER);
			paragraphCharge.setIndentationLeft(50);
			paragraphCharge.setIndentationRight(50);
			paragraphCharge.setSpacingAfter(10);
			document.add(paragraphCharge);
			
			PdfPTable tableCharges = new PdfPTable(8);
			tableCharges.setWidthPercentage(100);
			tableCharges.setSpacingBefore(10f);
			tableCharges.setSpacingAfter(10);
			
			Font tableHeaderCharges=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font tableBodyCharges=FontFactory.getFont("Arial",9,BaseColor.BLACK);
		
			float[] columnWidthsCharges= {2f,2f,2f,2f,2f,2f,2f,2f};
			tableCharges.setWidths(columnWidthsCharges);
			
			PdfPCell numberCharge=new PdfPCell(new Paragraph("Client Number",tableHeaderCharges));
			numberCharge.setBorderColor(BaseColor.BLACK);
			numberCharge.setPaddingLeft(10);
			numberCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
			numberCharge.setVerticalAlignment(Element.ALIGN_CENTER);
			numberCharge.setBackgroundColor(BaseColor.GRAY);
			numberCharge.setExtraParagraphSpace(5f);
			tableCharges.addCell(numberCharge);
			
			PdfPCell monthCharge=new PdfPCell(new Paragraph("Month",tableHeaderCharges));
			monthCharge.setBorderColor(BaseColor.BLACK);
			monthCharge.setPaddingLeft(10);
			monthCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
			monthCharge.setVerticalAlignment(Element.ALIGN_CENTER);
			monthCharge.setBackgroundColor(BaseColor.GRAY);
			monthCharge.setExtraParagraphSpace(5f);
			tableCharges.addCell(monthCharge);
			
			PdfPCell subscriptionCharge=new PdfPCell(new Paragraph("Subscription Monthly Cost",tableHeaderCharges));
			subscriptionCharge.setBorderColor(BaseColor.BLACK);
			subscriptionCharge.setPaddingLeft(10);
			subscriptionCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
			subscriptionCharge.setVerticalAlignment(Element.ALIGN_CENTER);
			subscriptionCharge.setBackgroundColor(BaseColor.GRAY);
			subscriptionCharge.setExtraParagraphSpace(5f);
			tableCharges.addCell(subscriptionCharge);
			
			PdfPCell networkMinutesCharge=new PdfPCell(new Paragraph("Network Minutes Charge",tableHeaderCharges));
			networkMinutesCharge.setBorderColor(BaseColor.BLACK);
			networkMinutesCharge.setPaddingLeft(10);
			networkMinutesCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
			networkMinutesCharge.setVerticalAlignment(Element.ALIGN_CENTER);
			networkMinutesCharge.setBackgroundColor(BaseColor.GRAY);
			networkMinutesCharge.setExtraParagraphSpace(5f);
			tableCharges.addCell(networkMinutesCharge);
			
			PdfPCell minutesCharge=new PdfPCell(new Paragraph("Minutes Charge",tableHeaderCharges));
			minutesCharge.setBorderColor(BaseColor.BLACK);
			minutesCharge.setPaddingLeft(10);
			minutesCharge.setHorizontalAlignment(Element.ALIGN_CENTER);
			minutesCharge.setVerticalAlignment(Element.ALIGN_CENTER);
			minutesCharge.setBackgroundColor(BaseColor.GRAY);
			minutesCharge.setExtraParagraphSpace(5f);
			tableCharges.addCell(minutesCharge);
			
			PdfPCell network_sms_charge=new PdfPCell(new Paragraph("Network SMS Charge",tableHeaderCharges));
			network_sms_charge.setBorderColor(BaseColor.BLACK);
			network_sms_charge.setPaddingLeft(10);
			network_sms_charge.setHorizontalAlignment(Element.ALIGN_CENTER);
			network_sms_charge.setVerticalAlignment(Element.ALIGN_CENTER);
			network_sms_charge.setBackgroundColor(BaseColor.GRAY);
			network_sms_charge.setExtraParagraphSpace(5f);
			tableCharges.addCell(network_sms_charge);
			
			PdfPCell sms_charge=new PdfPCell(new Paragraph("SMS Charge",tableHeaderCharges));
			sms_charge.setBorderColor(BaseColor.BLACK);
			sms_charge.setPaddingLeft(10);
			sms_charge.setHorizontalAlignment(Element.ALIGN_CENTER);
			sms_charge.setVerticalAlignment(Element.ALIGN_CENTER);
			sms_charge.setBackgroundColor(BaseColor.GRAY);
			sms_charge.setExtraParagraphSpace(5f);
			tableCharges.addCell(sms_charge);
			
			PdfPCell traffic_charge=new PdfPCell(new Paragraph("Traffic Charge",tableHeaderCharges));
			traffic_charge.setBorderColor(BaseColor.BLACK);
			traffic_charge.setPaddingLeft(10);
			traffic_charge.setHorizontalAlignment(Element.ALIGN_CENTER);
			traffic_charge.setVerticalAlignment(Element.ALIGN_CENTER);
			traffic_charge.setBackgroundColor(BaseColor.GRAY);
			traffic_charge.setExtraParagraphSpace(5f);
			tableCharges.addCell(traffic_charge);
			
			for(Invoice invoice : listInvoice) {
				
				PdfPCell phoneValue=new PdfPCell(new Paragraph(invoice.getPhone_number(),tableBodyCharges));
				phoneValue.setBorderColor(BaseColor.BLACK);
				phoneValue.setPaddingLeft(10);
				phoneValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				phoneValue.setVerticalAlignment(Element.ALIGN_CENTER);
				phoneValue.setBackgroundColor(BaseColor.WHITE);
				phoneValue.setExtraParagraphSpace(5f);
				tableCharges.addCell(phoneValue);
				
				PdfPCell monthValue=new PdfPCell(new Paragraph(Integer.toString(invoice.getCurrent_month()),tableBodyCharges));
				monthValue.setBorderColor(BaseColor.BLACK);
				monthValue.setPaddingLeft(10);
				monthValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				monthValue.setVerticalAlignment(Element.ALIGN_CENTER);
				monthValue.setBackgroundColor(BaseColor.WHITE);
				monthValue.setExtraParagraphSpace(5f);
				tableCharges.addCell(monthValue);
				
				PdfPCell subscriptionValue=new PdfPCell(new Paragraph(Double.toString(invoice.getMonthly_cost()),tableBodyCharges));
				subscriptionValue.setBorderColor(BaseColor.BLACK);
				subscriptionValue.setPaddingLeft(10);
				subscriptionValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				subscriptionValue.setVerticalAlignment(Element.ALIGN_CENTER);
				subscriptionValue.setBackgroundColor(BaseColor.WHITE);
				subscriptionValue.setExtraParagraphSpace(5f);
				tableCharges.addCell(subscriptionValue);
				
				PdfPCell network_minutes=new PdfPCell(new Paragraph(Double.toString(invoice.getNetwork_minutes_charge()),tableBodyCharges));
				network_minutes.setBorderColor(BaseColor.BLACK);
				network_minutes.setPaddingLeft(10);
				network_minutes.setHorizontalAlignment(Element.ALIGN_CENTER);
				network_minutes.setVerticalAlignment(Element.ALIGN_CENTER);
				network_minutes.setBackgroundColor(BaseColor.WHITE);
				network_minutes.setExtraParagraphSpace(5f);
				tableCharges.addCell(network_minutes);
				
				PdfPCell minutes_out=new PdfPCell(new Paragraph(Double.toString(invoice.getMinutes_charge()),tableBodyCharges));
				minutes_out.setBorderColor(BaseColor.BLACK);
				minutes_out.setPaddingLeft(10);
				minutes_out.setHorizontalAlignment(Element.ALIGN_CENTER);
				minutes_out.setVerticalAlignment(Element.ALIGN_CENTER);
				minutes_out.setBackgroundColor(BaseColor.WHITE);
				minutes_out.setExtraParagraphSpace(5f);
				tableCharges.addCell(minutes_out);
				
				PdfPCell sms_in=new PdfPCell(new Paragraph(Double.toString(invoice.getNetwork_sms_charge()),tableBodyCharges));
				sms_in.setBorderColor(BaseColor.BLACK);
				sms_in.setPaddingLeft(10);
				sms_in.setHorizontalAlignment(Element.ALIGN_CENTER);
				sms_in.setVerticalAlignment(Element.ALIGN_CENTER);
				sms_in.setBackgroundColor(BaseColor.WHITE);
				sms_in.setExtraParagraphSpace(5f);
				tableCharges.addCell(sms_in);
				
				PdfPCell sms_out=new PdfPCell(new Paragraph(Double.toString(invoice.getSms_charge()),tableBodyCharges));
				sms_out.setBorderColor(BaseColor.BLACK);
				sms_out.setPaddingLeft(10);
				sms_out.setHorizontalAlignment(Element.ALIGN_CENTER);
				sms_out.setVerticalAlignment(Element.ALIGN_CENTER);
				sms_out.setBackgroundColor(BaseColor.WHITE);
				sms_out.setExtraParagraphSpace(5f);
				tableCharges.addCell(sms_out);
				
				PdfPCell trafficValue=new PdfPCell(new Paragraph(Double.toString(invoice.getTraffic_charge()),tableBodyCharges));
				trafficValue.setBorderColor(BaseColor.BLACK);
				trafficValue.setPaddingLeft(10);
				trafficValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				trafficValue.setVerticalAlignment(Element.ALIGN_CENTER);
				trafficValue.setBackgroundColor(BaseColor.WHITE);
				trafficValue.setExtraParagraphSpace(5f);
				tableCharges.addCell(trafficValue);
				
				
			}
			
			document.add(table);
			document.add(tableCharges);
			document.close();
			writer.close();
			return true;
		
		
		}catch(Exception e) {
			return false;
		}
	}

	
}
