package com.school.work.controllers;

import com.school.work.dao.BookDao;
import com.school.work.dao.PurchaseDao;
import com.school.work.dao.SupplierDao;
import com.school.work.dao.WithdrawalDao;
import com.school.work.models.Purchase;
import com.school.work.models.Supplier;
import com.school.work.models.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SupplierController {

    @Autowired
    BookDao bkdao;

    @Autowired
    SupplierDao supDao;

    @Autowired
    PurchaseDao purchDao;

    @Autowired
    WithdrawalDao withDao;


    @GetMapping("/suppliers")
    public String all_all_suppliers(Model m){
        System.out.println("All Suppliers Started");
        m.addAttribute("suppliers", supDao.getAllSuppliers());

        return "all_suppliers";
    }

    @GetMapping("/suppliers/{id}")
    public String supplier_detail(@PathVariable String id, Model m){
        System.out.println("Supplier Details Started1");
        m.addAttribute("supplier", supDao.getSupplierBySupplierId(id));
        m.addAttribute("all_purchases", purchDao.getPurchaseOfSupplier(id));

        return "supplier_detail";
    }

    @GetMapping("/suppliers/new")
    public String supplier_new(Model m){
        m.addAttribute("supplier",new Supplier());

        return "new_supplier";
    }

    @PostMapping("/suppliers/new")
    public String supplier_add(@ModelAttribute Supplier supplier,Model m){
        System.out.println("New Supplier started1");
        String id = supDao.save(supplier);
        supplier.setSupplierId(id);

        return "redirect:/suppliers/"+ supplier.getSupplierId();
    }

    @GetMapping("/purchases")
    public String all_all_purchase(Model m){
        System.out.println("All Purchases Started");
        m.addAttribute("purchases", purchDao.getAllPurchases());

        return "all_purchases";
    }

    @PostMapping("/purchases/{id}/edit")
    public String book_update(@ModelAttribute Purchase purchase, @PathVariable String id, Model m) {
        purchase.setBookId(id);
        purchDao.update(purchase);

        return "redirect:/books/"+purchase.getBookId();
    }

    @GetMapping("/purchases/{id}/edit")
    public String book_update2(@ModelAttribute Purchase purchase, @PathVariable String id, Model m) {
        purchase.setBookId(id);
        purchDao.update(purchase);

        return "redirect:/books/"+purchase.getBookId();
    }

    @GetMapping("/purchases/new")
    public String purchase_new(Model m){
        m.addAttribute("purchase",new Purchase());
        m.addAttribute("books", bkdao.getAllBooks());
        m.addAttribute("suppliers",supDao.getAllSuppliers());

        return "new_purchase";
    }

    @PostMapping("/purchases/new")
    public String purchase_add(@ModelAttribute Purchase purchase,Model m){
        System.out.println("New Purchase started1");
        purchDao.save(purchase);

        return "redirect:/books/"+ purchase.getBookId() + "?allow=156";
    }

    @GetMapping("/withdrawals")
    public String all_withdrawals(Model m){
        m.addAttribute("withdrawals", withDao.getAllWithdrawals());

        return "all_withdrawals";
    }

    @GetMapping("/withdrawals/new")
    public String withdrawal_new(Model m){
        m.addAttribute("withdrawal", new Withdrawal());
        m.addAttribute("books",bkdao.getAllBooks());

        return "new_withdrawal";
    }

    @PostMapping("/withdrawals/new")
    public String withdrawal_add(@ModelAttribute Withdrawal withdrawal,Model m){
        System.out.println("New Withdrawal started1");
        Purchase purchase = purchDao.getPurchaseByBookId(withdrawal.getBookId());
        withdrawal.setBillNumber(purchase.getBillNumber());
        int id = withDao.save(withdrawal);
        withdrawal.setWithdrawalId(Integer.toString(id));

        return "redirect:/withdrawals";
    }

    @GetMapping("/withdrawals/delete/{id}")
    public String withdrawal_delete(@PathVariable String id, Model m){
        withDao.delete(id);

        return "redirect:/withdrawals";
    }

}
