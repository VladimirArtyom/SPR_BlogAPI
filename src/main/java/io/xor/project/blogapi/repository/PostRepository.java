package io.xor.project.blogapi.repository;

import io.xor.project.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Use this for shortcut, But you're here not because of this shit.
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
}
