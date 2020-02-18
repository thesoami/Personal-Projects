package com.trilogyed.stwitter;

import com.trilogyed.stwitter.model.Comment;
import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.service.ServiceLayer;
import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.viewModel.PostViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private  CommentClient commentClient;
    private   PostClient postClient;


    /*
     private int id;
    private String type;
    private String name;
    private String owner;

     */

    private void commentMockSetUp() {

        commentClient = mock(CommentClient.class);

        Comment testObj1 = new Comment();
        testObj1.setCommentId(1);
        testObj1.setCommenterName("John Wick");
        testObj1.setCommentDate(LocalDate.of(2019, 9, 10));
        testObj1.setPostId(1);
        testObj1.setComment("Yea I am thinking I re-rack?");

        Comment noIdObj1 = new Comment();
        noIdObj1.setCommenterName("John Wick");
        noIdObj1.setCommentDate(LocalDate.of(2019, 9, 10));
        noIdObj1.setPostId(1);
        noIdObj1.setComment("Yea I am thinking I re-rack?");

        Comment testObj2 = new Comment();
        testObj2.setCommentId(2);
        testObj2.setCommenterName("Neo");
        testObj2.setCommentDate(LocalDate.of(2011, 5, 31));
        testObj2.setPostId(1);
        testObj2.setComment("I am the one?");

        Comment noIdObj2 = new Comment();
        noIdObj2.setCommenterName("Neo");
        noIdObj2.setCommentDate(LocalDate.of(2011, 5, 31));
        noIdObj2.setPostId(1);
        noIdObj2.setComment("I am the one?");

        Comment testObj3 = new Comment();
        testObj3.setCommentId(3);
        testObj3.setCommenterName("Swamz");
        testObj3.setCommentDate(LocalDate.of(2019, 8, 3));
        testObj3.setPostId(2);
        testObj3.setComment("Adriana Is smart and Sweet?");

        Comment noIdObj3 = new Comment();
        noIdObj3.setCommenterName("Swamz");
        noIdObj3.setCommentDate(LocalDate.of(2019, 8, 3));
        noIdObj3.setPostId(2);
        noIdObj3.setComment("Adriana Is smart and Sweet?");

        //List Tests
        List<Comment> KeanuComments = new ArrayList<>();
        KeanuComments.add(testObj1);
        KeanuComments.add(testObj2);

        List<Comment> swamzComment = new ArrayList<>();
        swamzComment.add(testObj3);


        //give obj1 when id 1
        doReturn(testObj1).when(commentClient).getComment(1);

        //Return both comments on postId1
        doReturn(KeanuComments).when(commentClient).getCommentsByPostId(1);

        //Return Comments on postId
        doReturn(swamzComment).when(commentClient).getCommentsByPostId(2);

        //donothing when commentId 1 is deleted
        doNothing().when(commentClient).deleteComment(1);



    }


    private void postMockSetUp() {

        postClient = mock(PostClient.class);

        Post mockObj1 = new Post();
        mockObj1.setPosterName("Iron Man");
        mockObj1.setPostDate(LocalDate.of(2019, 5, 30));
        mockObj1.setPost("I am Iron Man");


        Post obj1 = new Post();
        obj1.setPostId(1);
        obj1.setPosterName("Iron Man");
        obj1.setPostDate(LocalDate.of(2019, 5, 30));
        obj1.setPost("I am Iron Man");

        //--------------------------------------
        Post mockObj2 = new Post();
        mockObj2.setPosterName("Iron Man");
        mockObj2.setPostDate(LocalDate.of(2019, 4, 30));
        mockObj2.setPost("We're in the endgame now");


        Post obj2 = new Post();
        obj2.setPostId(2);
        obj2.setPosterName("Iron Man");
        obj2.setPostDate(LocalDate.of(2019, 4, 30));
        obj2.setPost("We're in the endgame now");

        //----------------------------------------

        Post mockObj3 = new Post();
        mockObj3.setPosterName("Spider Man");
        mockObj3.setPostDate(LocalDate.of(2019, 4, 20));
        mockObj3.setPost("I took caps shield");

        Post obj3 = new Post();
        obj3.setPostId(3);
        obj3.setPosterName("Spider Man");
        obj3.setPostDate(LocalDate.of(2019, 4, 20));
        obj3.setPost("I took caps shield");


        List<Post> IronManPosts = new ArrayList<>();
        IronManPosts.add(obj1);
        IronManPosts.add(obj2);

        List<Post> spiderManPosts = new ArrayList<>();
        spiderManPosts.add(obj3);

        //Feign Mocks for mock object 1
        doReturn(obj1).when(postClient).createPost(mockObj1);
        doReturn(obj1).when(postClient).getPost(1);

        //Feign Mocks for mock object 2
        doReturn(obj2).when(postClient).createPost(mockObj2);
        doReturn(obj2).when(postClient).getPost(2);

        //Feign Mocks for mock object 3
        doReturn(obj3).when(postClient).createPost(mockObj3);
        doReturn(obj3).when(postClient).getPost(3);

        //Feign Mocks for mock postLists Iron-Man and Spider-Man
        doReturn(IronManPosts).when(postClient).getPostsByPosterName("Iron Man");
        doReturn(spiderManPosts).when(postClient).getPostsByPosterName("SpiderMan");
    }


    @Before
    public void setUp() throws Exception{
        postMockSetUp();
        commentMockSetUp();
        serviceLayer = new ServiceLayer(postClient, commentClient);
    }



    @Test
    public void addGetPost(){
        Post testMock = new Post();
        testMock.setPosterName("Iron Man");
        testMock.setPostDate(LocalDate.of(2019, 5, 30));
        testMock.setPost("I am Iron Man");

        PostViewModel postViewModel = serviceLayer.addPost(testMock);

        PostViewModel getPvm =serviceLayer.getPost(postViewModel.getPostId());

        assertEquals(postViewModel, getPvm);

        assertEquals(postViewModel.getComments().size(), 2);


    }

    @Test
    public void findComment(){
        Comment testComment1 = new Comment();
        testComment1.setCommentId(1);
        testComment1.setCommenterName("John Wick");
        testComment1.setCommentDate(LocalDate.of(2019, 9, 10));
        testComment1.setPostId(1);
        testComment1.setComment("Yea I am thinking I re-rack?");
        assertEquals(serviceLayer.findComment(1), testComment1);
    }



}
