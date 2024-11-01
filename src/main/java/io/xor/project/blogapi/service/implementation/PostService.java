package io.xor.project.blogapi.service.implementation;

import io.xor.project.blogapi.annot.OptionallyThrowsError;
import io.xor.project.blogapi.entity.Post;
import io.xor.project.blogapi.exception.http_exceptions.BadRequestException;
import io.xor.project.blogapi.exception.http_exceptions.InternalServerException;
import io.xor.project.blogapi.exception.http_exceptions.NotFoundException;
import io.xor.project.blogapi.exception.internal_exceptions.IE_NonUniqueResultException;
import io.xor.project.blogapi.exception.internal_exceptions.IE_PersistenceException;
import io.xor.project.blogapi.payload.PostDTO;
import io.xor.project.blogapi.repository.PostRepository;
import io.xor.project.blogapi.repository.implementation.PostDAO;
import io.xor.project.blogapi.service.IPostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public PostDTO updatePost(PostDTO newPost, String id ) {
        Post updatedPost = this.postRepository.updatePost(mapToPost(newPost), id);
        return mapToDTO(updatedPost);
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
    public void deletePost(PostDTO post) {
        this.postRepository.deletePost(mapToPost(post));
    }

    @Override
    public List<PostDTO> createPosts(List<PostDTO> posts) {
       List<Post> n_posts =  this.postRepository.createPosts(posts.stream().map(this::mapToPost).toList());
       return n_posts.stream().map(this::mapToDTO).toList();
    }

    @Override
    @OptionallyThrowsError
    public Optional<PostDTO> getPostByTitle(String title) {
        try {
            Post post = this.postRepository.getPostByTitle(title);
            return Optional.of(mapToDTO(post));
        }
         catch (EntityNotFoundException e) {
             throw new NotFoundException("Post", "title", title);
        } catch (NonUniqueResultException e) {
            throw new IE_NonUniqueResultException("Post", "Title is expected to return 1, but multiples returned", e.getMessage());
        } catch (PersistenceException e) {
            throw new IE_PersistenceException("Post",e.getMessage());
        }
    }

    @Override
    public Optional<PostDTO> getPostById(Long id) {
        return null;
    }

    @Override
    public Optional<List<PostDTO>> getPostsByTitle(String title) {
        try {
            return Optional.of(this.postRepository.getPostsByTitle(title).stream().map(this::mapToDTO).toList());
        }catch (EntityNotFoundException e) {
            throw new NotFoundException("Post", "title", title);
        } catch (PersistenceException e) {
            throw new IE_PersistenceException("Post", e.getMessage());
        }
    }

    @Override
    public void dev_exception() {
        throw new IE_PersistenceException("POST", "HERE ARE THE DETAILS");
        //throw new NotFoundException("Post", "title", "dev_exception");
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

        post.setId(postDTO.getId() != null ? postDTO.getId() : null);
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        return post;
    }

}
