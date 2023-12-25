package com.vtnq.smartmoney;

import com.vtnq.smartmoney.Dialog.DialogAlert;
import com.vtnq.smartmoney.models.Account;
import com.vtnq.smartmoney.util.DBConnection;
import com.vtnq.smartmoney.util.EncodePassword;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private MFXPasswordField PasswordField;

    @FXML
    private HBox SlideShowLogin;

    @FXML
    private MFXTextField UsernameField;

    @FXML
    void ChangeToRegister(ActionEvent event) {
        try {
            App.setRoot("Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String Hashcode;
    @FXML
    void CheckLogin(ActionEvent event) {
        if(UsernameField.getText().isEmpty()&&PasswordField.getText().isEmpty()){
            DialogAlert.DialogError("Username And Password Is Require!");
        }else if(UsernameField.getText().isEmpty()){
            DialogAlert.DialogError("Username Is Require!");
        }else if(PasswordField.getText().isEmpty()){
            DialogAlert.DialogError("Password Is Require!");
        }
        boolean IsFound1=false;
        boolean IsFound2=false;
        String query="select HashCode from account where Username=?";
        String LoginQuery1="select*from account where Username=? and Password=? and AccountType=0";
        String LoginQuery2="select*from account where Username=? and Password=? and AccountType=1";
        try {
            PreparedStatement preparedStatement=DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,UsernameField.getText());
            preparedStatement.executeQuery();
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                EncodePassword.hashCodeInDatabase=resultSet.getString("HashCode");
            }
            PreparedStatement preparedStatement1= DBConnection.getConnection().prepareStatement(LoginQuery1);
            preparedStatement1.setString(1,UsernameField.getText());
            preparedStatement1.setString(2,EncodePassword.MD5(PasswordField.getText()));
            PreparedStatement preparedStatement2=DBConnection.getConnection().prepareStatement(LoginQuery2);
            preparedStatement2.setString(1,UsernameField.getText());
            preparedStatement2.setString(2,EncodePassword.MD5(PasswordField.getText()));
            ResultSet resultSet1=preparedStatement1.executeQuery();
            ResultSet resultSet2=preparedStatement2.executeQuery();
            while (resultSet1.next()){
                IsFound1=true;
            }
            while (resultSet2.next()){
                IsFound2=true;
            }
            if(IsFound1){
                DialogAlert.DialogSuccess("Login Success");
                App.setRoot("Main");
            }else if(IsFound2){
                DialogAlert.DialogSuccess("Welcome Back Admin");
                App.setRoot("Admin");
            }else {
                DialogAlert.DialogError("Account Not Exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            DBConnection.DisConnection();
        }

    }

    private static final Image[] images={
            new Image("C:\\Users\\votan\\OneDrive\\Máy tính\\Smart Money\\src\\main\\resources\\com\\vtnq\\smartmoney\\asset\\image_login-1.png"),
            new Image("C:\\Users\\votan\\OneDrive\\Máy tính\\Smart Money\\src\\main\\resources\\com\\vtnq\\smartmoney\\asset\\image_login-2.png"),
            new Image("C:\\Users\\votan\\OneDrive\\Máy tính\\Smart Money\\src\\main\\resources\\com\\vtnq\\smartmoney\\asset\\image_login-3.png")
    };
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FnSlideShowLogin();
    }

    private void FnSlideShowLogin(){
        ImageView currentImageView=new ImageView(images[currentIndex]);
        SlideShowLogin.getChildren().add(currentImageView);
        Timeline timeline=new Timeline(
                new KeyFrame(
                        Duration.seconds(6),
                        actionEvent -> showNextImage()
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void showNextImage(){
        SlideShowLogin.getChildren().clear();
        currentIndex=(currentIndex+1)%images.length;
        ImageView nextImageView=new ImageView(images[currentIndex]);
        SlideShowLogin.getChildren().add(nextImageView);
    }
}
