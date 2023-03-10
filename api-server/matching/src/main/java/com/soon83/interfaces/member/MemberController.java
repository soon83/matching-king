package com.soon83.interfaces.member;

import com.soon83.CommonResponse;
import com.soon83.application.MemberApplication;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.model.MemberCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberApplication memberApplication;

    /**
     * [회원] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MemberDto.CreateResponse> registerMember(@RequestBody @Valid MemberDto.RegisterRequest request) {
        log.debug("# registerMember # request: {}", request);

        MemberCommand.CreateMember command = request.toCreateMemberCommand();
        Long registeredMemberId = memberApplication.registerMember(command);
        MemberDto.CreateResponse response = new MemberDto.CreateResponse(registeredMemberId);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MemberDto.Main>> getMembers() {
        return CommonResponse.success(
                List.of(
                        MemberDto.Main.builder()
                                .memberId(1L)
                                .memberEmail("member@email.com")
                                .memberNickname("matchingKing")
                                .memberGender(Member.Gender.MALE)
                                .memberMbti(Member.Mbti.ISTP)
                                .memberType(Member.Type.FREE)
                                .memberRole(Member.Role.MEMBER)
                                .build()
                )
        );
    }

    /**
     * [회원] 단건 조회
     */
    @GetMapping("/{memberId}")
    public CommonResponse<MemberDto.Main> getMember(@PathVariable Long memberId) {
        log.debug("# getMember # memberId: {}", memberId);
        return CommonResponse.success(
                MemberDto.Main.builder()
                        .memberId(1L)
                        .memberEmail("member@email.com")
                        .memberNickname("matchingKing")
                        .memberGender(Member.Gender.MALE)
                        .memberMbti(Member.Mbti.ISTP)
                        .memberType(Member.Type.FREE)
                        .memberRole(Member.Role.MEMBER)
                        .build()
        );
    }

    /**
     * [회원] 단건 수정
     */
    @PutMapping("/{memberId}")
    public CommonResponse<Void> editMember(@PathVariable Long memberId, @RequestBody @Valid MemberDto.EditRequest request) {
        log.debug("# editMember # memberId: {}, request: {}", memberId, request);
        return CommonResponse.success();
    }

    /**
     * [회원] 단건 삭제
     */
    @DeleteMapping("/{memberId}")
    public CommonResponse<Void> removeMember(@PathVariable Long memberId) {
        log.debug("# removeMember # memberId: {}", memberId);
        return CommonResponse.success();
    }
}
