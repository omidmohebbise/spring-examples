package com.omid.graphql.demographql;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDao {

    public List<Post> getRecentPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L,"title1"));
        posts.add(new Post(2L,"title2"));
        posts.add(new Post(3L,"title3"));
        //posts.add(new Post("4","title4"));
        return posts;
    }
}
