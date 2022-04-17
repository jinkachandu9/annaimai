package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.s3.AmazonS3;
@Controller
public class ControllerA {
	 @Autowired
	    private downloadFile downloadfile;
	public List<User> finald=new ArrayList<>();
	@PostConstruct
	public void init()
	{
		NameObject nameObject=new NameObject();
		User obj=new User();
		for(int i=0;i<1;i++){
		obj.ARTIST=nameObject.names[i][0];
		obj.TELUGU=nameObject.names[i][1];                               
		obj.MEANING=nameObject.names[i][2];                               
		obj.HINDI=nameObject.names[i][3];                               
		obj.KANNADA=nameObject.names[i][4];                               
		obj.TAMIL=nameObject.names[i][5];                               
		this.finald.add(obj);
		}
	}
	@RequestMapping(value = "{id}",produces=MediaType.TEXT_HTML_VALUE)
    public  ResponseEntity<byte[]> Controller(@PathVariable("id") String argument) {
    	String fileName="page-56779.mp3";
		ByteArrayOutputStream downloadInputStream =  downloadfile.downloadFileFunction(fileName);
		return ResponseEntity.ok()
                .contentType(contentType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(downloadInputStream.toByteArray());
    }
	
	private MediaType contentType(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping(value="/",produces=MediaType.TEXT_HTML_VALUE)
	public String songs(Model model)
	{	
		model.addAttribute("allemplist",this.finald);
		
		return "index";
	}
	@GetMapping(value="/defect-details1",produces=MediaType.TEXT_HTML_VALUE)
	public String songs1(Model model)
	{	
		System.out.println("acv");
		  ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("index.html");
		model.addAttribute("allemplist",this.finald);
		
		return "annamai.html";
	}
	@GetMapping(value="/test2",produces=MediaType.TEXT_HTML_VALUE)
	public String songs2(Model model)
	{	
		return "annamia";
	}
}
