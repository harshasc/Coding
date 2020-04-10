package com.example.PhoneCombinations.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneCombinationsController {
	@RequestMapping(
			  value = "/phoneCombinations", 
			  method = RequestMethod.GET, 
			  produces = "application/json"
			)
	@ResponseBody
	public List<String> generatePhoneCombinations(@RequestParam("phoneNumber") String strPhoneNumber){
		
		//Validate phone length
		if(strPhoneNumber.trim().length() != 7 && strPhoneNumber.trim().length() != 10) {
			//throw new Exception();
			
			  List<String> list = new ArrayList<String>();
			  list.add(HttpStatus.BAD_REQUEST.toString());
			  list.add("Phone number length should be either 7 or 10"); 
			  return list;
			 
		}
		
		List<String> outList = new ArrayList<>();
		List<String> list =  letterCombinations(strPhoneNumber);
		outList.add(String.valueOf(list.size()));
		outList.addAll(list);
		return outList;
		
		}
	
	public List<String> letterCombinations(String phoneDigits) {
		
		  HashMap<Character, String> hashMap = new HashMap<>();
		  	
		  hashMap.put('0', "0");
		  hashMap.put('1', "1");
		  hashMap.put('2', "2ABC");
		  hashMap.put('3', "3DEF");
		  hashMap.put('4', "4GHI");
		  hashMap.put('5', "5JKL");
		  hashMap.put('6', "6MNO");
		  hashMap.put('7', "7PQRS");
		  hashMap.put('8', "8TUV");
		  hashMap.put('9', "9WXYZ");
		
		List<String> outputList = new ArrayList<>();
		
	    if (phoneDigits == null || phoneDigits.length() == 0) {
	        return outputList;
	    }
	 
	    outputList.add("");
	 
	    for (int i = 0; i < phoneDigits.length(); i++) {
 
	        String numChars = hashMap.get(phoneDigits.charAt(i));
	        ArrayList<String> tmpList = new ArrayList<>();
	        		
	        for (int j = 0; j < outputList.size(); j++) {
	            for (int k = 0; k < numChars.length(); k++) {
	            	tmpList.add(new StringBuilder(outputList.get(j)).append(numChars.charAt(k)).toString());
	            }
	        }
	 
	        outputList.clear();
	        outputList.addAll(tmpList);
	    }
	 
	    return outputList;
	}
		
	
}
