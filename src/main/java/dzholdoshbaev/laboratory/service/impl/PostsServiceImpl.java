package dzholdoshbaev.laboratory.service.impl;

import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.repository.FollowersRepository;
import dzholdoshbaev.laboratory.repository.PostsRepository;
import dzholdoshbaev.laboratory.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final FollowersRepository followersRepository;
    @Override
    public List<Posts> findAllFollowingPosts(Users user) {

        List<Users> followingList = followersRepository.findAllByFollower(user);
        List<Posts> followingPost = new ArrayList<>();


        for (Users following : followingList) {
            List<Posts> postsByFollowing = postsRepository.findAllByAuthorId(following.getId());
            followingPost.addAll(postsByFollowing);
        }

        return followingPost;
    }

    @Override
    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }

}
