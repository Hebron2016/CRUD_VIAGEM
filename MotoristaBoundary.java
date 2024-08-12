package LABBD.crudviagem;

import javafx.util.Callback;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;


public class MotoristaBoundary extends Application {
    private TextField txtCodigo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtNaturalidade = new TextField();

    private Label lblCodigo = new Label("Codigo:");
    private Label lblNome = new Label("Nome:");
    private Label lblNaturalidade = new Label("Naturalidade:");

    private TableView<Motorista> tableMotorista = new TableView<>();

    private MotoristaControl control = null;

    public void start(Stage stage){
        try {
            control = new MotoristaControl();
        } 
        catch (MotoristaException e) {
            alert("Erro ao iniciar o controle");
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane grid = new GridPane();

        Scene scn = new Scene(panePrincipal, 400,600);

        panePrincipal.setTop(grid);
        panePrincipal.setCenter(tableMotorista);

        grid.add(lblCodigo, 0, 0);
        grid.add(txtCodigo, 1, 0);
        grid.add(lblNome, 0, 1);
        grid.add(txtNome, 1, 1);
        grid.add(lblNaturalidade, 0, 2);
        grid.add(txtNaturalidade, 1, 2);

        Button btnAdicionar = new Button("Adcionar");
        btnAdicionar.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                try{
                    control.adicionar();
                }catch(Exception err){
                    alert("Erro ao adicionar");
                }

            }
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnAdicionar.addEventFilter(ActionEvent.ANY, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                try{
                    control.pesquisarPorNome();
                }catch(Exception err){
                    alert("Erro ao pesquisar por Nome");
                }
            }
        });
        grid.add(btnAdicionar,0,3);
        grid.add(btnPesquisar,1,3);

        createTableView();
        generateBindings();

        stage.setScene(scn);
        stage.show();

    }
    public void generateBindings() { 
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(txtCodigo.textProperty(), control.codigoProperty(), converter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtNaturalidade.textProperty(), control.naturalidadeProperty());

    }

    public void alert(String msg){
        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public void createTableView(){
        TableColumn<Motorista, Integer> col1 = new TableColumn("Codigo");
        col1.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        TableColumn<Motorista, String> col2 = new TableColumn("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<>("nome"));
        TableColumn<Motorista, String> col3 = new TableColumn("Naturalidade");
        col3.setCellValueFactory( new PropertyValueFactory<>("naturalidade"));

        TableColumn<Motorista, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Motorista, Void>, TableCell<Motorista, Void>> callback = new Callback<>() { 
            public TableCell<Motorista, Void> call(TableColumn<Motorista, Void> coluna) {
                TableCell<Motorista, Void> tc = new TableCell<>() {
                    final Button btnExcluir = new Button("Excluir");
                    {
                        btnExcluir.setOnAction(event -> { 
                            Motorista a1 = table.getItems().get(getIndex());
                            control.excluir(a1);
                        });
                    }
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnExcluir);
                        }
                    }
                };
                return tc;
            }
        };

        tableMotorista.setItems( control.getLista() );
        colAcoes.setCellFactory( callback );

        tableMotorista.getColumns().addAll(col1, col2, col3, colAcoes);
    }

        public static void main(String args[]) { 
            Application.launch(MotoristaBoundary.class, args);
        }
}





