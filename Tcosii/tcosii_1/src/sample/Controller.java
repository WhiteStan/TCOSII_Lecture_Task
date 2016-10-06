package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button buttonOpenImage;

    @FXML
    private Button buttonConstMult;

    @FXML
    private Button buttonImagesAdd;

    @FXML
    private Button buttonImagesExtr;

    @FXML
    private Button buttonSaw;
    @FXML
    private Button buttonOpenImage2;

    @FXML
    private Button buttonMask;

    @FXML
    private Button buttonNot;

    @FXML
    private Button buttonXor;

    @FXML
    private Button buttonConstAdd;

    @FXML
    private ImageView viewDefaultImage;

    @FXML
    private ImageView viewConvertedImage;

    @FXML
    private TextField textOpenImage;

    @FXML
    private TextField constValueMult;

    @FXML
    private TextField constA;

    final private FileChooser fileChooser = new FileChooser();

    private Image defaultImage;

    private Image secondImage;

    @FXML
    protected void initialize() {

        buttonOpenImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                fileChooser.setTitle("View Pictures");
                fileChooser.setInitialDirectory(
                        new File(System.getProperty("user.home"))
                );
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    textOpenImage.setText(file.getAbsolutePath());
                    defaultImage = new Image(file.toURI().toString());
                    viewDefaultImage.setImage(defaultImage);
                }
            }
        });

        buttonOpenImage2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                fileChooser.setTitle("View Pictures");
                fileChooser.setInitialDirectory(
                        new File(System.getProperty("user.home"))
                );
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    secondImage = new Image(file.toURI().toString());
                }
            }
        });

        buttonConstMult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int multConst=0;
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        multConst = Integer.parseInt(constValueMult.getText());
                        int r_new = r * multConst;
                        int g_new = g * multConst;
                        int b_new = b * multConst;
                        if(r_new >= 255){
                            r_new = 255;
                        }
                        else if(g_new >= 255){
                            g_new = 255;
                        }
                        else if(b_new >= 255){
                            b_new = 255;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });
        buttonConstAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int addConst=0;
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        addConst = Integer.parseInt(constValueMult.getText());
                        int r_new = r + addConst;
                        int g_new = g + addConst;
                        int b_new = b + addConst;
                        if(r_new >= 255){
                            r_new = 255;
                        }
                        else if(g_new >= 255){
                            g_new = 255;
                        }
                        else if(b_new >= 255){
                            b_new = 255;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });

        buttonImagesAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                PixelReader secondPixelReader = secondImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        int argb2 = secondPixelReader.getArgb(x, y);
                        int a2 = (0xff & (argb2 >> 24));
                        int r2 = (0xff & (argb2 >> 16));
                        int g2 = (0xff & (argb2 >> 8));
                        int b2 = (0xff & argb2); //получение цветов из изображения

                        float aConst = Float.parseFloat(constA.getText());
                        int r_new = (int) (aConst*r + (aConst-1)*r2);
                        int g_new = (int) (aConst*g + (aConst-1)*g2);
                        int b_new = (int) (aConst*b + (aConst-1)*b2);
                        if(r_new >= 255){
                            r_new = 255;
                        }
                        else if(g_new >= 255){
                            g_new = 255;
                        }
                        else if(b_new >= 255){
                            b_new = 255;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });

        buttonImagesAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                PixelReader secondPixelReader = secondImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        int argb2 = secondPixelReader.getArgb(x, y);
                        int a2 = (0xff & (argb2 >> 24));
                        int r2 = (0xff & (argb2 >> 16));
                        int g2 = (0xff & (argb2 >> 8));
                        int b2 = (0xff & argb2); //получение цветов из изображения

                        float aConst = Float.parseFloat(constA.getText());
                        int r_new = (int) (aConst*r + (aConst-1)*r2);
                        int g_new = (int) (aConst*g + (aConst-1)*g2);
                        int b_new = (int) (aConst*b + (aConst-1)*b2);
                        if(r_new >= 255){
                            r_new = 255;
                        }
                        else if(g_new >= 255){
                            g_new = 255;
                        }
                        else if(b_new >= 255){
                            b_new = 255;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });

        buttonImagesExtr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                PixelReader secondPixelReader = secondImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        int argb2 = secondPixelReader.getArgb(x, y);
                        int a2 = (0xff & (argb2 >> 24));
                        int r2 = (0xff & (argb2 >> 16));
                        int g2 = (0xff & (argb2 >> 8));
                        int b2 = (0xff & argb2); //получение цветов из изображения

                        float aConst = Float.parseFloat(constA.getText());
                        int r_new = (int) (aConst*r - (aConst-1)*r2);
                        int g_new = (int) (aConst*g - (aConst-1)*g2);
                        int b_new = (int) (aConst*b - (aConst-1)*b2);
                        if(r_new >= 255){
                            r_new = 255;
                        }
                        else if(g_new >= 255){
                            g_new = 255;
                        }
                        else if(b_new >= 255){
                            b_new = 255;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });

        buttonMask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                PixelReader secondPixelReader = secondImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        int argb2 = secondPixelReader.getArgb(x, y);
                        int a2 = (0xff & (argb2 >> 24));
                        int r2 = (0xff & (argb2 >> 16));
                        int g2 = (0xff & (argb2 >> 8));
                        int b2 = (0xff & argb2); //получение цветов из изображения
                        int r_new,g_new,b_new;
                        if(r2 == 0 && g2 == 0 && b2 == 0){
                            r_new = 0;
                            g_new = 0;
                            b_new = 0;
                        }
                        else {
                            r_new = r;
                            g_new = g;
                            b_new = b;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });

        buttonSaw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(viewDefaultImage.getImage(), null);
                PixelReader pixelReader = defaultImage.getPixelReader();
                PixelReader secondPixelReader = secondImage.getPixelReader();
                for (int y = 0; y < defaultImage.getHeight(); y++) {
                    for (int x = 0; x < defaultImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        int a = (0xff & (argb >> 24));
                        int r = (0xff & (argb >> 16));
                        int g = (0xff & (argb >> 8));
                        int b = (0xff & argb); //получение цветов из изображения

                        int argb2 = secondPixelReader.getArgb(x, y);
                        int a2 = (0xff & (argb2 >> 24));
                        int r2 = (0xff & (argb2 >> 16));
                        int g2 = (0xff & (argb2 >> 8));
                        int b2 = (0xff & argb2); //получение цветов из изображения
                        int r_new,g_new,b_new;
                        if(r2 == 0 && g2 == 0 && b2 == 0){
                            r_new = 0;
                            g_new = 0;
                            b_new = 0;
                        }
                        else {
                            r_new = r;
                            g_new = g;
                            b_new = b;
                        }

                        //запись полученного препарированного изображения в буфер
                        bImage.setRGB(x, y, a << 24 | r_new << 16 | g_new << 8 | b_new);

                    }
                }
                viewConvertedImage.setImage(SwingFXUtils.toFXImage(bImage, null));
            }
        });
    }

}
