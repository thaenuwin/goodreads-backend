package com.goodreadsbackend.api.controller;

import com.goodreadsbackend.api.domain.BookQueryParam;
import com.goodreadsbackend.api.opr.BookCreation;
import com.goodreadsbackend.api.util.ResponseMessageUtil;
import com.goodreadsbackend.api.util.search.QueryBook;
import com.goodreadsbackend.api.util.search.comp.QueryParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Log4j2
public class BookController {

    @Autowired
    private BookCreation bookCreation;

    @Autowired
    private QueryBook queryBook;

    @PostMapping(path = "/create-book")
    public ResponseEntity<?> perform(@RequestHeader(value = "Authorization") String authorization,
                                     @RequestParam(value = "file") MultipartFile file, @ModelAttribute BookCreation.BookCreationCmd cmd) throws IOException {
        cmd.setBookImage(file.getBytes());
        BookCreation.CreateBookResponse statusCode= bookCreation.bookCreate(cmd);
        if(BookCreation.CreateBookResponse.SUCCESS.equals(statusCode)){
            return ResponseEntity.ok(ResponseMessageUtil.createSuccessResponse());
        }
        return ResponseEntity.badRequest().body(ResponseMessageUtil.createFailResponseMessage(statusCode.toString()));

    }
    @PostMapping(value = "/book-data")
    public ResponseEntity<Object> findPanelAssignmentData(
            @RequestHeader(value = "Authorization") String authorization, @RequestBody() QueryParam queryCmd) {
        log.debug("Query book data param : ", queryCmd);
        return ResponseEntity.ok(queryBook.query(queryCmd));
    }
}
