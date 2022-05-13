package com.school.work.controllers;

import com.school.work.dao.BookDao;
import com.school.work.dao.IssuesDao;
import com.school.work.dao.MemberDao;
import com.school.work.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberDao memberDao;

    @Autowired
    IssuesDao isuDao;

    @Autowired
    BookDao bkDao;


    @GetMapping("/members")
    public String all_active_members(Model m){
        System.out.println("All Active Members Started1");
        m.addAttribute("members",memberDao.getAllPresentMembers());
        m.addAttribute("memberMails", memberDao.getAllMemberEmails() );
        m.addAttribute("allMembers", memberDao.getAllMembers());
        System.out.println("All Active Members Started2");

        return "all_activeMembers";
    }

    @GetMapping("/members/all")
    public String all_all_members(Model m){
        System.out.println("All Members Started1");
        m.addAttribute("members",memberDao.getAllMembers());
        m.addAttribute("memberMails", memberDao.getAllMemberEmails() );
        System.out.println("All Members Started2");

        return "all_members";
    }

    @GetMapping("/members/{id}")
    public String member_detail (@PathVariable int id, Model m){
        System.out.println("Member Issues details started1");
        Member memberFound = memberDao.getMemberByMemberId(id);
        m.addAttribute("member",memberFound);

        List<Issues> isuList = isuDao.getMemberIssueDetails(id);
        m.addAttribute("isuList",isuList);
        m.addAttribute("count",isuDao.getActiveIssueCount(isuList));
        System.out.println("count=" + isuDao.getActiveIssueCount(isuList));

        List<String> mails = memberDao.getMemberEmailsByMemberId(id);
        m.addAttribute("mails",mails);

        List<Book> books = bkDao.getAllBooks();
        m.addAttribute("books",books);

        System.out.println("Member Issues details started2");

        return "member_detail";
    }

    @PostMapping("/members/{id}/edit")
    public String member_update(@ModelAttribute Member member, @PathVariable int id, Model m) {
        member.setLibraryId(id);
        memberDao.update(member);
        System.out.println("Member Issues details started3");

        return "redirect:/members/"+member.getLibraryId();
    }

    @PostMapping("/members/search")
    public String member_info(@RequestParam String LibraryId, Model m){
        if(!memberDao.isMemberIdPresent(LibraryId))
        {
            String msg = "Member Id does not exist!!! Add the member";
            m.addAttribute("msg", msg);
            return "error_page";
        }
        return "redirect:/members/" + LibraryId;
    }

    @GetMapping("/members/new")
    public String member_new(Model m){
        m.addAttribute("member",new Member());
        List<String> mails = new ArrayList<>();
        //m.addAttribute("memberMails", mails);
        return "new_member";
    }

    @PostMapping("/members/new")
    public String member_add(@ModelAttribute Member member, Model m){
        System.out.println("New Member started1");
        // System.out.println(member.getGender());
        int id = memberDao.save(member);

        return "redirect:/members/"+id;
    }

}
