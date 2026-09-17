package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Member not found: " + id));
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(long id, Member memberDetails) {
        Member existingMember = getMemberById(id);
        existingMember.setMail(memberDetails.getMail());
        existingMember.setName(memberDetails.getName());
        existingMember.setPhoneNumber(memberDetails.getPhoneNumber());
        return memberRepository.save(existingMember);
    }

    public void deleteMember(long id) {
        memberRepository.delete(getMemberById(id));
    }
}
