package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.*;
import static org.springframework.format.annotation.DateTimeFormat.ISO.*;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Optional<User> optionalUser=userRepository1.findById(userId);
        User user=optionalUser.get();

        Blog blog=new Blog(title, content,new Date());
        // Bidirectional Mapping
        blog.setUser(user);
        user.getBlogList().add(blog);

        userRepository1.save(user);
        return blog;

    }

    public void deleteBlog(int blogId) {
        //delete blog and corresponding images
        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            // Delete blog from the user list;

            blog.getImageList().clear();
            blogRepository1.save(blog);
        }
        blogRepository1.deleteById(blogId);

    }


}
