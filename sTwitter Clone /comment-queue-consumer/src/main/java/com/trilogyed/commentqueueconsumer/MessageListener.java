package com.trilogyed.commentqueueconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.commentqueueconsumer.util.feign.CommentFeignClient;
import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {


    @Autowired
    CommentFeignClient client;



    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Comment msg){

        //check to see the commentId is not 0, then call client to update comment,
        if(msg.getCommentId()!=0){
            client.updateComment(msg, msg.getCommentId());
        } else{
           // otherwise if it is 0 we are adding
            client.addComment(msg);
        }





    }

}
