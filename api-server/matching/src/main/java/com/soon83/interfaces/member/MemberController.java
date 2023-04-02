package com.soon83.interfaces.member;

import com.soon83.CommonResponse;
import com.soon83.application.MemberApplication;
import com.soon83.domain.auth.AuthUser;
import com.soon83.domain.member.MemberDetailQuery;
import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.member.MemberSearchConditionQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public CommonResponse<MemberCreateResponse> registerMember(@RequestBody @Valid MemberRegisterRequest request) {
        log.debug("# registerMember # request: {}", request);
        var createMemberCommand = request.toCreateMemberCommand();
        var createMatchingConditionCommand = request.memberMatchingCondition().toCreateMatchingConditionCommand();
        Long memberId = memberApplication.registerMember(createMemberCommand, createMatchingConditionCommand);
        MemberCreateResponse response = new MemberCreateResponse(memberId);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MemberResponse>> searchMembers(@ModelAttribute @Valid MemberSearchCondition request) {
        log.debug("# searchMembers # request: {}", request);
        MemberSearchConditionQuery condition = request.toSearchMemberCondition();
        List<MemberQuery> memberList = memberApplication.searchMembers(condition);
        List<MemberResponse> response = memberList.stream()
                .map(MemberResponse::new)
                .toList();
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 조회
     */
    @GetMapping("/{memberId}")
    public CommonResponse<MemberResponse> searchMember(@PathVariable Long memberId, @AuthenticationPrincipal AuthUser authUser) {
        log.debug("# searchMember # authUser: {}", authUser);
        log.debug("# searchMember # memberId: {}", memberId);
        MemberQuery member = memberApplication.searchMember(memberId);
        MemberResponse response = new MemberResponse(member);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 조회 (상세)
     */
    @GetMapping("/{memberId}/detail")
    public CommonResponse<MemberDetailResponse> searchMemberDetail(@PathVariable Long memberId) {
        log.debug("# searchMemberDetail # memberId: {}", memberId);
        MemberDetailQuery member = memberApplication.searchMemberDetail(memberId);
        MemberDetailResponse response = new MemberDetailResponse(member);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 조회 (인증)
     */
    @GetMapping("/{memberEmail}/auth")
    public CommonResponse<MemberAuthResponse> searchMemberAuth(@PathVariable String memberEmail) {
        log.debug("# searchMemberAuth # memberEmail: {}", memberEmail);
        MemberQuery member = memberApplication.searchMemberByEmail(memberEmail);
        MemberAuthResponse response = new MemberAuthResponse(member);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 수정
     */
    @PutMapping("/{memberId}")
    public CommonResponse<Void> editMember(@PathVariable Long memberId, @RequestBody @Valid MemberEditRequest request) {
        log.debug("# editMember # memberId: {}, request: {}", memberId, request);
        var editMemberCommand = request.toEditMemberCommand();
        var editMatchingConditionCommand = request.memberMatchingCondition().toEditMatchingConditionCommand();
        memberApplication.editMember(memberId, editMemberCommand, editMatchingConditionCommand);
        return CommonResponse.success();
    }

    /**
     * [회원] 단건 삭제
     */
    @DeleteMapping("/{memberId}")
    public CommonResponse<Void> removeMember(@PathVariable Long memberId) {
        log.debug("# removeMember # memberId: {}", memberId);
        memberApplication.removeMember(memberId);
        return CommonResponse.success();
    }
}
