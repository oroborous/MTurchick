package springxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springxml.beans.CommentBean;

public class SpringApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CommentBean comment = context.getBean("commentBean", CommentBean.class);

        System.out.println(comment.getContent());
        System.out.println(comment.validateContent());

        context.close();
    }
}