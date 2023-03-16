package com.Demo.app.Service.ServiceImpl;

import com.Demo.app.DTO.CommentDto;
import com.Demo.app.Entity.Comment;
import com.Demo.app.Entity.Post;
import com.Demo.app.Exception.ResourceNotFoundException;
import com.Demo.app.Repository.CommentRepository;
import com.Demo.app.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper mapper;


    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.mapper=mapper;
    }

    @Override
    public CommentDto saveData(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id",postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);


        CommentDto commentDto1 = mapToDto(save);

        return commentDto1;
    }

    @Override
    public List<CommentDto> getComment(long postId) {

        List<Comment> byPostId = commentRepository.findByPostId(postId);

        List<CommentDto> collect = byPostId.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto update(long postId, long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        Comment save = commentRepository.save(comment);
        CommentDto commentDto1 = mapToDto(save);
        return commentDto1;
    }

    @Override
    public void delete( long id) {


        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
           commentRepository.deleteById(id);

    }

    public Comment mapToEntity(CommentDto dto)
    {
        Comment cm = mapper.map(dto, Comment.class);
//        Comment cm=new Comment();
//        cm.setName(dto.getName());
//        cm.setEmail(dto.getEmail());
//        cm.setBody(dto.getBody());
        return cm;
    }
    public CommentDto mapToDto(Comment c)
    {
        CommentDto dto = mapper.map(c, CommentDto.class);
//        CommentDto dto=new CommentDto();
//        dto.setId(c.getId());
//        dto.setName(c.getName());
//        dto.setBody(c.getBody());
//        dto.setEmail(c.getEmail());
        return  dto;
    }
}
