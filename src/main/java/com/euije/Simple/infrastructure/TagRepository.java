package com.euije.Simple.infrastructure;

import com.euije.Simple.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository {
    // CREATE
    void save(Tag tag);
    void saveRelationsWithPostId(int postId, List<Integer> tagIds);

    // READ
    List<Tag> findAll();
    List<Tag> findAllByPostId(int postId);
    List<Integer> findRecentIdsByCount(int count);
    int countPostByTagId(int tagId);

    // UPDATE

    // DELETE
}
