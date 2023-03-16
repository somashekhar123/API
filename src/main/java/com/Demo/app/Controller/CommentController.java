package com.Demo.app.Controller;

import com.Demo.app.DTO.CommentDto;
import com.Demo.app.Service.ServiceImpl.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable("postId") long postId, @RequestBody CommentDto
            commentDto) {

        CommentDto dto = commentService.saveData(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentDataByPostId(@PathVariable("postId")long postId)

    {
        List<CommentDto> dto=  commentService.getComment(postId);
        return dto;
    }
    @PutMapping("/posts/{postId}/comments/{id}")

    public ResponseEntity<CommentDto>updateComment(@PathVariable("postId")long postId,@PathVariable("id")long id,@RequestBody CommentDto commentDto)
    {
        CommentDto dto=commentService.update(postId,id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id")long id)
    {
        commentService.delete(id);
        return new ResponseEntity<>("delete comment data successfully",HttpStatus.OK);
    }
}