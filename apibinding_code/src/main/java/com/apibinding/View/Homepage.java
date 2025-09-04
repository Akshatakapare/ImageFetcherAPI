package com.apibinding.View;

import com.apibinding.Controller.ApiController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Homepage extends Application {

    ApiController controller = new ApiController();

    @Override
    public void start(Stage myStage) {
        // 🔹 Title
        Text title = new Text("ImageFetcherAPI");
        title.setFont(Font.font("Arial", 36));
        title.setFill(Color.DARKBLUE);

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 20, 0));

        // 🔹 Input field
        TextField inputField = new TextField();
        inputField.setPromptText("Enter category or keyword...");
        inputField.setMaxWidth(300);
        inputField.setStyle("-fx-padding: 8; -fx-background-radius: 8; -fx-border-radius: 8;");

        // 🔹 Button
        Button fetchButton = new Button("Get Image");
        fetchButton.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14; " +
            "-fx-padding: 8 20; -fx-background-radius: 10; -fx-cursor: hand;"
        );

        fetchButton.setOnMouseEntered(e -> fetchButton.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14; " +
            "-fx-padding: 8 20; -fx-background-radius: 10; -fx-cursor: hand;"
        ));
        fetchButton.setOnMouseExited(e -> fetchButton.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14; " +
            "-fx-padding: 8 20; -fx-background-radius: 10; -fx-cursor: hand;"
        ));

        // 🔹 Image view
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500); // limit width
        imageView.setFitHeight(400); // limit height

        // 🔹 Button Action
        fetchButton.setOnAction(event -> {
            String query = inputField.getText().trim();
            if (!query.isEmpty()) {
                String imageUrl = controller.getImageUrl(query);
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    imageView.setImage(new Image(imageUrl, true));
                } else {
                    imageView.setImage(null);
                    title.setText("No image found for \"" + query + "\"");
                    title.setFill(Color.RED);
                }
            }
        });

        // 🔹 Layout
        VBox centerBox = new VBox(20, inputField, fetchButton, imageView);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(30));

        BorderPane root = new BorderPane();
        root.setTop(titleBox);
        root.setCenter(centerBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f9fafb, #dbeafe);");

        Scene scene = new Scene(root, 1020, 720);
        myStage.setScene(scene);
        myStage.setTitle("ImageFetcherAPI");
        myStage.show();
    }
}
