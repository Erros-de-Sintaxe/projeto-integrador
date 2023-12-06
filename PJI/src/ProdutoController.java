
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProdutoController {

    @FXML
    private Button btnConfig;

    @FXML
    private Button btnConta;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnFaturamento;

    @FXML
    private Button btnPainel;

    @FXML
    private Label lbCadastroProd;

    @FXML
    private Label lbConfig;

    @FXML
    private Label lbConta;

    @FXML
    private Label lbFaturamento;

    @FXML
    private Label lbPainel;

    @FXML
    private Label lbemail;

    @FXML
    private Label lbnome;

    @FXML
    private TextField txCodigo;

    @FXML
    private TextField txDescricao;

    @FXML
    private TextField txEnviar;

    @FXML
    private TextField txNomeProd;

    @FXML
    private TextField txUpload;

    @FXML
    private TextField txValorProd;

    @FXML
    private TextField txValorRev;

    @FXML
    void saveProduto(ActionEvent event) {
        Produto produto = new Produto();
        produto.setCodigo(txCodigo.getText());
        produto.setDescricao(txDescricao.getText());
        produto.setNome(txNomeProd.getText());

        if (!txValorProd.getText().isEmpty()) {
            String valorProdText = txValorProd.getText().replace(",", ".");
            produto.setValorProduto(Double.parseDouble(valorProdText));
        } else {
            exibirAlerta("alerta", "Preencha todos os campos!");
            return;
        }

        if (!txValorRev.getText().isEmpty()) {
            String valorRevText = txValorRev.getText().replace(",", ".");
            produto.setValorRevenda(Double.parseDouble(valorRevText));
        } else {
            exibirAlerta("alerta", "Preencha todos os campos!");
            return;
        }
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.salvarProduto(produto);
        exibirAlerta("Aviso", "Produto salvo no banco de dados!");
    }

    @FXML
    void telaPainel(ActionEvent event) {
        abrirTelaPainel(event);
    }

    @FXML
    void telaConfig(ActionEvent event) {
        abrirTelaConfig(event);
    }

    @FXML
    void telaConta(ActionEvent event) {
        abrirTelaConta(event);
    }

    @FXML
    void telaFaturamento(ActionEvent event) {
        abrirTelafaturamento(event);
    }

    @FXML
    private Cliente clienteLogado;

    private ClienteDao clienteDao;

    public ProdutoController() {
        this.clienteDao = new ClienteDao();
    }

    private void abrirTelaConfig(ActionEvent event) {
        try {
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("config.fxml"));
            Parent root = fxmloader.load();
            Scene telaInicial = new Scene(root);

            Stage stage = (Stage) btnPainel.getScene().getWindow();
            stage.close();

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Configurações");
            novaJanela.setScene(telaInicial);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaConta(ActionEvent event) {
        try {
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("conta.fxml"));
            Parent root = fxmloader.load();
            Scene telaInicial = new Scene(root);

            Stage stage = (Stage) btnPainel.getScene().getWindow();
            stage.close();

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Conta");
            novaJanela.setScene(telaInicial);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelafaturamento(ActionEvent event) {
        try {
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("faturamento.fxml"));
            Parent root = fxmloader.load();
            Scene telaInicial = new Scene(root);

            Stage stage = (Stage) btnPainel.getScene().getWindow();
            stage.close();

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Tela de Faturamento");
            novaJanela.setScene(telaInicial);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaPainel(ActionEvent event) {
        try {
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("painel.fxml"));
            Parent root = fxmloader.load();
            Scene telaInicial = new Scene(root);

            Stage stage = (Stage) btnPainel.getScene().getWindow();
            stage.close();

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Painel");
            novaJanela.setScene(telaInicial);
            novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        String usuario = ApplicationContext.getUsuarioLogado();

        if (usuario != null) {
            lbnome.setText(usuario);

            // Agora pegamos o e-mail diretamente do ApplicationContext
            String email = ApplicationContext.getEmailLogado();
            lbemail.setText(email);
        }
    }

}
