/* @Author : Nirajan Shrestha - 000934040 - July 6,2024*/
import javafx.scene.image.Image;
import java.io.InputStream;
import java.util.Random;

/**
 * This method uses Image object list and InputStream to load images  from source path, store them and extract random image from the stored list
 */
public class ImageDisplayManager {
    //variables
    private static Image[] correctImage;
    private static Image[] wrongImage;
    private static Random random = new Random();


    //Static to load the storeImage() method, since the method is private, the static will call the method first
    static {
        storeImage();
     }

     /**
     * This private method stores the image in the relative image List - (correctImage and wrongImage),
     * the size for both list is define and a for loop is used to store the images in relative list.
     * the loop runs till the length of correctImage list and calls loadImage(path) method where the path parameter is
     * the path for the image.
     */
    private  static void storeImage(){
        correctImage = new Image[5];
        wrongImage = new Image[5];

            for (int i=0; i<correctImage.length; i++){
                correctImage[i] = loadImage("/resource/images/success"+(i+1)+".png");
                wrongImage[i] = loadImage("/resource/images/wrong"+(i+1)+".png");
            }
    }

    /**
     * This private method loads an image from a specified path. It uses javafx Input stream builtin class which is a superclass used for reading and processing data from diffferent sources
     * the parameter is actually the input source that the InputStream will read.
     * Then a errorHandling process is initiated with if statement that, if the stream is null print out an error message and return null
     * else return a new Image abject with the stream as a parameter which will load the image
     * @param path the path for the image
     * @return new Image object if stream is not null else returns null
     */
    private static Image loadImage(String path) {
            InputStream stream = ImageDisplayManager.class.getResourceAsStream(path);
            if (stream == null) {
                System.err.println("Cannot find image: " + path);
                return null;
            }
            return new Image(stream);
    }

    /**
     * This method retrieves a random image from correctImage array , the bound is relative to the correctImage array
     * @return the retrieved random correctImage
     */
    public static Image getRandomCorrectImage(){
       return correctImage[random.nextInt(correctImage.length)];
    }

    /**
     * This method retrieves a random image from wrongImage array , the bound is relative to the wrongImage array
     * @return the retrieved random wrongImage
     */
    public static Image getRandomWrongImage(){
       return  wrongImage[random.nextInt(wrongImage.length)];
    }
}
