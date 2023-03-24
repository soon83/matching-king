package com.soon83.interfaces;

import com.soon83.CommonResponse;
import com.soon83.JwtUtil;
import com.soon83.domain.AuthCommand;
import com.soon83.exception.auth.AuthInvalidTokenException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @PostMapping("/refresh")
    public CommonResponse<Void> refreshToken(@RequestBody @Valid AuthCommand.RefreshRequest request, HttpServletResponse response) {
        log.debug("# refreshToken # request: {}", request);
        JwtUtil.JwtVerifyResult validAuthToken = JwtUtil.verify(request.getMemberAuthToken());
        log.debug("### validAuthToken: {}, authToken: {}", validAuthToken, request.getMemberAuthToken());
        JwtUtil.JwtVerifyResult validRefreshToken = JwtUtil.verify(request.getMemberRefreshToken());
        log.debug("### validRefreshToken: {}, refreshToken: {}", validRefreshToken, request.getMemberRefreshToken());
        boolean isValidAuthToken = ObjectUtils.nullSafeEquals(request.getMemberEmail(), validAuthToken.getUsername());
        boolean isValidRefreshToken = ObjectUtils.nullSafeEquals(request.getMemberEmail(), validRefreshToken.getUsername());
        if (!isValidAuthToken || !isValidRefreshToken) throw new AuthInvalidTokenException();
        if (!validRefreshToken.isSuccess()) throw new AuthInvalidTokenException();
        JwtUtil.issueTokenToResponseHeader(validAuthToken.getUsername(), validAuthToken.getId(), validAuthToken.getNickname(), response);
        return CommonResponse.success();
    }
}
