package com.zyj.disk.sys.tool;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.AllArgsConstructor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

/**
 * 二维码
 * 文献地址：https://blog.csdn.net/s735819795/article/details/80255400
 */
public final class QRImageTool {
    @AllArgsConstructor
    public enum FileFormat {
        PNG("PNG"),
        JPG("JPG");

        public final String format;
    }

    /**
     * 生成二维图
     *
     * @param body 内容
     * @param size 大小(默认 : 宽 = 高)
     */
    public BufferedImage generate(String body, int size) {
        Hashtable<EncodeHintType, Object> hashTable = new Hashtable<>();
        hashTable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashTable.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        hashTable.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(body, BarcodeFormat.QR_CODE, size, size, hashTable);
        } catch (WriterException e) {
            return null;
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0 : 0xFFFFFF);
            }
        }
        return image;
    }

    /**
     * 添加图片
     *
     * @param image      修改图片
     * @param imagePath  要添加的图片路径
     * @param isCompress 是否压缩添加图片
     */
    public void addImage(BufferedImage image, String imagePath, boolean isCompress) {
        BufferedImage targetImage;
        try {
            targetImage = ImageIO.read(new File(imagePath));
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        int width = targetImage.getWidth(), height = targetImage.getHeight();
        if (isCompress) {
            width = (int) (image.getWidth() / 3.3);
            height = (int) (image.getHeight() / 3.3);
            Image tempImage = targetImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tempBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = tempBufferedImage.getGraphics();
            graphics.drawImage(tempImage, 0, 0, null);
            graphics.dispose();
            targetImage = toBufferedImage(tempImage);
        }
        Graphics2D graphics2D = image.createGraphics();
        int x = (image.getWidth() - width) / 2;
        int y = (image.getHeight() - height) / 2;
        graphics2D.drawImage(targetImage, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, height, 6, 6);
        graphics2D.setStroke(new BasicStroke(3f));
        graphics2D.draw(shape);
        graphics2D.dispose();
    }

    public BufferedImage toBufferedImage(Image image) {
        image = new ImageIcon(image).getImage();
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
        BufferedImage bufferedImage = graphicsConfiguration.createCompatibleImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics graphics = bufferedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }

    public void output(String directoryPath, String fileName, BufferedImage image, FileFormat fileFormat) {
        File file = new File(directoryPath + "/" + fileName + "." + fileFormat.format.toLowerCase());
        try {
            ImageIO.write(image, fileFormat.format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}