package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image(description,dimensions);

        // BIDIRECTIONAL MAPPING
        Optional<Blog> blogOptional=blogRepository2.findById(blogId);
        Blog blog=blogOptional.get();
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);

        return image;

    }

    public void deleteImage(Integer id){
        // delete this image data from the blog lis for a particular blog
        Optional<Image>optionalImage=imageRepository2.findById(id);
        if(optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setBlog(null);
            imageRepository2.save(image);
        }
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        // find the image by id provided
        Optional<Image> imageOptional=imageRepository2.findById(id);
        Image image=imageOptional.get();

        String array[]=image.getDimensions().split("X");
        String StrWid=array[1];
        int lenOfImage=Integer.parseInt(array[0]);


        int widOfImage=Integer.parseInt(array[1]);

        // decode screendimensions into value
        String[] arrayOfScreen =screenDimensions.split("X");

        int lenOfScreen=Integer.parseInt(arrayOfScreen[0]);
        int widOfScreen=Integer.parseInt(arrayOfScreen[1]);

        int noOfImages=(lenOfScreen/lenOfImage)*(widOfScreen/widOfImage);

        return  noOfImages;
    }
}