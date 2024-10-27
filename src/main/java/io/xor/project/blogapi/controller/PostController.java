package io.xor.project.blogapi.controller;


import io.xor.project.blogapi.payload.response.ApiResponse;
import io.xor.project.blogapi.payload.PostDTO;
import io.xor.project.blogapi.payload.response.ReadApiResponse;
import io.xor.project.blogapi.service.implementation.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/dev")
    public ResponseEntity<ApiResponse> devCreatePosts(@RequestBody List<PostDTO> postDTO) {
        this.postService.createPosts(postDTO);
        ApiResponse api = new ApiResponse<>(HttpStatus.CREATED.value(), "Success", 2 );
        return new ResponseEntity<>(api, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPost(@RequestBody PostDTO postDTO) {
        PostDTO dto  = this.postService.createPost(postDTO);
        ApiResponse api = new ApiResponse<>(HttpStatus.CREATED.value(), "Success", dto );
        return new ResponseEntity<>(api, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPosts(@RequestParam(required =true) int page,
                                                @RequestParam(required =true) int size) {
        List<PostDTO> posts = this.postService.getPosts(page, size);
        int total = this.total(posts);
        boolean isLast = this.isLast(page, size, posts);
        ReadApiResponse api = new ReadApiResponse(
                page, size, total, isLast,
                HttpStatus.OK.value(), "Success",
                posts);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    private <T> boolean isLast(int page, int size, List<T> posts) {
        int skip = (page - 1) * size;
        boolean isLast = skip + size > posts.size() + 1;
        return isLast;
    }

    private <T> int total(List<T> posts) {
        return posts.size() + 1;
    }

}
