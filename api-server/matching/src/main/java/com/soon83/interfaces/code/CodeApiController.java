package com.soon83.interfaces.code;

import com.soon83.CommonResponse;
import com.soon83.config.enumcode.EnumMapper;
import com.soon83.config.enumcode.EnumMapperValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/codes")
public class CodeApiController {

    private final EnumMapper enumMapper;

    @GetMapping
    public CommonResponse<Map<String, List<EnumMapperValue>>> getCodes() {
        return CommonResponse.success(enumMapper.getAll());
    }

    @GetMapping("/{codeName}")
    public CommonResponse<List<EnumMapperValue>> getCode(@PathVariable String codeName) {
        return CommonResponse.success(enumMapper.get(codeName));
    }
}