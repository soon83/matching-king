package com.soon83.config;

import com.soon83.config.enumcode.EnumMapper;
import com.soon83.domain.member.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnumCodeConfig {
    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("member-role", Member.Role.class);
        enumMapper.put("member-type", Member.Type.class);
        enumMapper.put("member-gender", Member.Gender.class);
        enumMapper.put("member-mbti", Member.Mbti.class);
        return enumMapper;
    }
}
