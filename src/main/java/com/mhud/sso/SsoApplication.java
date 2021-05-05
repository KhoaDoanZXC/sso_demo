package com.mhud.sso;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@SpringBootApplication
@Controller
public class SsoApplication {
	Logger logger = LoggerFactory.getLogger(SsoApplication.class);
	@Autowired
	private ObjectMapper objectMapper;
	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

	@RequestMapping(value = "/user")
	public String user(Model model, Principal principal) {
		JsonNode node = objectMapper.valueToTree(principal);
		JsonNode details = node.get("userAuthentication").get("details");
		model.addAttribute("id", details.get("id").textValue());
		model.addAttribute("username", details.get("name").textValue());
		model.addAttribute("email", details.get("email").textValue());
		model.addAttribute("picture", details.get("picture").textValue());

		return "loggedIn";
	}
}
