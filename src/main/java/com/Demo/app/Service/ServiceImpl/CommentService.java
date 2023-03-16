package com.Demo.app.Service.ServiceImpl;

import com.Demo.app.DTO.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveData(long postId, CommentDto commentDto);

    List<CommentDto> getComment(long postId);

    CommentDto update(long postId, long id, CommentDto commentDto);

    void delete( long id);
}
