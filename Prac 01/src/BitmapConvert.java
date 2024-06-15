

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BitmapConvert<T> {

    public Image<T> readBitmapToArray(String filename, PixelConverter<T> converter) {
        try {
            File file = new File(filename);
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();
            Image<T> pixelArray = new Image<T>(width, height);

            /* Explaining the Loop below
             * Here, 0xff is used as a bitmask. When combined with the bitwise AND (&) operator, it helps to extract the red, green, and blue components from the pixel value. 
             * 0xff masks out the least significant 8 bits of the value, effectively keeping only the lower 8 bits (the rightmost byte) of the integer. 
             * This is used because the RGB values are stored in the last 24 bits of the integer, where each color component is represented by 8 bits (1 byte).
             * By performing bitwise AND with 0xff, you ensure that only the lower 8 bits of the integer (corresponding to the blue component) are retained, and the higher bits are all set to 0. 
             * This operation effectively isolates the blue component from the rest of the integer
             */
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    int red = (pixel >> 16) & 0xff;	//there are 24 bits, the first 16 are red 
                    int green = (pixel >> 8) & 0xff;//the middle 8 are green
                    int blue = pixel & 0xff; //the first 8 are blue
                    T convertedPixel = converter.apply(red, green, blue);
                    pixelArray.getData()[y][x] = convertedPixel;
                }
            }
            return pixelArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeBitmapFromArray(String filename, Image<T> pixelArray) {
    	//TODO: Complete code [15 marks]
    	try {
            int width = pixelArray.getWidth();
            int height = pixelArray.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i< height; i++) {
                for (int j = 0; j < width; j++) {
                    image.setRGB(j, i, (Integer) pixelArray.getData()[i][j]);
                }
            }
            File file = new File(filename);
            ImageIO.write(image, "bmp", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
    }
       
}


