package com.omid.graphql.demographql;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class Query{

    private final PostDao postDao;

    public Post getRecentPost(Long id) {
        return postDao.getRecentPosts().get(0);
    }

    public List<Post> getRecentPosts() {
        return postDao.getRecentPosts();
    }



}
