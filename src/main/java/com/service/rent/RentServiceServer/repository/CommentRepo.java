package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    Comment getCommentById(Long id);
}
