package dzholdoshbaev.laboratory.service.impl;

import dzholdoshbaev.laboratory.model.Likes;
import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.repository.LikesRepository;
import dzholdoshbaev.laboratory.repository.PostsRepository;
import dzholdoshbaev.laboratory.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
    private final PostsRepository postsRepository;
    private final LikesRepository likesRepository;

    @Override
    public void addLike(Users user, Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        Likes likesCheck = likesRepository.findByUsersAndPosts(user,posts);
        if (likesCheck == null) {
            Likes likes = new Likes();
            likes.setPost(posts);
            likes.setUsers(user);
            likes.setLikedAt(LocalDateTime.now());
            likesRepository.save(likes);
            posts.setLikes(posts.getLikes() + 1);
            postsRepository.save(posts);
        }
    }
}
