package com.example.test.studentportal.service;
import com.example.test.studentportal.api.PostApi;
import com.example.test.studentportal.domain.DomainPost;
import com.example.test.studentportal.domain.DomainStudent;
import com.example.test.studentportal.model.Post;
import com.example.test.studentportal.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PostService {

    public List<Post> getPostsList(){
        List<Post> modelPosts = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DomainPost[]> response= restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts" , DomainPost[].class);
        List<DomainPost> domainPosts = Arrays.asList(response.getBody());
        for (DomainPost domainPost : domainPosts) {
            modelPosts.add(getPostFromDomainPost(domainPost));
        }
        return modelPosts;
    }

    public Post getPostWithId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DomainPost> response= restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/"+id , DomainPost.class);
        return getPostFromDomainPost(response.getBody());
    }

    public Post addPost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");
        HttpEntity<Post> request = new HttpEntity<>(post, headers);
        ResponseEntity<DomainPost> response= restTemplate.postForEntity("https://jsonplaceholder.typicode.com/posts", request , DomainPost.class);
        return getPostFromDomainPost(response.getBody());
    }

    private Post getPostFromDomainPost(DomainPost domainPost){
        Post postObject = new Post();
        postObject.setTitle(domainPost.getTitle());
        postObject.setBody(domainPost.getBody());
        postObject.setId(domainPost.getId());
        return postObject;
    }
}
