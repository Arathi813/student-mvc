package com.example.test.studentportal.controller;

import com.example.test.studentportal.model.Post;
import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.service.PostService;
import com.example.test.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostRestController {

    @Autowired
    PostService postService;

    @RequestMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getPostsList();
    }

//    @RequestMapping("/post/{id}")
//    public Post getOnePost(@PathVariable("id") Long id) {
//        return postService.getPostWithId(id);
//    }


    @RequestMapping(value = "/post/create" , method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post){
        return postService.addPost(post);
    }
}
