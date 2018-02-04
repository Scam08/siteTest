package sample;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {
    private final double cameraModifier = 50.0;
    private final double cameraQuantity = 10.0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root,600,600);
        scene.setFill(Color.BLACK);
        //Чтобы видеть объект в 3Д надо добавить камеру
        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(50000.0);
        camera.setTranslateZ(-1000);

        Cylinder cylinder = new Cylinder(50,100);
        //Меняем его цвет
        PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE); // Цвет к которому будет приближаться
        blueMaterial.setSpecularColor(Color.BLUE); // Основной цвет
        cylinder.setMaterial(blueMaterial);

        // Чтобы было все видно, прокрутим камеру по оси Х
        cylinder.setRotationAxis(Rotate.X_AXIS);
        cylinder.setRotate(45);

        root.getChildren().addAll(cylinder);
        primaryStage.setTitle("Hello World");
        scene.setCamera(camera); // Добавили камеру на сцену

        // Node - объект на который мы класнули
        // мы его увеличимаем в 2 раза, если он уже увеличен, то уменьшаем
        scene.setOnMouseClicked(event -> {
            Node picked = event.getPickResult().getIntersectedNode();
            if (null!=picked){
                double scalar = 2;
                if (picked.getScaleX()>1)
                    scalar = 1;
                    picked.setScaleX(scalar);
                    picked.setScaleY(scalar);
                    picked.setScaleZ(scalar);
            }
        });

        scene.setOnKeyPressed(event -> {
            double change = cameraQuantity;
            if (event.isShiftDown()){
                change = cameraModifier;
            }
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.W){
                cylinder.setRotationAxis(Rotate.X_AXIS);
                cylinder.setRotate(cylinder.getRotate()+10);
                //camera.setTranslateZ(camera.getTranslateZ() + change);gd
            }
            if (keyCode == KeyCode.S){
                cylinder.setRotationAxis(Rotate.X_AXIS);
                cylinder.setRotate(cylinder.getRotate()-10);
                //camera.setTranslateZ(camera.getTranslateZ() - change);
            }
            if (keyCode == KeyCode.A){
                cylinder.setRotationAxis(Rotate.Z_AXIS);
                cylinder.setRotate(cylinder.getRotate()-10);
               // camera.setTranslateX(camera.getTranslateX() - change);
            }
            if (keyCode == KeyCode.D){
                cylinder.setRotationAxis(Rotate.Z_AXIS);
                cylinder.setRotate(cylinder.getRotate()+10);
                //camera.setTranslateX(camera.getTranslateX() + change);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
