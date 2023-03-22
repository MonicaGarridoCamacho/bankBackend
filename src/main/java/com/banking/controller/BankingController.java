package com.banking.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.jdbc.Transactions;

@RestController

@RequestMapping("test")
public class BankingController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/transactions")
	public @ResponseBody ResponseEntity<String> transactions() {
		/*List<Transactions> list = new ArrayList<Transactions>();
		List<JSONObject> listJSON = new ArrayList<JSONObject>();
		jdbcTemplate.query("SELECT * FROM transactions", new Object[] {},
				(rs, rowNum) -> new Transactions(rs.getString("accountId"), rs.getString("transactionId"),
						rs.getString("transactionInformation"), rs.getString("addressLine"), rs.getString("amount")))
				.forEach(thing -> list.add(0, thing));
		 
		System.out.println("list Moni " + list);
	    //This part is not working.
	    /*try {
	    	
	        for (Transactions tr : list) {
	        	JSONObject json = new JSONObject();
	        	json.put("accountId", tr.getAccountId());
	        	json.put("transactionId", tr.getTransactionId());
	        	json.put("transactionInformation", tr.getTransactionInformation());
	        	json.put("addressLine", tr.getAddressLine());
	        	json.put("amount", tr.getAmount());
	        	listJSON.add(json);
	        }
	    } catch (Exception e) {
	    }*/
	   
	   /* return ResponseEntity<String>(list.toString(), HttpStatus.OK);*/
		List<Transactions> list = new ArrayList<Transactions>();
		List<JSONObject> listJSON = new ArrayList<JSONObject>();
		jdbcTemplate.query("SELECT * FROM transactions", new Object[] {},
				(rs, rowNum) -> new Transactions(rs.getString("accountId"), rs.getString("transactionId"),
						rs.getString("transactionInformation"), rs.getString("addressLine"), rs.getString("amount")))
				.forEach(thing -> list.add(0, thing));
		 
		System.out.println("list Moni " + list);
		try {
	    	
	        for (Transactions tr : list) {
	        	JSONObject json = new JSONObject();
	        	json.put("accountId", tr.getAccountId());
	        	json.put("transactionId", tr.getTransactionId());
	        	json.put("transactionInformation", tr.getTransactionInformation());
	        	json.put("addressLine", tr.getAddressLine());
	        	json.put("amount", tr.getAmount());
	        	listJSON.add(json);
	        }
	    } catch (Exception e) {
	    }
		return new ResponseEntity<String>(listJSON.toString(), HttpStatus.OK); 
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/hello")
	public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
	    return "Hello " + name;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/accounts")
    public Transactions transactionsId(@RequestParam(name = "accountId", defaultValue = "0") String accountId) {
        List<String> list = new ArrayList<>();
        list.add("Table data...");
        return jdbcTemplate.queryForObject(
                "SELECT * FROM transactions WHERE accountId=?", new Object[]{ accountId },
                (rs,rowNum) -> new Transactions(rs.getString("accountId"), rs.getString("transactionId"), rs.getString("transactionInformation"), rs.getString("addressLine"), rs.getString("amount")));

    }

}