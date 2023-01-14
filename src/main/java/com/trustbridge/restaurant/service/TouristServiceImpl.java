package com.trustbridge.restaurant.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.trustbridge.restaurant.api.Tourist;
import com.trustbridge.restaurant.api.TouristRecord;

@Service
public class TouristServiceImpl implements TouristService {

	@Value("${tourist.base.url}")
	private String baseUrl;

	private final String fetch_3rd_page_only = "?page=3";

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public TouristRecord getTourists() throws Exception {
		ResponseEntity<TouristRecord> records = restTemplate.getForEntity(baseUrl + fetch_3rd_page_only,
				TouristRecord.class);
		if (records == null || records.getBody() == null || records.getBody().getData() == null
				|| records.getBody().getData().size() == 0) {
			throw new Exception("Unable to load tourist record");
		}

		Collections.sort(records.getBody().getData(), new Comparator<Tourist>() {
			@Override
			public int compare(final Tourist object1, final Tourist object2) {
				return object1.getTourist_email().compareTo(object2.getTourist_email());
			}
		});
		records.getBody().setData(records.getBody().getData());
		return records.getBody();
	}

	@Override
	public Tourist findById(String id) throws Exception {
		TouristRecord record = getTourists();
		Tourist tourist = record.getData().stream().filter(data -> id.equals(data.getId())).findAny().orElse(null);
		if (tourist == null) {
			throw new Exception("Tourist id " + id + " not found!");
		}
		return tourist;
	}

	@Override
	public Tourist create(Tourist tourist) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> map = new HashMap<>();
		map.put("tourist_name", tourist.getTourist_name());
		map.put("tourist_email", tourist.getTourist_email());
		map.put("tourist_location", tourist.getTourist_location());

		ResponseEntity<Tourist> response = restTemplate.postForEntity(baseUrl, map, Tourist.class);
		return response.getBody();
	}

	@Override
	public Tourist update(Tourist tourist) throws Exception {
		HttpEntity<Tourist> entity = new HttpEntity<Tourist>(tourist);
		ResponseEntity<Tourist> response = null;
		try {
			return restTemplate.exchange(baseUrl, HttpMethod.PUT, entity, Tourist.class).getBody();
		} catch (RestClientException e) {
			throw new Exception("Update Endpoint is not available at the moment.");
		}
	}

	@Override
	public Tourist delete(String referenceNumber) throws Exception {
		Tourist tourist = findById(referenceNumber);
		HttpEntity<Tourist> entity = new HttpEntity<Tourist>(tourist);
		try {
			return restTemplate.exchange(baseUrl, HttpMethod.DELETE, entity, Tourist.class).getBody();
		} catch (RestClientException e) {
			throw new Exception("Delete Endpoint is not available at the moment.");
		}
	}

}
