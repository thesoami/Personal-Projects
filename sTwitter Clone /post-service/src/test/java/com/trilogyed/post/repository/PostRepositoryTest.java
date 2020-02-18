package com.trilogyed.post.repository;

import com.trilogyed.post.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository repo;
//    @Test
//    public void getPostsByUser() {
//
//    }

    @Test
    public void createTest() {
        repo.deleteAll();

        Post post = new Post();
//        post.setPostId(1);
        post.setPostDate(LocalDate.of(2019, 9, 25));
        post.setPosterName("Neo");
        post.setPost("There is no spoon");

        repo.save(post);
        List<Post> postList = repo.findAll();
        assertEquals(1,postList.size());
        //-----------------------------testing findById note you have to use .get()

        Optional<Post> post2 = repo.findById(post.getPostId());
        assertEquals(post2.get(),post);
    }

    //------------------------------------
    @Test
    public void updateTest(){
        repo.deleteAll();

        Post post = new Post();

        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Morpheus");
        post.setPost("I can only show you the path of the one");

        repo.save(post);

        post.setPostDate(LocalDate.of(2019,9,27));
        post.setPosterName("The Oracle");
        post.setPost("I can only show you the path of the one");
        repo.save(post);

        Optional<Post> posts = repo.findById(post.getPostId());

        Post p = null;
        if(posts.isPresent()==true)
            p=posts.get();

        assertEquals(post,p);

    }

    @Test
    public void deleteTest(){
        repo.deleteAll();

        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,15));
        post.setPosterName("Neo");
        post.setPost("I know kungfu");

        repo.save(post);

        Post post1 = new Post();
        post1.setPostDate(LocalDate.of(2019,9,16));
        post1.setPosterName("Morpheus");
        post1.setPost("Show me");

        repo.save(post1);

        repo.delete(post);

        List<Post> postList = repo.findAll();
        assertEquals(postList.size(),1);

    }
    @Test
    public void findPostByPoster() throws Exception {
        Post post = new Post();
        post.setPostDate(LocalDate.of(2019,9,15));
        post.setPosterName("Neo");
        post.setPost("I know kungfu");

//        postDao.createPost(post);
        post = repo.save(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,9,16));
        post.setPosterName("Morpheus");
        post.setPost("Show me");

//        postDao.createPost(post);
        post = repo.save(post);

        post = new Post();
        post.setPostDate(LocalDate.of(2019,9,26));
        post.setPosterName("Morpheus");
        post.setPost("I can only show you the path of the one");

        post = repo.save(post);

        List<Post> posts = repo.findByPosterName("Morpheus");
        assertEquals(2,posts.size());
        }
    }
