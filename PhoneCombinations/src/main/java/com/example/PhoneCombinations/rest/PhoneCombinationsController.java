package com.example.PhoneCombinations.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public List<String> generatePhoneCombinations(@RequestParam("phoneNumber") Integer phoneNumber) throws Exception{
		
		String strPhoneNumber = phoneNumber.toString();
		
		//Validate phone length
		if(strPhoneNumber.length() != 7 || strPhoneNumber.length() != 10) {
			throw new Exception();
		}
		
		
		List<String> outList =  letterCombinations(strPhoneNumber);
		
		int countOfCombinations = outList.size();
		
		return outList;
		
		}
	
	public List<String> letterCombinations(String phoneDigits) {
		
		  HashMap<Character, String> hashMap = new HashMap<>();
		  	
		  hashMap.put('0', "0");
		  hashMap.put('1', "1");
		  hashMap.put('2', "ABC");
		  hashMap.put('3', "DEF");
		  hashMap.put('4', "GHI");
		  hashMap.put('5', "JKL");
		  hashMap.put('6', "MNO");
		  hashMap.put('7', "PQRS");
		  hashMap.put('8', "TUV");
		  hashMap.put('9', "WXYZ");
		
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
