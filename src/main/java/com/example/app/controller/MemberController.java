package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberMapper mapper;
	
	//会員一覧の表示
	@GetMapping("/")
	public String list(Model model) throws Exception {
		model.addAttribute("members", mapper.selectMembers());
		return "members";
	}
	
	//会員追加フォームの表示
	@GetMapping("add")
	public String add(Model model) throws Exception {
		Member member = new Member();
		member.setAge(20);
		member.setTypeId(1);
		model.addAttribute("member", member);
		return "addMember";
	}
	
	//会員追加
	@PostMapping("add")
	public String add(@Valid Member member, Errors errors) throws Exception {
		if(errors.hasErrors()) {
			return "addMember";
		}
		
		mapper.addMember(member);
		return "redirect:/";
	}
	
}
