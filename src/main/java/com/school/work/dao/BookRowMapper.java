package com.school.work.dao;

import com.school.work.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet rs, int row) throws SQLException {
        Book e = new Book();
        e.setBookId(rs.getString("BookId"));
        e.setTitle(rs.getString("Title"));
        e.setWriter(rs.getString("Writer"));
        e.setPublisher(rs.getString("Publisher"));
        e.setPublishYear(rs.getInt("PublishYear"));
        e.setEdition(rs.getInt("Edition"));
        e.setVolume(rs.getString("Volume"));
        e.setPages(rs.getInt("Pages"));
        e.setBookCategory(rs.getString("BookCategory"));
        e.setBookStatus(rs.getString("BookStatus"));
        e.setRoomNumber(rs.getString("RoomNumber"));
        e.setRackNumber(rs.getString("RackNumber"));
        e.setShelfNumber(rs.getString("ShelfNumber"));
        e.setSerialNumber(rs.getInt("SerialNumber"));
        e.setSubjectNumber(rs.getString("SubjectNumber"));
        e.setFrequency(rs.getInt("Frequency"));
        e.setLanguage(rs.getString("Language"));

        return e;
    }
}
