package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Comment;
import com.service.rent.RentServiceServer.entity.dto.CommentDto;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import com.service.rent.RentServiceServer.repository.CommentRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ApartmentRepo apartmentRepo;

    public Comment addComment(Long apartmentId, Long accountId, String commentContent) {
        Comment comment = new Comment();

        Apartment apartment = apartmentRepo.getApartmentById(apartmentId);
        if(apartment == null) {
            throw new RuntimeException("Apartment not found");
        }

        Account account = accountRepo.getAccountById(accountId);
        if(account == null) {
            throw new RuntimeException("Apartment not found");
        }

        comment.setApartment(apartment);
        comment.setOwner(account);
        comment.setContent(commentContent);

        return commentRepo.save(comment);
    }

    public boolean deleteComment(Long id, CommentDto commentInfo) {
        Comment comment = commentRepo.getCommentById(id);

        if(comment == null) {
            throw new RuntimeException("Comment with id:" + id + " not found");
        }

        if(commentInfo != null && commentInfo.getOwner() != null
                && commentInfo.getOwner().getId().equals(comment.getOwner().getId())) {
            commentRepo.delete(comment);
        } else {
            throw  new RuntimeException("You don`t have permission to delete this comment");
        }
        return true;
    }
}
