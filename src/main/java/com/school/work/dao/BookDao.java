package com.school.work.dao;


import com.school.work.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class BookDao {
    private JdbcTemplate template;
    private int id = 10003;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public Book getBookByBookId(String BookId){
        String sql = "select * from book where BookId=?";
        return template.queryForObject(sql,
                new Object[]{BookId},
                new BookRowMapper());
    }

    /*
        public String getTitleByBookId(String BookId){
        String sql = "select * from book where BookId=" + BookId;
        Book bk = template.queryForObject(sql,
                new Object[]{BookId},
                new BookRowMapper());

        return bk.getTitle();
    }
     */

    public void save(Book book){

        id=id+1;

        String sql = "insert into book (BookId,Title,Writer,Publisher,PublishYear,Edition,Volume,Pages,BookCategory,BookStatus,SubjectNumber,Language,Frequency," +
                "RoomNumber,RackNumber,ShelfNumber,SerialNumber) values ('" + book.getBookId() + "','" + book.getTitle() + "','" + book.getWriter() + "','" + book.getPublisher() +
                "'," +  book.getPublishYear() + "," + book.getEdition() + ",'" + book.getVolume() + "'," + book.getPages() + ",'" + book.getBookCategory() +
                "','" + book.getBookStatus() + "'," + book.getSubjectNumber() + ",'" + book.getLanguage() + "'," + book.getFrequency() +
                "," + book.getRoomNumber() + "," + book.getRackNumber() + "," + book.getShelfNumber() + "," + book.getSerialNumber() + ")";

        System.out.println("Book save: " + sql);

        template.update(sql);
    }

    public void update(Book book){

        String sql = "update book set BookId='" + book.getBookId() + "', Title='" + book.getTitle() + "', Writer='" + book.getWriter() +
                "', Publisher='" + book.getPublisher() + "', PublishYear=" + book.getPublishYear() + ", Edition='" + book.getEdition() +
                "', Volume=" + book.getVolume() + ", Pages=" + book.getPages() + ", BookCategory='" + book.getBookCategory() + "', BookStatus='" +
                book.getBookStatus() + "', SubjectNumber=" + book.getSubjectNumber() + ", Language='" + book.getLanguage() + "', Frequency=" +
                book.getFrequency() + ",RoomNumber=" + book.getRoomNumber() + ",RackNumber=" + book.getRackNumber() + ",ShelfNumber=" +
                book.getShelfNumber() + ",SerialNumber=" + book.getSerialNumber() + " where BookId = " + book.getBookId();

        System.out.println("Book update: " + sql);
        template.update(sql);
    }

    public List<Book> getAllBooks() {
        String sql="select * from book";
        return template.query(sql,
                new BookRowMapper());
    }

    public boolean isBookIdPresent(String bookId) {
        String sql = "select * from book where BookId="+bookId;
        List<Book> book = template.query(sql,new BookRowMapper());

        if(book.size()==0 || book.get(0).getBookCategory()=="Out of Stock")  return false;

        return true;
    }


    public void updateBookStatus(Book book, String status, int incr) {
        System.out.println("In BookDao updatebookStatus 1");

        int freq = incr + book.getFrequency();
        String sql = "update book set BookStatus='" + status + "' ,Frequency=" + freq + " where BookId=" + book.getBookId();
        System.out.println("In BookDao updatebookStatus 2");

        template.update(sql);
    }

    public List<Book> getAllBooksOfSubject(String id) {
        String sql = "select * from book where SubjectNumber='" + id + "'";

        return template.query(sql, new BookRowMapper());
    }
}
