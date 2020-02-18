package com.trilogyed.comment.repository;


import com.trilogyed.comment.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository repo;

    @Test
    public void createTest(){
        repo.deleteAll();

        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setCommentDate(LocalDate.of(2019, 9, 25));
        comment.setCommenterName("Swamz");
        comment.setComment("Adriana you're awesome");

        repo.save(comment);
        Optional<Comment> comment2 = repo.findById(comment.getCommentId());
        assertEquals(comment2.get(),comment);
    }

    @Test
    public void updateTest(){
        repo.deleteAll();

        Comment comment = new Comment();
        comment.setPostId(2);
        comment.setCommentDate(LocalDate.of(2019,9,26));
        comment.setCommenterName("Morpheus");
        comment.setComment("I can only show you the path of the one");
        repo.save(comment);

        comment.setPostId(2);
        comment.setCommentDate(LocalDate.of(2019,9,27));
        comment.setCommenterName("The Oracle");
        comment.setComment("I can only show you the path of the one");
        repo.save(comment);

        Optional<Comment> commentUpdate = repo.findById(comment.getCommentId());
        Comment cNull = null;

        if(commentUpdate.isPresent()==true)
            cNull = commentUpdate.get();

        assertEquals(comment,cNull);
    }

    @Test
    public void deleteGetAllTest(){
        repo.deleteAll();

        Comment comment = new Comment();
        comment.setPostId(2);
        comment.setCommentDate(LocalDate.of(2019,9,27));
        comment.setCommenterName("The Oracle");
        comment.setComment("I can only show you the path of the one");
        repo.save(comment);

        Comment comment2 = new Comment();
        comment2.setPostId(3);
        comment2.setCommentDate(LocalDate.of(2019,9,15));
        comment2.setCommenterName("Neo");
        comment2.setComment("I know kungfu");

        repo.save(comment2);

        Comment comment3 = new Comment();
        comment3.setPostId(4);
        comment3.setCommentDate(LocalDate.of(2019,9,16));
        comment3.setCommenterName("Morpheus");
        comment3.setComment("Show me");

        repo.save(comment3);

        //Delete method tested
        repo.delete(comment);


        //FindAll tested
        List<Comment> comments = repo.findAll();
        assertEquals(comments.size(),2);

    }


}
