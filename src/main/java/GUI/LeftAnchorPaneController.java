package GUI;

import be.Cadres;
import be.CadresManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeftAnchorPaneController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfdob;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfpapers;
    @FXML
    private TextField tfteach;
    @FXML
    private TextField tfserve;

    @FXML
    private CheckBox cbname;
    @FXML
    private CheckBox cbid;
    @FXML
    private CheckBox cbdob;
    @FXML
    private CheckBox cbtype;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tffilterByPapers;
    @FXML
    private TextField tffilterByTeach;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void addCadres(ActionEvent e) {
        RightAnchorPaneController rightAnchorPaneController = RightPane.getInstance().getController();
        Cadres cadres = createCadres();
        if (cadres != null) {
            rightAnchorPaneController.addCadres(cadres);
        }
    }

    public Cadres createCadres() {
        return CadresManagement.getInstance()
                .createCadres(tfname.getText(), tfid.getText(), tfdob.getText(), tftype.getText(), tfpapers.getText(), tfteach.getText(), tfserve.getText());
    }

    public void viewCadresOn(Cadres cadres) {
        tfname.setText(cadres.getName());
        tfid.setText(String.valueOf(cadres.getId()));
        tfdob.setText(cadres.getDob());
        tftype.setText(cadres.getType().toString());
        tfpapers.setText(String.valueOf(cadres.getPapers()));
        tfteach.setText(String.valueOf(cadres.getTeach()));
        tfserve.setText(String.valueOf(cadres.getServe()));
    }

    public void search(ActionEvent e) {
        CadresManagement cadresManagement = CadresManagement.getInstance();
        ObservableList<Cadres> list = cadresManagement.getListCadres();
        String content = tfsearch.getText();
        ObservableList<Cadres> listResult = null;
        if (!content.equals("")) {
            if (cbname.isSelected()) listResult = cadresManagement.searchByName(content, list);
            if (cbid.isSelected() && (listResult == null || listResult.isEmpty())) listResult = cadresManagement.searchByID(Integer.valueOf(content), list);
            if (cbdob.isSelected() && (listResult == null || listResult.isEmpty())) listResult = cadresManagement.searchByDOB(content, list);
            if (cbtype.isSelected() && (listResult == null || listResult.isEmpty())) listResult = cadresManagement.searchByType(content, list);
        }
        else {
            listResult = FXCollections.observableArrayList(new ArrayList<>());
        }

        RightAnchorPaneController controller = RightPane.getInstance().getController();
        controller.setFilter(listResult);
//        if (content.equals("")) {
//            controller.viewAll();
//        }
        controller.viewFilter();
        clearTextFieldsSearchAndFilter();
    }

    @FXML
    public void filterByPapers() {
        CadresManagement cadresManagement = CadresManagement.getInstance();
        ObservableList<Cadres> list = cadresManagement.getListCadres();
        String content = tffilterByPapers.getText();
        RightAnchorPaneController controller = RightPane.getInstance().getController();
        if (content.equals("")) {
            controller.viewAll();
            return;
        }
        ObservableList<Cadres> listResult = cadresManagement.filterByPapers(Integer.valueOf(content), list);

        controller.setFilter(listResult);
        controller.viewFilter();
        clearTextFieldsSearchAndFilter();
    }

    @FXML
    public void filterByTeach() {
        CadresManagement cadresManagement = CadresManagement.getInstance();
        ObservableList<Cadres> list = cadresManagement.getListCadres();
        String content = tffilterByPapers.getText();
        RightAnchorPaneController controller = RightPane.getInstance().getController();
        if (content.equals("")) {
            controller.viewAll();
        }
        ObservableList<Cadres> listResult = cadresManagement.filterByTeach(Integer.valueOf(content), list);

        controller.setFilter(listResult);
        controller.viewFilter();
        clearTextFieldsSearchAndFilter();
    }

    public void clearTextFieldsAdd() {
        tfname.clear();
        tfid.clear();
        tfdob.clear();
        tftype.clear();
        tfpapers.clear();
        tfteach.clear();
        tfserve.clear();
    }

    public void clearTextFieldsSearchAndFilter() {
        tfsearch.clear();
        tffilterByPapers.clear();
        tffilterByTeach.clear();
    }


}
