package com.example.PhoneCombinations.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PhoneCombinations.service.PhoneCombinationsService;

@Controller
public class PhoneCombinationsController {

	@Autowired
	PhoneCombinationsService phoneCombinationsService;

	@RequestMapping(value = "/phoneCombinations", method = RequestMethod.GET)

	public String generatePhoneCombinations(@RequestParam("phoneNumber") String strPhoneNumber,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model) {

		// Validate phone length
		if (strPhoneNumber.trim().length() != 7 && strPhoneNumber.trim().length() != 10) {
			// throw new Exception - direcct to error page;
			model.addAttribute("errormessage", "Input Error: Phone number length should be either 7 or 10");
			return "error";

		}

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(1000);

		Page<String> phonePage = phoneCombinationsService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				strPhoneNumber);

		model.addAttribute("phoneNumber", strPhoneNumber);
		model.addAttribute("phonePage", phonePage);

		model.addAttribute("totalphonenums", phonePage.getTotalElements());

		int totalPages = phonePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		// return outList;

		return "phonenumberslist";

	}

}
