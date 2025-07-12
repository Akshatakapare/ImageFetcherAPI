package com.apibinding.View;

import com.apibinding.Controller.ApiController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Homepage extends Application {

    ApiController controller= new ApiController();
    String imageUrl;

    @Override
    public void start(Stage myStage) throws Exception {
        
        Text tx = new Text("API Binding");
        tx.setStyle("-fx-font-size:35");

        TextField catTextField = new TextField();
        catTextField.setPromptText("Enter Category");
        catTextField.setStyle("-fx-max-width:300;");

        ImageView imageView= new ImageView();

        Button clickButton = new Button("Get Image");
        clickButton.setOnAction(event->{
            System.out.println(catTextField.getText()+"Image displayed");
            String imageUrl = controller.getImageUrl(catTextField.getText());
            imageView.setImage(new Image(imageUrl));

        });



        VBox vb = new VBox(30,tx,catTextField,clickButton,imageView);
        vb.setStyle("-fx-alignment:center;-fx-padding:50,10,10,10");
        Scene sc = new Scene(vb,1020,720);
        myStage.setScene(sc);
        myStage.show();

    }
    
}
