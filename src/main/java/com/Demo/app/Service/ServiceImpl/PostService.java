package com.Demo.app.Service.ServiceImpl;

import com.Demo.app.DTO.PostDTO;
import com.Demo.app.DTO.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO savaValue(PostDTO postDTO);

    List<PostDTO> get();

    PostDTO getId(long id);

    PostDTO update(long id, PostDTO postDTO);

    void delete(long id);

    PostResponse page(int pageNo, int pageSize,String sortBy);
}
