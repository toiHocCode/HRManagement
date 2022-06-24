package GUI;

import be.Cadres;
import be.CadresManagement;
import be.CadresType;
import be.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class RightAnchorPaneController implements Initializable{

    @FXML
    private TableView<Cadres> table;
    @FXML
    private TableColumn<Cadres, String> nameCol;
    @FXML
    private TableColumn<Cadres, Integer> idCol;
    @FXML
    private TableColumn<Cadres, String> dobCol;
    @FXML
    private TableColumn<Cadres, CadresType> typeCol;
    @FXML
    private TableColumn<Cadres, Integer> papersCol;
    @FXML
    private TableColumn<Cadres, Integer> teachCol;
    @FXML
    private TableColumn<Cadres, Integer> serveCol;

    private ObservableList<Cadres> listCadres;
    private ObservableList<Cadres> filter;

    public ObservableList<Cadres> getListCadres() {
        return listCadres;
    }

    public void setListCadres(ObservableList<Cadres> listCadres) {
        this.listCadres = listCadres;
    }

    public ObservableList<Cadres> getFilter() {
        return filter;
    }

    public void setFilter(ObservableList<Cadres> filter) {
        this.filter = filter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCol.setCellValueFactory(new PropertyValueFactory<Cadres, String>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<Cadres, Integer>("id"));
        dobCol.setCellValueFactory(new PropertyValueFactory<Cadres, String>("dob"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Cadres, CadresType>("type"));
        papersCol.setCellValueFactory(new PropertyValueFactory<Cadres, Integer>("papers"));
        teachCol.setCellValueFactory(new PropertyValueFactory<Cadres, Integer>("teach"));
        serveCol.setCellValueFactory(new PropertyValueFactory<Cadres, Integer>("serve"));
        listCadres = CadresManagement.getInstance().getListCadres();
        table.setItems(listCadres);

        table.setOnMouseClicked(event -> {
            Cadres cadresOnClicked = table.getSelectionModel().getSelectedItem();
            if (cadresOnClicked != null) {
                LeftAnchorPaneController leftAnchorPaneController = LeftPane.getInstance().getController();
                leftAnchorPaneController.viewCadresOn(cadresOnClicked);
            }
        });
    }

    public void addCadres(Cadres cadres) {
        if (!listCadres.contains(cadres)) {
            listCadres.add(cadres);
            LeftPane.getInstance().getController().clearTextFieldsAdd();
        }
    }

    @FXML
    public void update(ActionEvent e) {
        Cadres cadres = LeftPane.getInstance().getController().createCadres();
        for (Cadres child : listCadres) {
            if (child.equals(cadres)) {
                child = cadres;
                int index = listCadres.indexOf(child);
                listCadres.set(index, cadres);
                break;
            }
        }
    }

    @FXML
    public void delete(ActionEvent event) {
        Cadres cadresOnClick = table.getSelectionModel().getSelectedItem();
        listCadres.remove(cadresOnClick);
    }

    @FXML
    public void deleteAll(ActionEvent event) {
        listCadres.clear();
    }

    public void viewFilter() {
        table.setItems(getFilter());
    }

    public void viewAll() {
        table.setItems(getListCadres());
    }

    @FXML
    public void saveTable(ActionEvent event) {
        ObservableList<Cadres> listCadresOnTable = table.getItems();
        String content = "";
        for (Cadres cadres : listCadresOnTable) {
            content += cadres.getFormatValue() + "\n";
        }
        Utils.saveContentToNewFile(content);
    }

    @FXML
    public void reward(ActionEvent event) {
        table.setItems(getListReward());
    }

    public ObservableList<Cadres> getListReward() {
        List<Cadres> listReward = new ArrayList<>();
        for (Cadres cadres : table.getItems()) {
            if (cadres.isRewarded()) listReward.add(cadres);
        }
        return FXCollections.observableArrayList(listReward);
    }

    @FXML
    public void viewDefault() {
        viewAll();
    }

}
