package com.Demo.app.Controller;

import com.Demo.app.DTO.PostDTO;
import com.Demo.app.DTO.PostResponse;
import com.Demo.app.Service.ServiceImpl.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<Object>saveData(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDTO dto=postService.savaValue(postDTO);

       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    @GetMapping
//    public List<PostDTO> getData()
//    {
//        List<PostDTO>dto=postService.get();
//        return dto;
//    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>getById(@PathVariable("id")long id)
    {
        PostDTO dto=postService.getId(id);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO>updateData(@PathVariable("id")long id,@RequestBody PostDTO postDTO)
    {
        PostDTO dto=postService.update(id,postDTO);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteData(@PathVariable("id")long id)
    {
        postService.delete(id);
        return new ResponseEntity<>("delete postData into table",HttpStatus.OK);
    }
 @GetMapping
 public PostResponse getDataByPage(@RequestParam(value="PageNo",defaultValue = "0")int pageNo, @RequestParam(value="PageSize",defaultValue = "10")int pageSize,@RequestParam(value="sortBy",defaultValue = "id")String sortBy)
 {
    PostResponse post=postService.page(pageNo,pageSize,sortBy);
     return post;
 }

}
