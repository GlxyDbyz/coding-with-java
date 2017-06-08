package org.dbyz.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
/**
 * 图片类型判断
 */
public class PicTypeUtil {
    public static String getImageType(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        int leng = fis.available();
        BufferedInputStream buff = new BufferedInputStream(fis);
        byte[] mapObj = new byte[leng];
        buff.read(mapObj, 0, leng);

        String type = "";
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis = null;
        try {
            bais = new ByteArrayInputStream(mapObj);
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator<ImageReader> itr = ImageIO.getImageReaders(mcis);
            while (itr.hasNext()) {
                ImageReader reader = itr.next();
                String imageName = reader.getClass().getSimpleName();
                if (imageName != null) {
                    if ("GIFImageReader".equals(imageName)) {
                        type = "gif";
                    } else if ("JPEGImageReader".equals(imageName)) {
                        type = "jpg";
                    } else if ("PNGImageReader".equals(imageName)) {
                        type = "png";
                    } else if ("BMPImageReader".equals(imageName)) {
                        type = "bmp";
                    } else {
                        type = "noPic";
                    }
                }
            }
        } catch (Exception e) {
            type = "noPic";
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException ioe) {
                }
            }
            if (mcis != null) {
                try {
                    mcis.close();
                } catch (IOException ioe) {
                }
            }
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException ioe) {
                }
            }

        }
        return type;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getImageType("/home/user/20170608101530.jpg")); //act PNG
    }
}
