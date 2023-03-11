package com.soon83.interfaces.member;

import com.soon83.CommonResponse;
import com.soon83.application.MemberApplication;
import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;
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
        var createMemberCommand = request.toCreateMemberCommand();
        var createMemberConditionCommand = request.toCreateMemberConditionCommand();
        var createMemberMatchingConditionCommand = request.toCreateMemberMatchingConditionCommand();
        Long memberId = memberApplication.registerMember(createMemberCommand, createMemberConditionCommand, createMemberMatchingConditionCommand);
        MemberDto.CreateResponse response = new MemberDto.CreateResponse(memberId);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MemberDto.Main>> searchMembers(@ModelAttribute @Valid MemberDto.SearchCondition request) {
        log.debug("# searchMembers # request: {}", request);
        // TODO 페이징 해야 함
        MemberQuery.SearchCondition condition = request.toSearchMemberCondition();
        List<MemberQuery.Main> memberList = memberApplication.searchMembers(condition);
        List<MemberDto.Main> response = memberList.stream()
                .map(MemberDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 조회
     */
    @GetMapping("/{memberId}")
    public CommonResponse<MemberDto.Main> searchMember(@PathVariable Long memberId) {
        log.debug("# searchMember # memberId: {}", memberId);
        MemberQuery.Main member = memberApplication.searchMember(memberId);
        MemberDto.Main response = new MemberDto.Main(member);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 상세 조회
     */
    @GetMapping("/{memberId}/detail")
    public CommonResponse<MemberDto.Detail> searchMemberDetail(@PathVariable Long memberId) {
        log.debug("# searchMemberDetail # memberId: {}", memberId);
        MemberQuery.Detail member = memberApplication.searchMemberDetail(memberId);
        MemberDto.Detail response = new MemberDto.Detail(member);
        return CommonResponse.success(response);
    }

    /**
     * [회원] 단건 수정
     */
    @PutMapping("/{memberId}")
    public CommonResponse<Void> editMember(@PathVariable Long memberId, @RequestBody @Valid MemberDto.EditRequest request) {
        log.debug("# editMember # memberId: {}, request: {}", memberId, request);
        MemberCommand.EditMember command = request.EditMemberCommand();
        memberApplication.editMember(memberId, command);
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
