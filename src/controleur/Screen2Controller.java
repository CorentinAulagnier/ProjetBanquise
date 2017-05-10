package controleur;

import vue.ControlledScreen;
import vue.Interface;
import vue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Screen2Controller implements Initializable,ControlledScreen{

	ScreensController myController;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
			// ??
	}
	
	public void setScreenParent(ScreensController screenParent){
		myController = screenParent;
	}
	
	 @FXML
	    private void goToScreen1(ActionEvent event){
	       myController.setScreen(Interface.screen1ID);
	}
	@FXML
	    private void goToScreen3(ActionEvent event){
	       myController.setScreen(Interface.screen1ID);
	}
    
}
