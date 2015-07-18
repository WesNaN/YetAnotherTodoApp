import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmationBox {
	static boolean buttonYesClicked = false;
	static Stage stage;
	
	
	
	public static boolean show(String message, String title, String button1txt, String button2txt){
		
		
		stage = new Stage();
		stage.setTitle(title);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setResizable(false);
		
		Label lbl = new Label(message);
	
		Button btnYes = new Button (button1txt);
		btnYes.setOnAction(e -> buttonYesClicked() );
		
		
		Button btnNo = new Button (button2txt);
		btnNo.setOnAction(e -> buttonNoClicked());
		
		
		
	
		HBox paneH = new HBox(20);
		paneH.getChildren().addAll(btnYes, btnNo);
		paneH.setAlignment(Pos.CENTER);
		
		VBox paneV = new VBox(20);
		paneV.getChildren().addAll(lbl, paneH);
		paneV.setAlignment(Pos.CENTER);
		
		
		Scene scene1 = new Scene(paneV, 250, 75);
		stage.setScene(scene1);
		
		stage.showAndWait();
		
		return buttonYesClicked;
		
		

		
		
		
	}

	private static void buttonYesClicked() {
		buttonYesClicked = true;
		stage.close();
		
	
		
	}
	
	private static void buttonNoClicked(){
		buttonYesClicked = false;
		stage.close();
		
	}
	
	
	
	
	
	
	
	
	
	
}
