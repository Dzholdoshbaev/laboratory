package dzholdoshbaev.laboratory.service.impl;

import dzholdoshbaev.laboratory.model.Comments;
import dzholdoshbaev.laboratory.model.Posts;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.repository.CommentsRepository;
import dzholdoshbaev.laboratory.repository.PostsRepository;
import dzholdoshbaev.laboratory.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;


    @Override
    public boolean isAuthorPost(Users user, Long postId) {
       Posts posts =  postsRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        return posts.getAuthorId().equals(user);
    }

    @Override
    public List<Comments> gelAllPostComments(Long postId) {
        Posts posts =  postsRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        return commentsRepository.findAllByPost(posts);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Override
    public void createComment(Long postId, String comments,Users users) {
        Posts posts =  postsRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        Comments comments1 = new Comments();
        comments1.setPost(posts);
        comments1.setComment(comments);
        comments1.setCommentator(users);
        comments1.setCreatedAt(LocalDateTime.now());
        commentsRepository.save(comments1);
        posts.setComments(posts.getComments() + 1);
        postsRepository.save(posts);
    }
}
