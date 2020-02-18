package com.trilogyed.stwitter.util.feign;
import com.trilogyed.stwitter.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentClient {

    //=======================================Delete comment by post id========================================================

    @RequestMapping(path = "/comments/byPosts/{postId}", method = RequestMethod.GET)
    List<Comment> getCommentsByPostId(@PathVariable int postId);


    //=======================================       Get Comment by Id       ========================================================
    @RequestMapping(path = "/comments/{commentId}", method=RequestMethod.GET)
    Comment getComment(@PathVariable int commentId);

    //=======================================           Delete by commentId        ========================================================

    @RequestMapping(path = "/comments/{commentId}", method = RequestMethod.DELETE)
    void deleteComment(@PathVariable int commentId);

}