package com.example.demo.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Invoice;
import com.example.demo.services.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ServletContext context;
	
	@GetMapping("/")
	public List<Invoice> getAll(){
		return invoiceService.getAll();
	}
	
	@GetMapping("/{keyword}")
	public List<Invoice> getAllByPhoneNumber(@PathVariable String keyword){
		
		return invoiceService.findInvoicesByKeyWord(keyword);
		
		//model.addAttribute("listInvoices",listInvoices);
		
		//return "rest_data";
		
		
	}
	
	
	@GetMapping("/createPdf")
	@Produces("application/pdf")
	public void createPdf(HttpServletRequest request,HttpServletResponse response) {
		List<Invoice> listInvoice=invoiceService.getAll();
		boolean isFlag=invoiceService.createPdf(listInvoice,context,request,response);
		
		if(isFlag) {
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"listInvoice"+".pdf");
			fileDownload(fullPath,response,"listInvoice.pdf");
		
		
		}
		
		
	}
	
	private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
		File file=new File(fullPath);
		
		final int BUFFER_SIZE=4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream=new FileInputStream(file);
				String mimeType=context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				
				OutputStream outputStream=response.getOutputStream();
			
				byte[] buffer=new byte[BUFFER_SIZE];
				int bytesRead=-1;
				while((bytesRead=inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,bytesRead);
				}
				
				inputStream.close();
				outputStream.close();
				file.delete();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
