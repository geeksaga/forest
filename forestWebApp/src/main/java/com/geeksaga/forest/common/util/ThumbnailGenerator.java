package com.geeksaga.forest.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author geeksaga
 * @version 0.1
 */
public class ThumbnailGenerator
{
    protected static final Logger logger = LoggerFactory.getLogger(ThumbnailGenerator.class);

    public static void transform(String original, String thumbnail, int width, int height) throws IOException
    {
        Image image = null;

        String suffix = FilenameUtils.getExtension(original).toLowerCase();
        if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif"))
        {
            image = ImageIO.read(new File(original));
        }
        else
        {
            image = new ImageIcon(original).getImage();
        }

        double thumbRatio = (double) width / (double) height;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio)
        {
            height = (int) (width / imageRatio);
        }
        else
        {
            width = (int) (height * imageRatio);
        }

        if (imageWidth < width && imageHeight < height)
        {
            width = imageWidth;
            height = imageHeight;
        }
        else if (imageWidth < width)
        {
            width = imageWidth;
        }
        else if (imageHeight < height)
        {
            height = imageHeight;
        }

        int pixels[] = new int[width * height];
        Image imageTarget = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        PixelGrabber pg = new PixelGrabber(imageTarget, 0, 0, width, height, pixels, 0, width);

        try
        {
            pg.grabPixels();
        }
        catch (InterruptedException e)
        {
            throw new IOException(e.getMessage());
        }
        catch (Exception e)
        {
            logger.info(e.getMessage(), e);
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(bufferedImage, "jpg", new File(thumbnail));
    }
}