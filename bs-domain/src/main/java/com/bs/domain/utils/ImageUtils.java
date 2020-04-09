package com.bs.domain.utils;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * Created by JMCM
 */
public class ImageUtils {

    public static BufferedImage cropImage(String imgPath, String coords, String separator) {
        String[] strCoords = coords.split(separator);
        return cropImage(
                imgPath,
                Integer.valueOf(strCoords[0]),
                Integer.valueOf(strCoords[1]),
                Integer.valueOf(strCoords[2]),
                Integer.valueOf(strCoords[3])
        );
    }

    public static BufferedImage cropImage(String imgPath, int x1, int y1, int x2, int y2) {
        BufferedImage imgOrigen = getImageBuffer(imgPath);

        //Check if the widh and height are valid
        if (x2 <=  0 || y2 <= 0)
            return imgOrigen;

        return imgOrigen.getSubimage(x1, y1, x2, y2);

    }


    public static byte[] getBytesFromBuffer(BufferedImage buffer, String extension) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buffer, extension, baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    private static BufferedImage getImageBuffer(String filename) {
        try {

            //InputStream in = (new ImageUtils()).getClass().getResourceAsStream(filename);
            //return ImageIO.read(in);
            return ImageIO.read(new File(filename));

        } catch (Exception e) {
            System.out.println("The image was not loaded.");
        }
        return null;
    }

    public static void writeFile(byte[] data, String path) throws FileNotFoundException, IOException {
        //convert array of bytes into file
        FileOutputStream fileOuputStream
                = new FileOutputStream(path);
        fileOuputStream.write(data);
        fileOuputStream.close();
    }
    
    public static void resizeImage(InputStream inputStream, int width, int height, File output) throws IOException {
        BufferedImage sourceImage = ImageIO.read(inputStream);
        Image thumbnail = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
                                                            thumbnail.getHeight(null),
                                                            BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedThumbnail.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.drawImage(thumbnail, 0, 0, Color.WHITE, null);
        g.dispose();
//        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
        ImageIO.write(bufferedThumbnail, "jpeg", output);
    }

    public static void scaleImage(InputStream inputStream, int newWidth, int newHeight,
                                  File output) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        // Make sure the aspect ratio is maintained, so the image is not distorted
        double thumbRatio = (double) newWidth / (double) newHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newWidth = (int) (newHeight * aspectRatio);
        }

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
        graphics2D.dispose();
        ImageIO.write(newImage, "JPG", output);
    }

}
