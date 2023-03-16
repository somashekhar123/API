package com.Demo.app.Service.ServiceImpl;

import com.Demo.app.DTO.PostDTO;
import com.Demo.app.DTO.PostResponse;
import com.Demo.app.Entity.Post;
import com.Demo.app.Exception.ResourceNotFoundException;
import com.Demo.app.Repository.PostRepository;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
  private ModelMapper mapper;




    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper)
    {
        this.postRepository = postRepository;
        this.mapper=mapper;
    }

    @Override
    public PostDTO savaValue(PostDTO postDTO) {
        Post posts = mapToEntity(postDTO);


        Post save = postRepository.save(posts);

        PostDTO postDTO1 = mapToDto(save);


        return postDTO1;

    }

        public Post mapToEntity(PostDTO postdto)
        {
            Post map = mapper.map(postdto, Post.class);
//             Post posts=new Post();
//            post.setTitle(post.getTitle());
//            post.setDescription(post.getDescription());
//            post.setContent(post.getContent());
//            return posts;

            return map; 
        }
    public PostDTO mapToDto(Post x)
    {
        PostDTO dto = mapper.map(x, PostDTO.class);
//        PostDTO dto = new PostDTO();
//        dto.setId(x.getId());
//        dto.setTitle(x.getTitle());
//        dto.setDescription(x.getDescription());
//        dto.setContent(x.getContent());
        return dto;
    }

    @Override
    public List<PostDTO> get() {

        List<Post> all = postRepository.findAll();
        List<PostDTO> collect = all.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDTO getId(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        PostDTO postDTO = mapToDto(post);
        return postDTO;
    }

    @Override
    public PostDTO update(long id, PostDTO postDTO) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post save = postRepository.save(post);
        PostDTO postDTO1 = mapToDto(save);
        return postDTO1;
    }

    @Override
    public void delete(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.deleteById(id);

    }

    @Override
    public PostResponse page(int pageNo, int pageSize,String sortBy) {
        PageRequest of = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> all = postRepository.findAll(of);
        List<Post> content = all.getContent();
        List<PostDTO> collect = content.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        PostResponse post=new PostResponse();
      post.setCollect(collect);
      post.setPageNo(all.getNumber());
      post.setPageSize(all.getSize());
      post.setTotalPages(all.getTotalPages());
      post.setTotalElements(all.getTotalElements());
      post.setLast(all.isLast());



        return post ;
    }

}
