package mainmenu;


// Lots of placeholder code until I figure out exactly how to do this.


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Window extends Application {

	public static void main(String[] args) { // Temporary main file???
		launch(args);

	}
	
	static int WIDTH = 800;
	static int HEIGHT = 640;
	boolean editingtask;
	Rectangle rct1, calendarplaceholder;
	ScrollPane maintasksscrollpane;
	HBox boxheader;
	VBox mainvbox, tasksvbox, leftsidevbox, rightsidevbox;
	TextField txttasks;
	Scene scenemain;
	Label lbltasksheader, lbltaskstitle;
	StackPane mainpane;
	Rectangle placeholder1, placeholder2;
	Pane maintaskspane;
	Font fontheader, fonttasktitle, fontbutton;
	Button btnaddtask;
	Label x [];
	@Override
	public void start(Stage primaryStage) {
		try {
			loadNodes();
			handleKeyboard();
			primaryStage.setScene(scenemain);
			primaryStage.setResizable(true);
			primaryStage.setTitle("YetAnotherTodoApp");
			primaryStage.setOnCloseRequest(e -> {
				if (ConfirmationBox.show("Are you sure you want to exit? ", "Exit", "Yes", "No")){
					System.exit(0);//this is necessary otherwise resources remain open after the app is closed
				}
				e.consume();// If consume is not used the task will close whenever the condition is true of false.
				
			}); 
			primaryStage.show();
			primaryStage.setFullScreen(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void loadNodes() {
		fontheader = new Font("Georgia", 35);
		fontbutton = new Font("Arial", 15);
		// left side

		placeholder1 = new Rectangle(300, 200);
		placeholder2 = new Rectangle(300, 400);
		placeholder2.setFill(Color.BLUE);
		
		btnaddtask = new Button("Add Task");
		btnaddtask.setFont(fontbutton);
		btnaddtask.setOnAction(e-> addTask());
		
		leftsidevbox = new VBox(btnaddtask, placeholder2);
		leftsidevbox.setPadding(new Insets(20));

		// Right Side
		tasksvbox = new VBox(0);
	
		// Top "Tasks" label
		lbltasksheader = new Label("Tasks");
		
		lbltasksheader.setFont(fontheader);
		lbltasksheader.setAlignment(Pos.BASELINE_CENTER);
		
		
		
		
		// Main tasks labels
		lbltaskstitle = new Label("This is a task title");
		// lbltasks.setMinHeight(100);
		fonttasktitle = new Font("Verdana", 20);
		

	
		txttasks = new TextField(lbltaskstitle.getText());
		txttasks.setFont(fonttasktitle);
		txttasks.setVisible(false);
		// txttasks.setWrapText(true); // disables horizontal scroll bar
		
		rightsidevbox = new VBox();
		rightsidevbox.getChildren().add(lbltasksheader); //Tasks header is added first to make sure its outside the scrollpane and on top

		x = new Label[100];
	
		
		
		for (int i = 1; i<x.length; i++){
			x[i] = new Label();
			x[i].setText("A very big text for a task to make sure it takes at least one line and goes to the end of the window "+i+"\t");
			x[i].setFont(fonttasktitle);
			x[i].setWrapText(true); // disables horizontal scroll bar
		
			x[i].setPadding(new Insets(20, 0, 20, 0)); 
			tasksvbox.getChildren().add(x[i]);
			x[i].setOnMouseClicked(e-> handleMouse(e));
			if (i%2 == 0){
				x[i].setStyle("-fx-border-color:black; -fx-background-color: bisque ;"); //CSS for background color
			}else{
				x[i].setStyle("-fx-border-color:black; -fx-background-color: beige;");  //CSS for background color
			}
			
		}
		
		
	    //tasksvbox contains all the tasks in the array
		
		
		
		maintasksscrollpane = new ScrollPane();
		maintasksscrollpane.setVmax(HEIGHT-100);
		maintasksscrollpane.setFitToWidth(true);
		maintasksscrollpane.setContent(tasksvbox);
		
		//maintasksscrollpane is a pane that allows vertical and horizontal scroll
		
		
		rightsidevbox.getChildren().add(maintasksscrollpane);
		
		
		// Main
		boxheader = new HBox(leftsidevbox, rightsidevbox);
		// mainvbox = new VBox(boxheader);

		mainpane = new StackPane(boxheader);
		scenemain = new Scene(mainpane, WIDTH, HEIGHT);
		
		
	
	}

	private void addTask(){
		TasksWindow.show();
		System.out.println("running addtask");
	}
	
	
	
	
	private void handleMouse(MouseEvent e) { // handles mouse inputs
		
		System.out.println(e.getSource());
		
		
			if (e.getClickCount() == 1) {
				// DO STUFF FOR 1 CLICK , show description etc
				
			} else if (e.getClickCount() == 2) { // Double Click
				
				lbltaskstitle.setVisible(false);
				txttasks.setVisible(true);
				editingtask = true;
			}
	}
	
	
	private void handleKeyboard(){

		scenemain.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ESCAPE:
				if (editingtask) {
					txttasks.setText(lbltaskstitle.getText());
					lbltaskstitle.setVisible(true);
					txttasks.setVisible(false);
					editingtask = false;
				}
				break;
			default:
				break;

			}
		});
		// Enter key press needs to be specified to the txtArea otherwise it
		// wont read for whatever reason. I can latter remove this and add a
		// save button instead
		txttasks.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ENTER:
				if (editingtask) {
					lbltaskstitle.setText(txttasks.getText());
					lbltaskstitle.setVisible(true);
					txttasks.setVisible(false);
					editingtask = false;
				}
				break;
			}

		});
	}

}
