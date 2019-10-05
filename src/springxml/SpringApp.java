package springxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springxml.beans.CommentBean;

public class SpringApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CommentBean comment = context.getBean("commentBean", CommentBean.class);

        System.out.println("CommentID: " + comment.getCommentId());
        System.out.println("Content: " + comment.getContent());
        System.out.println("HostTypeCode: " + comment.getHostTypeCode());
        System.out.println("ImageId: " + comment.getImageId());
        System.out.println("Content is valid(Ends with a period): " + comment.validateContent());

        context.close();
    }
}