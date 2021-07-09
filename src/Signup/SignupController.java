/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Signup;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import expo.GantiHalaman;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class SignupController implements Initializable {
    
    GantiHalaman h = new GantiHalaman();
    DataAkun d = new DataAkun();
    int status = 0;

    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Button buttonDaftar;
    @FXML
    private Hyperlink hlMasuk;
    @FXML
    private Label labelUser;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPass;
    @FXML
    private Label labelPass1;
    @FXML
    private PasswordField tfPass1;
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
        // TODO
    }    

    @FXML
    private void tfUser(ActionEvent event) {
    }

    @FXML
    private void tfEmail(ActionEvent event) {
    }

    @FXML
    private void tfPass(ActionEvent event) {
    }

    @FXML
    private void buttonDaftar(ActionEvent event) {
        status = 0;
        if(tfUser.getText().isEmpty()){
            labelUser.setText("Isi Nama User");
        }
        else{
            d.setUser(tfUser.getText());
            labelUser.setText("");
            status ++;
        }
        
        if(tfEmail.getText().isEmpty()){
            labelEmail.setText("Isi Email Anda");
        }
        else{
            d.setEmail(tfEmail.getText());
            labelEmail.setText("");
            status ++;
        }
        
        if(tfPass.getText().isEmpty()){
            labelPass1.setText("");
            labelPass.setText("Isi Password");
        }
        else if(tfPass.getText().length() < 8){
            labelPass1.setText("");
            labelPass.setText("Password minimal 8 digit");
        }
        else{
            labelPass1.setText("");
            if(tfPass1.getText().isEmpty()){
                labelPass.setText("Konfirmasi password anda");
            }
            else if(tfPass1.getText().equals(tfPass.getText())){
                d.setPass(tfPass.getText());
                labelPass.setText("");
                status ++;
            }
            else{
                labelPass.setText("Kata sandi tidak cocok. Coba lagi");
            }
        }
        
        if(cbPemilik.isSelected()){
            d.setStatus(true);
            status ++;
            labelAkun.setText("");
        }
        else if(cbPegawai.isSelected()){
            d.setStatus(false);
            status ++;
            labelAkun.setText("");
        }
        else{
            labelAkun.setText("Pilih salah satu");
        }
        if(status == 4){
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Akun berhasil di daftarkan");
            a.setTitle("Berhasil");
            a.setHeaderText("Silahkan klik OK");
            a.showAndWait();
            
            if(d.isStatus() == true){
                simpanDataPemilik();
            }
            else{
                simpanDataPegawai();
            }
            
            tfUser.setText("");
            tfEmail.setText("");
            tfPass.setText("");
            tfPass1.setText("");
            labelAkun.setText("");
            h.pindahHalaman(event, "/Login/Login.fxml");
        }
    }
    
    @FXML
    private void cbPemilik(ActionEvent event) {
        if(cbPegawai.isSelected()){
            cbPegawai.setSelected(false);
        }
    }

    @FXML
    private void cbPegawai(ActionEvent event) {
        if(cbPemilik.isSelected()){
            cbPemilik.setSelected(false);
        }
    }

    @FXML
    private void hlMasuk(ActionEvent event) {
        h.pindahHalaman(event, "/Login/Login.fxml");
    }
    
    void simpanDataPemilik(){
        XStream xstream = new XStream(new StaxDriver());
        String dataKu = xstream.toXML(d);
        FileOutputStream xmlKu = null;
        
        try{
            xmlKu = new FileOutputStream("xmlDataPemilik.xml");
            byte[] byteKu = dataKu.getBytes("UTF-8");
            xmlKu.write(byteKu);
            xmlKu.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
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
    
    void simpanDataPegawai(){
        XStream xstream = new XStream(new StaxDriver());
        String dataKu = xstream.toXML(d);
        FileOutputStream xmlKu = null;
        
        try{
            xmlKu = new FileOutputStream("xmlDataPegawai.xml");
            byte[] byteKu = dataKu.getBytes("UTF-8");
            xmlKu.write(byteKu);
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
