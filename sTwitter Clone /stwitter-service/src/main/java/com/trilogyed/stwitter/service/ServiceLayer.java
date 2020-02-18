package com.trilogyed.stwitter.service;



import com.trilogyed.stwitter.model.Comment;
import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;

import com.trilogyed.stwitter.viewModel.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    //Need to specify the Topic Exchange Name and Routing Key
    public static final String TOPIC_EXCHANGE_NAME = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.#";


    //Dependency injection for feign comment client, feign post client and rabbitTemplate Below
    @Autowired
    private CommentClient commentClient;

    @Autowired
    private PostClient postClient;

    @Autowired
    private
    RabbitTemplate rabbitTemplate;
    //RABBID RABIT


    public ServiceLayer(){

    }

    public ServiceLayer(PostClient postClient, CommentClient commentRepository) {
        this.commentClient = commentRepository;
        this.postClient = postClient;

    }

    //Note for the endpoints I mimicked what a twitter service would employ
    //create a tweet,(might not have comments), get a tweet (find someones tweet), in theory there is no updating a tweet
    //and deletion of a tweet
    //also finding tweets by a specific user aka view tweets of said person

    //================================================================================================================
    //=========================================    Post Methods for Service    =======================================
    //================================================================================================================


    public PostViewModel addPost(Post post) {
        return buildPostViewModel(postClient.createPost(post));
    }

    public PostViewModel getPost(int postId) {
        Post post = postClient.getPost(postId);
        return buildPostViewModel(post);
    }

    public void updatePost(Post post) {
        postClient.updatePost(post, post.getPostId());
    }

    public void deletePost(int postId) {

        postClient.deletePost(postId);
    }

    public List<PostViewModel> getPostsByPoster(String poster) {
        List<Post> posts = postClient.getPostsByPosterName(poster);
        List<PostViewModel> userPVM = new ArrayList<>();

        posts.forEach(post -> {
            userPVM.add(buildPostViewModel(post));
        });
        //use lambda to stream through each post in the posts list
        //build the viewmodel

        return userPVM;
    }

    //================================================================================================================
    //=========================================    Comment Methods for Service     ===================================
    //================================================================================================================
    //delete a comment by comment id
    public void deleteComment(int commentId) {
        commentClient.deleteComment(commentId);
    }

    public void addComment(Comment comment) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, comment);
    }

    public void updateComment(Comment com) {
        //a check to ensure the same commenter is sending comments to the queue needs to be implemented
        //use .equals here for string comparison
        if (com.getCommentId() != 0 && com.getCommenterName()
                .equals(commentClient.getComment(com.getCommentId()).getCommenterName())) {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, com);
        } else { //otherwise throw an exception
            throw new IllegalArgumentException("Must be a valid comment, must also be the same commenter in question");
        }
    }

    public Comment findComment(int commentId) {
        return commentClient.getComment(commentId);
    }




//BuildView Model method including list of comments
    private PostViewModel buildPostViewModel(Post post) {
        PostViewModel pvm = new PostViewModel();
        pvm.setPost(post.getPost());
        pvm.setPostDate(post.getPostDate());
        pvm.setPosterName(post.getPosterName());
        //call the comment client to get comments by the post id (think we want to pull all comments for a given post,
        //we use the postId as a form of key to pull
        pvm.setComments(commentClient.getCommentsByPostId(post.getPostId()));
        pvm.setPostId(post.getPostId());
        return pvm;
    }


}