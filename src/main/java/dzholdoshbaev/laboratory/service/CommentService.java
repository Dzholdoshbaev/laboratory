package dzholdoshbaev.laboratory.service;

import dzholdoshbaev.laboratory.model.Comments;
import dzholdoshbaev.laboratory.model.Users;

import java.util.List;

public interface CommentService {
    boolean isAuthorPost(Users user, Long postId);

    List<Comments> gelAllPostComments(Long postId);

    void deleteComment(Long commentId);

    void createComment(Long postId, String comments,Users users);
}
