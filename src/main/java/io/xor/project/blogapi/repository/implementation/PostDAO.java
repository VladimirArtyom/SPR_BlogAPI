package io.xor.project.blogapi.repository.implementation;


import io.xor.project.blogapi.entity.Post;
import io.xor.project.blogapi.repository.interfaces.IDAOPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PostDAO implements IDAOPost {

    private EntityManager entityManager;

    @Autowired
    public PostDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        String query = "SELECT p FROM Post p WHERE p.title = :title";
        TypedQuery<Post> typedQuery = entityManager.createQuery(query, Post.class);
        typedQuery.setParameter("title", "%" + title + "%");
        return typedQuery.getResultList();
    }

    @Override
    public Post getPostByTitle(String title) {
        String query = "SELECT p FROM Post p WHERE p.title = :title";
        TypedQuery<Post> typedQuery = entityManager.createQuery(query, Post.class);
        typedQuery.setParameter("title", title);
        return typedQuery.getSingleResult();
    }

    @Override
    public Post getPostById(Long id) {
        String query = "SELECT p FROM Post p WHERE p.id = :id";
        TypedQuery<Post> typedQuery = entityManager.createQuery(query, Post.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public Post updatePost(Post post, String uuid) {
        return null;
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    @Transactional
    public Post createPost(String title, String content, String description) {
        Post post = new Post(title, content, description);
        entityManager.persist(post);
        return post;
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        this.entityManager.persist(post);
        return post;
    }

    @Override
    @Transactional
    public List<Post> createPosts(List<Post> posts) {
        List<Post> newPosts = new ArrayList<>();
        for (Post post : posts) {
            this.createPost(post);
            newPosts.add(post);
        }
        return newPosts;
    }

    @Override
    public List<Post> getPosts() {
        String query = "SELECT p FROM Post p";
        TypedQuery<Post> tq = entityManager.createQuery(query, Post.class);
        return tq.getResultList();
    }

    @Override
    public List<Post> getPosts(int page, int size) {
        List<Post> posts = this.getPosts();
        int skip = (page - 1) * size;
        if (skip <= posts.size()) {
            int toIndex = Math.min(skip + size, posts.size());
            return posts.subList(skip, toIndex);
        }
        else {
            return posts.subList(0, posts.size());
        }
    }



}