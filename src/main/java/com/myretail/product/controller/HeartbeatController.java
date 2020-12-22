package com.myretail.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HeartbeatController {

	@Value("${application.name}")
	private String applicationName;

	@Value("${application.version}")
	private String applicationVersion;

	/**
     * This method returns system's metadata like application name and version.
     *
     * @return HeartBeatResponse
     */
	@GetMapping(value = "/heartbeat", produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> getHeartBeat() {
		return Map.of("name", applicationName, "version", applicationVersion);
	}
}
