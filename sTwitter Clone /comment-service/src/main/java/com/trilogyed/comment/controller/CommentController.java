package com.trilogyed.comment.controller;

import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"comments"})
public class CommentController {
    //we need to specify the caching which will take place here and the various endpoints

    //cacheput will store the createion of the cache for the commentId

    //cacheevict removes the cache when we update and delete, it gets sent to the queue after a update/and removed when we delete

    @Autowired
    CommentRepository commentRepository;

    @CachePut(key = "#result.getCommentId()")
    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public Comment addComment(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @RequestMapping(path = "/comments/byPosts/{postId}", method = RequestMethod.GET)
    public List<Comment> getCommentsByPostId(@PathVariable int postId){
        return  commentRepository.findByPostId(postId);
    }

    @Cacheable
    @RequestMapping(path = "/comments/{commentId}", method=RequestMethod.GET)
    public Comment getComment(@PathVariable int commentId){
        return commentRepository.findById(commentId).orElse(null);
    }

    @CacheEvict(key = "#comment.getCommentId()")
    @RequestMapping(path = "/comments/{commentId}", method = RequestMethod.PUT)
    public void updateComment(@RequestBody Comment comment,@PathVariable int commentId){
        if(comment.getCommentId()!=0 && commentId == comment.getCommentId()){
            commentRepository.save(comment);
        }else{
            throw new IllegalArgumentException("Comment id must match value in path");
        }
    }

    @CacheEvict
    @RequestMapping(path = "/comments/{commentId}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int commentId){
        commentRepository.deleteById(commentId);
    }
}


////    @Autowired
////    CommentServiceLayer service;
//
//    //========================      Create     ====================================
//    @RequestMapping(value = "/comment", method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Comment createComment(@RequestBody Comment comment) {
//        comment = dao.createComment(comment);
//        return comment;
//    }
//
//    //========================      GET(byId)     ====================================
//    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Comment getComment(@PathVariable int id) {
//
//        return dao.getComment(id);
//    }
//
//    //========================      GetAll(ByPostId)     ====================================
//    @RequestMapping(value = "/post/{post_id}", method = RequestMethod.GET)
//    public List<Comment> findCommentByPost(@PathVariable(name = "post_id") Integer postId){
//        return dao.getCommentByPostId(postId);
//    }
//
//
//
//
//    //========================      Get(All)     ====================================
//    @RequestMapping(value = "/comments", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Comment> getAllComments(){
//        return dao.getAllComments();
//    }
//
//    //========================      Update     ====================================
//    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Comment updateComment(@RequestBody Comment comment, @PathVariable int id) {
////        if (comment.getCommentId() == 0)
//////            comment.setCommentId(id);
//////        if (id != comment.getCommentId()) {
//////            throw new IllegalArgumentException("missing correct id number");
//////        }
//        dao.updateComment(comment);
//        //just to confirm that post has been updated
//        return dao.getComment(id);
//    }
//
//    //========================      Delete     ====================================
//    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteComment(@PathVariable int id) {
//        dao.deleteComment(id);
//    }
//    @RequestMapping(value = "/comment/post/{id}", method = RequestMethod.GET)
//    public List<Comment> getCommentsByPostId(@PathVariable(name = "id") int postId) {
//
//        return dao.getCommentByPostId(postId);
//    }
////
////    @RequestMapping(value = "/comment", method = RequestMethod.POST)
////    @ResponseStatus(value = HttpStatus.CREATED)
////    public CommentViewModel createComment(@RequestBody CommentViewModel comment) {
////        comment = service.createComment(comment);
////        return comment;
////    }
////
////    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
////    public CommentViewModel getComment(@PathVariable int id) {
////        CommentViewModel comment = service.getComment(id);
//////        if (comment.isPresent()) {
//////            return comment.get();
//////        }
//////        throw new CommentNotFoundException(id);
////        return comment;
////    }
////    @RequestMapping(value = "/comments", method = RequestMethod.GET)
////    @ResponseStatus(value = HttpStatus.OK)
////    public List<CommentViewModel> getAllComments(){
////        return service.getAll();
////    }
////
////    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
////    @ResponseStatus(value = HttpStatus.OK)
////    public Comment updateComment(@RequestBody Comment comment, @PathVariable int id) {
////        if (comment.getCommentId() == 0)
////            comment.setCommentId(id);
////        if (id != comment.getCommentId()) {
////            throw new IllegalArgumentException("missing correct id number");
////        }
////        dao.save(comment);
////        //just to confirm that post has been updated
////        return dao.getOne(id);
////    }
////
////    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
////    @ResponseStatus(value = HttpStatus.OK)
////    public void deleteComment(@PathVariable int id) {
////        service.deleteComment(id);
////    }
//}
