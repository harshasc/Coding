package com.example.PhoneCombinations.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhoneCombinationsService {

	public Page<String> findPaginated(Pageable pageable, String phoneDigits) {

		List<String> phoneCombinations = letterCombinations(phoneDigits);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<String> list;

		if (phoneCombinations.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, phoneCombinations.size());
			list = phoneCombinations.subList(startItem, toIndex);
		}

		Page<String> phonePage = new PageImpl<String>(list, PageRequest.of(currentPage, pageSize),
				phoneCombinations.size());

		return phonePage;
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
