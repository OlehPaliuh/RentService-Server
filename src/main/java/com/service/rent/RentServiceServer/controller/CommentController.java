package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Comment;
import com.service.rent.RentServiceServer.entity.dto.CommentDto;
import com.service.rent.RentServiceServer.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;

@RestController
@RequestMapping(path="/api/apartment/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "{apartmentId}/add")
    public @ResponseBody
    CommentDto addComment(@PathVariable Long apartmentId, @RequestBody CommentDto comment) {
        return modelMapper.map(commentService.addComment(apartmentId,comment.getOwner().getId(), comment.getContent()), CommentDto.class);
    }

    @PostMapping(path = "{id}/delete")
    public @ResponseBody
    boolean deleteComment(@PathVariable Long id, @RequestBody CommentDto comment) {
        return commentService.deleteComment(id, comment);
    }
}
