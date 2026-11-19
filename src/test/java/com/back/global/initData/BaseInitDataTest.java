package com.back.global.initData;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class BaseInitDataTest {
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("샘플 글의 작성자")
    void t1() {
        assertThat(postService.findById(1).get().getAuthor().getUsername()).isEqualTo("user1");
        assertThat(postService.findById(2).get().getAuthor().getUsername()).isEqualTo("user2");
        assertThat(postService.findById(3).get().getAuthor().getUsername()).isEqualTo("user3");
    }

    @Test
    @DisplayName("샘플 댓글의 작성자")
    void t2() {
        Post post1 = postService.findById(1).get();
        Post post2 = postService.findById(2).get();

        assertThat(post1.findCommentById(1).get().getAuthor().getUsername()).isEqualTo("user1");
        assertThat(post1.findCommentById(2).get().getAuthor().getUsername()).isEqualTo("user2");
        assertThat(post1.findCommentById(3).get().getAuthor().getUsername()).isEqualTo("user3");
        assertThat(post2.findCommentById(4).get().getAuthor().getUsername()).isEqualTo("user1");
        assertThat(post2.findCommentById(5).get().getAuthor().getUsername()).isEqualTo("user2");
    }
}
