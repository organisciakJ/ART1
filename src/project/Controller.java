package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Controller {

    @FXML
    Button button, setButton;
    @FXML
    TableView table;
    @FXML
    TextField p;

    @FXML
    public void loadImg() {
        Network network = new Network();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Letters");
        List<File> list = fileChooser.showOpenMultipleDialog(button.getScene().getWindow());
        File[] files = new File[list.size()];

        for(int i = 0; i < list.size(); i++){
            files[i] = list.get(i);
//            System.out.println(files[i]);
        }

        String firstImg = files[0].getAbsolutePath();
        ImageConvert image = new ImageConvert(firstImg);

        if (network.getNeuronList().size() == 0) {
            network.addToNeuronList(image.getImgBinaryTable());
        }

        TableColumn nameColumn = new TableColumn("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn similarityColumn = new TableColumn("similarity");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("similarity"));
        TableColumn neuronColumn = new TableColumn("neuron");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("neuron"));


        for (File f : files){

            String filename = f.getAbsolutePath();
            ImageConvert img = new ImageConvert(filename);

            network.returnWinnerNeuron(img.getImgBinaryTable());

//            table.getItems().add(0, filename.substring(f.getAbsolutePath().lastIndexOf("\\")+1));
//            table.getItems().add(1, Float.toString(network.getSimilarity()));
//            table.getItems().add(2, Integer.toString(network.getNeuronId()+1));

        }

    }


}
