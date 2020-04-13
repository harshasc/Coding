package com.example.PhoneCombinations.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PhoneCombinationsControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	PhoneCombinationsController phoneCombinationsController;

	public void init() {

	}

	@Test
	public void testPhoneNumberNotCorrectFormat() {
		String out = (this.restTemplate.getForObject("http://localhost:" + port + "/phoneCombinations?phoneNumber=542",
				String.class));
		Assert.notNull(out, "Collection should not be empty");
		Assert.hasText("Input Error", "Response is good for Negative test for Phone validation");
	}

	@Test
	public void testPhoneNumber() {
		String out = (this.restTemplate
				.getForObject("http://localhost:" + port + "/phoneCombinations?phoneNumber=2403867231", String.class));
		Assert.notNull(out, "Response should not be empty");
		Assert.hasText("Total Phone Numbers Combinations: 81920", "Response is good");

		Assert.doesNotContain(out, "Input Error");
	}

}
