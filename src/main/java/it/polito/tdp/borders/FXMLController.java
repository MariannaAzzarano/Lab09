
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    

    @FXML
    private ComboBox<Country> comboStati;

    @FXML
    private Button btnStatiRagg;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	this.comboStati.getItems().clear();
    	Integer anno = Integer.parseInt(txtAnno.getText());
    	if(anno < 1816 || anno > 2016) {
    		txtResult.setText("L'anno deve essere compreso tra il 1816 e il 2016");
    	}
    	else {
    		model.creaGrafo(anno);
        	txtResult.appendText("Inseriti: " + model.getNUmeroVertici() + " Vertici e " + model.getNumeroArchi() +" Archi \n");  //per numero di vertici e archi
        	txtResult.appendText(""+model.getComponentiConnesse() + " componenti connesse \n");
        	List<Country> countries = model.getVertici(anno);
        	for(Country c : countries) {
        		txtResult.appendText(""+ c.getStateName() +" "+c.getnStatiConfinanti() +"\n");
        	}
    	}
    	this.comboStati.getItems().addAll(model.getCountries());
    }
    
    
//    @FXML
//    void doStatiRaggiungibili(ActionEvent event) {
//    	Country countryScelta = comboStati.getValue();
//    	Integer anno = Integer.parseInt(txtAnno.getText());
//    	List<Country> confinanti = model.getStatiConfinanti(countryScelta, anno);   
//
//    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboStati != null : "fx:id=\"comboStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStatiRagg != null : "fx:id=\"btnStatiRagg\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }
}
