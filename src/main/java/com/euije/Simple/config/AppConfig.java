package com.euije.Simple.config;

import com.euije.Simple.infrastructure.PostJDBCRepository;
import com.euije.Simple.infrastructure.PostRepository;
import com.euije.Simple.infrastructure.TagJDBCRepository;
import com.euije.Simple.infrastructure.TagRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PostRepository postRepository() {
        return new PostJDBCRepository();
    }

    @Bean
    public TagRepository tagRepository() {
        return new TagJDBCRepository();
    }
}
