package com.school.work.controllers;

import com.school.work.dao.*;
import com.school.work.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookDao bkdao;

    @Autowired
    SubjectDao subDao;

    @Autowired
    UserinfoDao userDao;

    @Autowired
    IssuesDao isuDao;

    @Autowired
    PurchaseDao purchDao;

    @Autowired
    SupplierDao supDao;

    // admin
    // employee, admin "/books/new", "/books/purchaseAdd/{id}", "/books/purchase/new", "/subjects/new", "/subjects/{id}/edit"
    // all "/books/search","/books/{id}","/books", "/subjects/all", "/subjects/{id}"
    @GetMapping("/books/{id}")
    public String book_detail (@PathVariable String id, @RequestParam(name="allow", required = false) String edit, Model m){
        System.out.println("Book details started1");
        Book bookFound = bkdao.getBookByBookId(id);
        m.addAttribute("book", bookFound);

        List<Issues> isuList = isuDao.getBookIssueDetails(id);
        m.addAttribute("isuList",isuList);
        m.addAttribute("subjects",subDao.getAllSubjects());
        String subject_number = bookFound.getSubjectNumber();

        Subject sub = subDao.getSubjectBySubjectNumber(subject_number);
        m.addAttribute("subject_name", sub.getSubjectName());

        System.out.println("Book details started2");

        int edit2 = 0;
        if(edit!=null)  edit2 = Integer.parseInt(edit);
        if(edit2==154){ //edit boook details
            m.addAttribute("allow",1);
        }
        else if(edit2==156){    //edit purchase details
            m.addAttribute("allow",2);
            m.addAttribute("purchase", purchDao.getPurchaseByBookId(id));
            m.addAttribute("suppliers", supDao.getAllSuppliers());
        }
        else{
            m.addAttribute("allow",0);
        }

        System.out.println("Book details started3");

        return "book_detail";
    }

    @PostMapping("/books/{id}/edit")
    public String book_update(@ModelAttribute Book book, @PathVariable String id, Model m) {
        book.setBookId(id);
        if(book.getTitle()==null || book.getWriter()==null)
        {
            String msg = "Please add valid details!!!";
            m.addAttribute("msg", msg);
            return "error_page";
        }
        bkdao.update(book);
        return "redirect:/books/"+book.getBookId();
    }

    @PostMapping("/books/search")
    public String book_info(@RequestParam String BookId, Model m){
        if(!bkdao.isBookIdPresent(BookId))
        {
            String msg = "Book Id does not exist!!! Add the book";
            m.addAttribute("msg", msg);
            return "error_page";
        }
        return "redirect:/books/" + BookId;
    }

    @GetMapping("/books")
    public String all_all_books(Model m){
        System.out.println("All Books Started");
        m.addAttribute("books", bkdao.getAllBooks());

        return "all_books";
    }

    @GetMapping("/books/new")
    public String book_new(Model m){
        m.addAttribute("book",new Book());
        m.addAttribute("subjects",subDao.getAllSubjects());

        return "new_book";
    }

    @PostMapping("/books/new")
    public String book_add(@ModelAttribute Book book,Model m){
        System.out.println("New Book started1");
        bkdao.save(book);

        return "redirect:/books/purchaseAdd/"+book.getBookId();
    }

    @GetMapping("/books/purchaseAdd/{id}")
    public String book_purchase_add(@PathVariable String id, Model m){
        Book book = bkdao.getBookByBookId(id);
        m.addAttribute("book", book);

        String subject_number = book.getSubjectNumber();
        Subject sub = subDao.getSubjectBySubjectNumber(subject_number);
        m.addAttribute("subject_name", sub.getSubjectName());
        m.addAttribute("purchase", new Purchase());
        m.addAttribute("suppliers", supDao.getAllSuppliers());

        return "add_book_purchase";
    }

    @PostMapping("/books/purchase/new")
    public String book_purchase_save(@ModelAttribute Purchase purchase, Model m){
        System.out.println("New Book Purchase started 1");
        purchDao.save(purchase);

        return "redirect:/books/" + purchase.getBookId();
    }


    //Subject Controllers
    @GetMapping("/subjects/new")
    public String subject_new(Model m){
        m.addAttribute("subject", new Subject());
        System.out.println("In Subject-new controller 1");

        return "new_subject";
    }

    @PostMapping("/subjects/new")
    public String subject_add(@ModelAttribute Subject subject, Model m){
        System.out.println("In Subject-add controller 1");
        int id = subDao.save(subject);
        subject.setSubjectId(id);
        System.out.println("In Subject-add controller 2");

        return "redirect:/subjects/" + subject.getSubjectId() + "?check=0";
    }

    @GetMapping("/subjects/all")
    public String all_subjects(Model m){
        System.out.println("In all_subjects controller 1");
        m.addAttribute("all_subjects", subDao.getAllSubjects());

        return "all_subjects";
    }

    @GetMapping("/subjects/{id}")
    public String edit_subject(@PathVariable int id,@RequestParam(value = "check", required = false) String check, Model m){
        System.out.println("In subject/{id} controller 1");
        Subject subject = subDao.getSubjectBySubjectId(id);
        String subjectNumber = subject.getSubjectNumber();
        m.addAttribute("subject", subject);
        m.addAttribute("books",bkdao.getAllBooksOfSubject(subjectNumber));
        System.out.println("In /subject/{id} controller 2");
        if(check!=null) m.addAttribute("check", Integer.parseInt(check));
        else    m.addAttribute("check", 0);

        return "edit_subject";
    }

    @PostMapping("/subjects/{id}/edit")
    public String subject_update(@ModelAttribute Subject subject, @PathVariable int id, Model m) {
        subject.setSubjectId(id);
        subDao.update(subject);

        System.out.println("subject_update started 1");

        return "redirect:/subjects/"+subject.getSubjectId() + "?check=1";
    }

}
