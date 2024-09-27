package dzholdoshbaev.laboratory.service;

import dzholdoshbaev.laboratory.model.Users;

public interface LikeService {
    void addLike(Users user, Long postId);
}
