/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Signup.DataAkun;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import expo.GantiHalaman;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO idepad GAMING
 */
public class LoginController implements Initializable {
    
    GantiHalaman h = new GantiHalaman();
    DataAkun d = new DataAkun();
    boolean next = false;

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Button buttonLogin;
    @FXML
    private Hyperlink hlDaftar;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPass;
    @FXML
    private CheckBox cbPemilik;
    @FXML
    private CheckBox cbPegawai;
    @FXML
    private Label labelAkun;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ambilDataPemilik();
        ambilDataPegawai();
    }    

    @FXML
    private void tfEmail(ActionEvent event) {
    }

    @FXML
    private void tfPass(ActionEvent event) {
    }

    @FXML
    private void buttonLogin(ActionEvent event) {
        if(next == true){
            labelAkun.setText("");
            }
        else{
            labelAkun.setText("Pilih salah satu");
        }
        
        if(tfEmail.getText().equals(d.getEmail()) && tfPass.getText().equals(d.getPass()) && next == true){
            h.pindahHalaman(event, "/Home/Home.fxml");
        }
        else if (tfEmail.getText().isEmpty() || tfPass.getText().isEmpty()){
            if (tfEmail.getText().isEmpty()){
                labelEmail.setText("Isi email anda");
            }
            else{
                labelEmail.setText("");
            }
            
            if (tfPass.getText().isEmpty()){
                labelPass.setText("Isi password anda");
            }
            else{
                labelPass.setText("");
            }
        }
        else if (next == true){
            labelEmail.setText("");
            labelPass.setText("");
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Isi ulang kembali");
            a.setContentText("Email dan Password tidak sesuai !");
            a.showAndWait();
        }
    }
    
    @FXML
    private void cbPemilik(ActionEvent event) {
        if(cbPegawai.isSelected()){
            cbPegawai.setSelected(false);
        }
        next = !next;
    }

    @FXML
    private void cbPegawai(ActionEvent event) {
        if(cbPemilik.isSelected()){
            cbPemilik.setSelected(false);
        }
        next = !next;
    }

    @FXML
    private void hlDaftar(ActionEvent event) {
        h.pindahHalaman(event, "/Signup/Signup.fxml");
    }
    
    void ambilDataPemilik(){
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream xmlKu = null;
        
        try{
            xmlKu = new FileInputStream("xmlDataPemilik.xml");
            int kodeKu;
            String pesanKu = "";
            
            while((kodeKu = xmlKu.read()) != -1){
                pesanKu += (char) kodeKu;
            }
            
            d = (DataAkun) xstream.fromXML(pesanKu);
            xmlKu.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    void ambilDataPegawai(){
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream xmlKu = null;
        
        try{
            xmlKu = new FileInputStream("xmlDataPegawai.xml");
            int kodeKu;
            String pesanKu = "";
            
            while((kodeKu = xmlKu.read()) != -1){
                pesanKu += (char) kodeKu;
            }
            
            d = (DataAkun) xstream.fromXML(pesanKu);
            xmlKu.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
