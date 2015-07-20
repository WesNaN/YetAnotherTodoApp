<<<<<<< HEAD
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

=======
package mainmenu;

import Preferences.Preferences;
>>>>>>> origin/master
import com.sun.javafx.geom.Rectangle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
<<<<<<< HEAD
import javafx.util.Callback;
=======
import model.CalendarObjects.Calendar;
import model.CalendarObjects.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
>>>>>>> origin/master


public class TasksWindow {

	private static DatePicker startdatepicker, duedatepicker;
	static Stage taskswindow;
	static HBox mainhbox, priorityhbox;
	static VBox leftvbox, rightvbox;
	static TextField  txttitle, txtlocation, txtlabel, txtdue;
	static TextArea txtdescription;
	static Label lblfrom, lbldue, lblreminders, lblpriority;
	static FlowPane panereminders;
	static Button btnsave;

	public static void show()
	{
		Stage addtaskscenestage = new Stage();
		//Left side	
<<<<<<< HEAD
	txttitle = new TextField("Title");
	txtdescription = new TextArea("Description");
	txtlocation = new TextField("Location");
	txtlabel = new TextField("Label");
	lblpriority = new Label("Priority Level");
	ChoiceBox<String> cbpriority = new ChoiceBox<>();
	cbpriority.getItems().addAll("High", "Medium", "Low");
	cbpriority.setValue("Medium");
	btnsave = new Button("Save Task");
	leftvbox = new VBox();
	priorityhbox = new HBox(lblpriority, cbpriority);
	

	
	
	  
	  
	priorityhbox.setSpacing(20);
	leftvbox.getChildren().addAll(txttitle, txtdescription,txtlocation, txtlabel, priorityhbox);	
	leftvbox.setPadding(new Insets(20));
	leftvbox.setSpacing(20);
	
	
	//right side
	startdatepicker = new DatePicker();
	startdatepicker.setValue(LocalDate.now());
	
	
	lbldue = new Label("To");
	duedatepicker = new DatePicker();
	duedatepicker.setValue(LocalDate.now());

	
	
	//Disables days
	final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                            		startdatepicker.getValue())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                           
                    }
                        
                };
            }
        };
	
        duedatepicker.setDayCellFactory(dayCellFactory);
	
	/* GETS VALUE SELECTED FROM CALENDARS AND DO STUFF!
	startdatepicker.setOnAction(e-> {
		System.out.println(startdatepicker.getValue());
	});
	
	duedatepicker.setOnAction(e-> {
		System.out.println(duedatepicker.getValue());
	});
	*/
	
	  long p = ChronoUnit.DAYS.between(
			  startdatepicker.getValue(), duedatepicker.getValue()
      );
	 
	lblreminders = new Label("Reminders");
	panereminders = new FlowPane(); // Empty pane where the reminders will be added
	rightvbox = new VBox();
	
	
	rightvbox.getChildren().addAll(startdatepicker, lbldue, duedatepicker, lblreminders, panereminders, btnsave);
	rightvbox.setPadding(new Insets(20));
	rightvbox.setSpacing(20);
	//Main
	mainhbox = new HBox(leftvbox, rightvbox);
	
	btnsave.setOnAction(e-> {
		//Check if Inputs are correct
		//RUN CODE TO SAVE DATA TO DATABASE
		 System.out.println(p);
		addtaskscenestage.close();
	});
	
	Scene addtaskscene = new Scene(mainhbox, 500, 400);
	
	addtaskscenestage.initModality(Modality.APPLICATION_MODAL);
	addtaskscenestage.setResizable(false);
	addtaskscenestage.setScene(addtaskscene);
	addtaskscenestage.setTitle("Add Task");
	addtaskscenestage.initStyle(StageStyle.UTILITY);
	addtaskscenestage.showAndWait();
		
		
		
		
		
		
		
=======
		txttitle = new TextField("Title");
		txtdescription = new TextArea("Description");
		txtlocation = new TextField("Location");
		txtlabel = new TextField("Label");
		lblpriority = new Label("Priority Level");
		ChoiceBox<String> cbpriority = new ChoiceBox<>();
		cbpriority.getItems().addAll("High", "Medium", "Low");
		cbpriority.setValue("Medium");
		btnsave = new Button("Save Task");
		leftvbox = new VBox();
		priorityhbox = new HBox(lblpriority, cbpriority);


		priorityhbox.setSpacing(20);
		leftvbox.getChildren().addAll(txttitle, txtdescription, txtlocation, txtlabel, priorityhbox);
		leftvbox.setPadding(new Insets(20));
		leftvbox.setSpacing(20);


		//right side
		lbldue = new Label("Due");
		txtdue = new TextField("dd/mm/yyyy");
		lblreminders = new Label("Reminders");
		panereminders = new FlowPane(); // Empty pane where the reminders will be added
		rightvbox = new VBox();


		rightvbox.getChildren().addAll(lbldue, txtdue, lblreminders, panereminders, btnsave);
		rightvbox.setPadding(new Insets(20));
		rightvbox.setSpacing(20);
		//Main
		mainhbox = new HBox(leftvbox, rightvbox);

		btnsave.setOnAction(e -> {

			// Getting all data from fields
			String title = txttitle.getText();
			String description = txtdescription.getText();
			String location = txtlocation.getText();
			model.Label label = new model.Label(txtlabel.getText());
			List<LocalDateTime> reminders = null; 	// TODO Create reminders adding functionality in GUI
			LocalDateTime due = null; 				// TODO Create from String date converter
			int priority = -1;						// FIXME Should we just have 3 priorities (High, Medium, Low) ?
			int ownerId = -1; // for now we don't have this functionality

			Task task = new Task(title, description, location, label, reminders, due, priority, ownerId);
			System.out.println(task);


			addtaskscenestage.close();
		});

		Scene addtaskscene = new Scene(mainhbox, 500, 400);

		addtaskscenestage.initModality(Modality.APPLICATION_MODAL);
		addtaskscenestage.setResizable(false);
		addtaskscenestage.setScene(addtaskscene);
		addtaskscenestage.setTitle("Add Task");
		addtaskscenestage.initStyle(StageStyle.UTILITY);
		addtaskscenestage.showAndWait();

>>>>>>> origin/master
	}
}
