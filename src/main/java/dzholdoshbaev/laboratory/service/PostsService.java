package dzholdoshbaev.laboratory.service;

import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;

import java.util.List;

public interface PostsService {
    List<Posts> findAllFollowingPosts(Users user);

    void deletePost(Long postId);
}
