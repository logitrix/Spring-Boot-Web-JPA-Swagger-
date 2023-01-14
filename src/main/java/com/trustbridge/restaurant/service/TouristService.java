package com.trustbridge.restaurant.service;

import java.util.List;

import com.trustbridge.restaurant.api.Tourist;
import com.trustbridge.restaurant.api.TouristRecord;

public interface TouristService {

	TouristRecord getTourists() throws Exception;

	Tourist findById(String referenceNumber) throws Exception;

	Tourist create(Tourist tourist) throws Exception;

	Tourist update(Tourist tourist) throws Exception;

	Tourist delete(String referenceNumber) throws Exception;

}
