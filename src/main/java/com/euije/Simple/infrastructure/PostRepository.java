package com.euije.Simple.infrastructure;

import com.euije.Simple.domain.Post;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    // CREATE
    void save(Post post);

    // READ
    List<Post> findAll();
    Optional<Post> findById(int id);
    int findRecentId();
    int countByTagId(int tagId);


    // UPDATE
    void update(Post post, Post newPost);

    // DELETE
    void deleteById(int postId);
}
