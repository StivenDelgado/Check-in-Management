package com.example.check_in.management.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.check_in.management.services.HttpService;
import com.example.check_in.management.config.UserManagementApiProperties;

@Component
public class ApiClient {

    @Autowired
    private HttpService httpService;

    @Autowired
    private UserManagementApiProperties userManagementApiProperties;

    public boolean employeeExists(Long id, String authorization) {
        String url = userManagementApiProperties.getBaseUrl() + "/employee/exists/" + id;

        ResponseEntity<Boolean> response = httpService.get(
                url,
                Boolean.class,
                Map.of("Authorization", authorization)
        );

        return response.getBody();
    }
}
