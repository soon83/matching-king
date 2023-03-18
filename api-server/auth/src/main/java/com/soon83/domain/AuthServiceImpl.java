package com.soon83.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.CommonResponse;
import com.soon83.exception.auth.AuthMemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public AuthQuery.AuthAdaptor loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        String response = restTemplate.getForObject(
                String.format("http://localhost:8002/api/v1/members/%s/auth", memberEmail),
                String.class
        );
        log.debug("# loadUserByUsername # response: {}", response);

        CommonResponse<AuthQuery.Info> memberResponse;
        try {
            memberResponse = objectMapper.readValue(response, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new AuthMemberNotFoundException();
        }
        return new AuthQuery.AuthAdaptor(memberResponse.getData());
    }
}
