package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostClient {

    //Create--------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post);

    // FindByPosterName---------------------------------------------------------------------------------------------
    @RequestMapping(value = "/posts/poster/{posterName}", method = RequestMethod.GET)
    public  List<Post> getPostsByPosterName(@PathVariable String posterName);


    //Get-----------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable int id);

    //Update------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public void updatePost(@RequestBody Post post, @PathVariable int id);


    //Delete-------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public  void deletePost(@PathVariable int id);

}
















