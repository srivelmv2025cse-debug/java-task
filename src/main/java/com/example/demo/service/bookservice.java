package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Author;
import com.example.demo.model.Member;
import com.example.demo.model.book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.bookrepository;

@Service 
public class bookservice {
    private final bookrepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public bookservice(bookrepository bookRepository, AuthorRepository authorRepository,
            MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }

    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }

    public book getBookById(long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Book not found: " + id));
    }

    public book createBook(book book) {
        setRelationships(book);
        return bookRepository.save(book);
    }

    public book updateBook(long id, book bookDetails) {
        book existingBook = getBookById(id);
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setMember(bookDetails.getMember());
        existingBook.setYear(bookDetails.getYear());
        setRelationships(existingBook);
        return bookRepository.save(existingBook);
    }

    public void deleteBook(long id) {
        bookRepository.delete(getBookById(id));
    }

    private void setRelationships(book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.getReferenceById(book.getAuthor().getId());
            book.setAuthor(author);
        }
        if (book.getMember() != null && book.getMember().getId() != null) {
            Member member = memberRepository.getReferenceById(book.getMember().getId());
            book.setMember(member);
        }
    }

    
}
