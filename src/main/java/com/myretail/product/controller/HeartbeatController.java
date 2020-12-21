package com.myretail.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HeartbeatController {

	@Value("${application.name}")
	String applicationName;

	@Value("${application.version}")
	String applicationVersion;

	@GetMapping(value = "/heartbeat", produces = "application/json")
	Map<String, String> getHeartBeat() {
		return Map.of("name", applicationName, "version", applicationVersion);
	}
}
