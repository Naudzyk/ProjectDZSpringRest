package com.zhenya.org.Spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final String sensorName = "Sensor123";

		registerSensor(sensorName);

		Random random = new Random();

		double minTemperature = 0.0;
		double maxTemperature = 45.0;
		for (int i =0;i<500;i++){
			System.out.println(i);
			sendChanges(random.nextDouble()
			* maxTemperature
			, random.nextBoolean(), sensorName);
		}
	}

	private static void registerSensor(String sensorName){
		final String url ="http://localhost:8080/sensor/registration";

		Map<String, Object> jsonData = new HashMap<>();
		jsonData.put("name",sensorName);
	}
	private static void sendChanges(double value,boolean raining,String sensorName){
		final String url ="http://localhost:8080/weather/add";

		Map<String,Object> jsonData = new HashMap<>();
		jsonData.put("value",value);
		jsonData.put("raining", raining);
		jsonData.put("sensor",Map.of("name",sensorName));

		makePostRequestWithJSONData(url, jsonData);

	}

	private static void makePostRequestWithJSONData(String url , Map<String,Object> jsonData){
		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> request = new HttpEntity<>(jsonData,headers);

		try {
			restTemplate.exchange(url, HttpMethod.POST,request,String.class);

			System.out.println("Измернеие отправлены");

		}catch (HttpClientErrorException e){
			System.out.println("ОШИБКА");
			System.out.println(e.getMessage());
		}
		}

}
