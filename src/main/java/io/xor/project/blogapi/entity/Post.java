package io.xor.project.blogapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name="posts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title"})
        }
)
public class Post {

        public Post(String title, String content, String description) {
                this.id = null;
                this.title = title;
                this.content = content;
                this.description = description;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(nullable = false, name = "title")
        private String title;

        @Column(nullable= false, name="description")
        private String description;

        @Column(nullable = false, name="content")
        private String content;

}
