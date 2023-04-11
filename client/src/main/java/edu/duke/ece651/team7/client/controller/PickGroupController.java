package edu.duke.ece651.team7.client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import edu.duke.ece651.team7.client.model.UserSession;
import edu.duke.ece651.team7.shared.*;

public class PickGroupController implements Initializable {

    public static Scene getScene(RemoteGame server) throws IOException {
        URL xmlResource = PickGroupController.class.getResource("/fxml/pick-group-page.fxml");
        FXMLLoader loader = new FXMLLoader(xmlResource);
        loader.setController(new PickGroupController(server));
        return new Scene(loader.load(), 669, 457);
    }

    @FXML
    private ChoiceBox<String> selector;
    @FXML
    private Button confirmButton;

    private final RemoteGame server;
    private ObservableList<String> groupList;

    public PickGroupController(RemoteGame server) throws RemoteException {
        this.server = server;
        this.groupList = FXCollections
                .observableArrayList(server.getGameMap().getInitGroupOwners().stream().map(p -> p.getName()).toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selector.setItems(groupList);
    }

    @FXML
    public void clickOnConfirm(ActionEvent action) throws InterruptedException, IOException {
        String groupName = selector.getSelectionModel().getSelectedItem();
        String response = server.tryPickTerritoryGroupByName(UserSession.getInstance().getUsername(), groupName);
        if (response != null) {
            throw new IllegalArgumentException(response);
        }
        Scene newScene = PlaceUnitsController.getScene(server);
        Stage currStage = (Stage) confirmButton.getScene().getWindow();
        currStage.setScene(newScene);
        currStage.show();
    }

}
