import GUI.LeftPane;
import GUI.RightPane;
import be.CadresManagement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    @FXML
    private AnchorPane leftAncho;
    @FXML
    private AnchorPane rightAncho;
    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splitPane.getItems().set(0, LeftPane.getInstance().getAnchorPane());
        splitPane.getItems().set(1, RightPane.getInstance().getAnchorPane());
    }
}
