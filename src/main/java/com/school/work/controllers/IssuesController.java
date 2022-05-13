package com.school.work.controllers;

import com.school.work.dao.BookDao;
import com.school.work.dao.IssuesDao;
import com.school.work.dao.MemberDao;
import com.school.work.dao.UserinfoDao;
import com.school.work.models.Book;
import com.school.work.models.Issues;
import com.school.work.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IssuesController {

    @Autowired
    IssuesDao isuDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    BookDao bkDao;

    @Autowired
    UserinfoDao userDao;

    @GetMapping("/issues")
    public String all_all_issues(Model m){
        System.out.println("All Issues Started1");
        m.addAttribute("issues", isuDao.getAllIssues());
        m.addAttribute("books", bkDao.getAllBooks());
        m.addAttribute("members",memberDao.getAllMembers());
        System.out.println("All Issues Started2");

        return "all_issues";
    }

    @GetMapping("/issues/{id}")
    public String issue_detail (@PathVariable int id, Model m){
        System.out.println("Issues details started1");
        Issues issue = isuDao.getIssueByIssueId(id);

        isuDao.updateCurrentDelay(issue);

        m.addAttribute("isu",issue);
        System.out.println("Issues details started2");

        return "issue_detail";
    }

    @PostMapping("/issues/{id}/edit")
    public String issue_update(@ModelAttribute Issues isu, @PathVariable int id, Model m) {
        isu.setIssueId(id);
        isuDao.update(isu);
        System.out.println("Issues details started3");

        return "redirect:/issues/"+isu.getIssueId() ;
    }

    @GetMapping("/issues/member/{id}")
    public String all_issues_by_member(@PathVariable int id, @RequestParam(name = "allow", required = false) String edit, Model m){
        System.out.println("All Issues by member Started1");

        List<Issues> MemberIssues = isuDao.getMemberIssueDetails(id);
        m.addAttribute("isuList", MemberIssues);

        Member member = memberDao.getMemberByMemberId(id);
        m.addAttribute("member", member);

        List<Book> books = bkDao.getAllBooks();
        m.addAttribute("books",books);

        List<String> mails = memberDao.getMemberEmailsByMemberId(id);
        m.addAttribute("mails",mails);

        int edit2 = 0;
        if(edit!=null)  edit2 = Integer.parseInt(edit);
        if(edit2==154)  m.addAttribute("allow",1);
        else    m.addAttribute("allow",0);

        System.out.println("All Issues by member Started2");

        return "member_detail";
    }

    @GetMapping("/issues/book/{id}")
    public String all_issues_of_book(@PathVariable String id, Model m){
        System.out.println("All Issues of book Started1");

        List<Issues> BookIssues = isuDao.getBookIssueDetails(id);
        m.addAttribute("book_issue", BookIssues);

        Book book = bkDao.getBookByBookId(id);
        m.addAttribute("book",book);

        List<Member> members = memberDao.getAllMembers();
        m.addAttribute("members", members);

        System.out.println("All Issues of book Started2");

        return "book_issue_details";
    }

    @GetMapping("/issues/new")
    public String issue_new(Model m){
        System.out.println("Add a book Issue Started1");
        m.addAttribute("issue", new Issues());
        m.addAttribute("member",memberDao.getAllPresentMembers());
        int id = -1;
        m.addAttribute("LibraryId",id);
        System.out.println("Add a book Issue Started2");

        return "new_issue";
    }


    @PostMapping("/issues/new")
    public String issue_add(@ModelAttribute Issues issue, Model m){
        // System.out.println(employee.getGender());
        int id = isuDao.save(issue);
        // int id=1;
        return "redirect:/issues/"+id;
    }


    @PostMapping("/issues/members/search")
    public String member_issue_info(@RequestParam String LibraryId, Model m){
        if(!memberDao.isMemberIdPresent(LibraryId))
        {
            String msg = "Member Id does not exist!!! Add the member";
            m.addAttribute("msg", msg);
            return "error_page";
        }
        System.out.println("Leaving this issues of member search function");
        return "redirect:/issues/new/member/" + LibraryId;
    }

    @GetMapping("issues/book/return/{id}")
    public String book_return_info(@PathVariable int id, Model m){
        Issues issue = isuDao.getIssueByIssueId(id);

        return "redirect:/issues/new/member/" + issue.getLibraryId() + "/" + issue.getBookId() + "?check=2";
    }


    @GetMapping("/issues/new/member/{id}")
    public String issue_book_detail(@PathVariable int id, Model m) {
        System.out.println("I am here in issues/new/member/{id}");
        m.addAttribute("member",memberDao.getMemberByMemberId(id));

        List<String> mails = memberDao.getMemberEmailsByMemberId(id);
        m.addAttribute("mails",mails);

        List<Issues> isuList = isuDao.getMemberIssueDetails(id);
        m.addAttribute("isuList",isuList);

        m.addAttribute("count",isuDao.getActiveIssueCount(isuList));
        m.addAttribute("issues", new Issues());

        m.addAttribute("show_book",0);
        m.addAttribute("book", new Book());


        //System.out.println("issues/new/member/{id} exited!!");

        return "issue_book";
    }

    @PostMapping("/issues/books/search/{id}")
    public String book_issue_info(@RequestParam String BookId, @PathVariable int id, Model m){
        if(!bkDao.isBookIdPresent(BookId))
        {
            String msg = "Book Id does not exist!!! Add the book";
            m.addAttribute("msg", msg);
            return "error_page";
        }
        System.out.println("Leaving this function");
        m.addAttribute("book",bkDao.getBookByBookId(BookId));

        return "redirect:/issues/new/member/" + id + "/" + BookId + "?check=1";
    }


    @GetMapping("/issues/new/member/{id1}/{id2}")
    public String issue_book_detail(@PathVariable int id1, @PathVariable String id2, @RequestParam(value="check") int check,
                                    Model m) {
        System.out.println("I am here in issues/new/member/{id1}/{id2}");
        m.addAttribute("member",memberDao.getMemberByMemberId(id1));

        List<String> mails = memberDao.getMemberEmailsByMemberId(id1);
        m.addAttribute("mails",mails);

        List<Issues> isuList = isuDao.getMemberIssueDetails(id1);
        m.addAttribute("isuList",isuList);

        m.addAttribute("count",isuDao.getActiveIssueCount(isuList) );
        m.addAttribute("issues", new Issues());

        if(check == 1){         //issue book
            m.addAttribute("show_book",1);
            m.addAttribute("book", bkDao.getBookByBookId(id2));
        }
        else if(check == 2){    //return book
            m.addAttribute("show_book",2);
            m.addAttribute("book",bkDao.getBookByBookId(id2));
            Issues this_isu = isuDao.getIssueId(id1,id2);
            //isuDao.updateCurrentDelay(this_isu);
            m.addAttribute("isu",this_isu);
        }
        else{       //Do nothing
            m.addAttribute("show_book",0);
        }

        System.out.println("issues/new/member/{id1}/{id2} exited!!");

        return "issue_book";
    }

    @PostMapping("/issues/{id1}/book/add")
    public String issue_add_book(@ModelAttribute Issues issues, @PathVariable int id1, Model m) {
        //System.out.println("Hi, I am in /issues/{id1}/book/add");
        issues.setLibraryId(id1);
        isuDao.save(issues);
        Book book = bkDao.getBookByBookId(issues.getBookId());
        bkDao.updateBookStatus(book, "Issued", 1);

        return "redirect:/issues/new/member/" + id1;
    }

    @PostMapping("/issues/book/return/{id}")
    public String issue_return_book(@PathVariable int id, @ModelAttribute Issues isu, Model m) {
        System.out.println("Hi, I am in /issues/book/return");
        isu.setIssueId(id);
        isuDao.update(isu);
        System.out.println("Hi, I am in /issues/book/return 2");
        Issues isu_updated = isuDao.getIssueByIssueId(id);
        System.out.println(isu_updated.getBookId());

        Book book = bkDao.getBookByBookId(isu_updated.getBookId());
        bkDao.updateBookStatus(book, "Available", 0);
        System.out.println("Hi, I am in /issues/book/return 3");

        return "redirect:/issues/new/member/" + isu_updated.getLibraryId();
    }

}
