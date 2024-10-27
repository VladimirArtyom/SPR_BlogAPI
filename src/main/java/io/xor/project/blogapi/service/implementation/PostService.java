package io.xor.project.blogapi.service.implementation;

import io.xor.project.blogapi.annot.OptionallyThrowsError;
import io.xor.project.blogapi.entity.Post;
import io.xor.project.blogapi.exception.ResourceNotFoundException;
import io.xor.project.blogapi.payload.PostDTO;
import io.xor.project.blogapi.repository.PostRepository;
import io.xor.project.blogapi.repository.implementation.PostDAO;
import io.xor.project.blogapi.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    private PostDAO postRepository;
    private PostRepository ps;
    PostService(PostDAO postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO post) {
        Post l_post = mapToPost(post);
        l_post = this.postRepository.createPost(l_post);
        return mapToDTO(l_post);
    }

    @Override
    public PostDTO updatePost(PostDTO post, Long id) {
        return new PostDTO();
    }

    @Override
    public PostDTO getPost(Long id) {
        return new PostDTO();
    }

    @Override
    public List<PostDTO> getPosts() {
        return this.postRepository.getPosts().stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<PostDTO> getPosts(int page, int size) {
        return this.postRepository.getPosts(page, size).stream().map(this::mapToDTO).toList();
    }

    @Override
    public void deletePostById(Long id) {
        return;
    }

    @Override
    public List<PostDTO> createPosts(List<PostDTO> posts) {
       List<Post> n_posts =  this.postRepository.createPosts(posts.stream().map(this::mapToPost).toList());
       return n_posts.stream().map(this::mapToDTO).toList();
    }

    @Override
    @OptionallyThrowsError
    public PostDTO getPostByTitle(String title) {
        Post post = this.postRepository.getPostByTitle(title);
        if (post == null) {
            throw new ResourceNotFoundException("Post", "id", post.getId());
        }

        return mapToDTO(post);
        //return new PostDTO();
    }

    @Override
    public PostDTO getPostById(Long id) {
        return null;
    }

    @Override
    public List<PostDTO> getPostsByTitle(String title) {
        return List.of();
    }

    private PostDTO mapToDTO(io.xor.project.blogapi.entity.Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId() != null ? post.getId(): null);
        postDTO.setDescription(post.getDescription());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        return postDTO;
    }


    private Post mapToPost(PostDTO postDTO) {
        Post post = new Post();

        post.setId(post.getId() != null ? post.getId() : null);
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        return post;
    }

}
