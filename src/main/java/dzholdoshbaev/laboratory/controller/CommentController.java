package dzholdoshbaev.laboratory.controller;

import dzholdoshbaev.laboratory.model.Comments;
import dzholdoshbaev.laboratory.model.Users;
import dzholdoshbaev.laboratory.service.CommentService;
import dzholdoshbaev.laboratory.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final UsersService usersService;
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public String comment(@PathVariable Long postId, Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.getUserByEmail(username);
        boolean isAuthorPost = commentService.isAuthorPost(user, postId);
        List<Comments> postComments = commentService.gelAllPostComments(postId);
        model.addAttribute("postComments", postComments);
        model.addAttribute("isAuthorPost", isAuthorPost);
        model.addAttribute("postId", postId); // Добавляем postId в модель для использования в форме
        return "microgram/comment";
    }

    @PostMapping("/add/{postId}")
    public String addComment(@PathVariable Long postId, @RequestParam String comment, Principal principal) {
        String username = principal.getName();
        Users user = usersService.getUserByEmail(username);
        if (!comment.isBlank()) {
            commentService.createComment(postId, comment, user);
        }
        return "redirect:/comment/" + postId;
    }

    @GetMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/comment/" + postId;
    }
}
