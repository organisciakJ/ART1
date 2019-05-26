package project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageConvert {

    private int[][] imgBinaryTable;

    public ImageConvert(String path) {
        File file = new File(path);
        try {
            BufferedImage image = ImageIO.read(file);
            imgBinaryTable = convertImgToTable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Function for converting image to binary table
     * @param image
     * @return imgBinaryTable
     */
    private int[][] convertImgToTable(BufferedImage image) {
        int[][] imgBinaryTable = new int[image.getWidth()][image.getHeight()];
        int val;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                val = image.getRGB(j, i) < -2 ? 1:0;

                imgBinaryTable[j][i] = val;

            }
        }
        return imgBinaryTable;
    }


    /**
     * Function for converting 2D table to 1D table
     * @param arr
     * @return vector
     */
    public static int[] convert2Dto1D(int[][] arr) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
        }

        int[] vector = new int[list.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = list.get(i);
        }

        return vector;

    }

    public int[] getImgBinaryTable() { return convert2Dto1D(imgBinaryTable); }

}
