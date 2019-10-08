package cs1302.gallery;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import java.lang.Thread;
import java.io.Reader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
    

/** 
 * Represents an iTunes GalleryApp!
 * This GUI Application uses user search queries to result in a relating
 * display of gallery images based on the ITunes API.
 *
 * @author Stephanie Delgadillo
 */

public class GalleryApp extends Application {

   

    Button playPause; 
    Button update; //updates images
    TextField text; //our search test
    String [] allUrls; //array of all URLs from itunes api
    GridPane layout = new GridPane(); //temp layout
    String input; 
    int numResults=0;   
    int counter=0; //tracks images
    MenuBar menuBar;   
    ToolBar toolBar;
    VBox vbox;
    HBox hbox;
    Stage stage;
    Scene scene;
    ProgressBar progress; //begins at 0
    Label label; //copyright
    GridPane firstQuerySetup; //intial layout

  
   /** {@inheritdoc} */
    @Override
    
    public void start(Stage stage) {
	
        this.stage = stage;
        vbox = new VBox();
        hbox = new HBox();

	menuBar = createMenuBar(); //menubar created
        toolBar = createToolBar(); //toolbar created
        progress = new ProgressBar(0); //start
        firstQuerySetup = searchQuery("britney");   
        label  = new Label(" Image provided courtesy of ITunes");
	
        hbox.getChildren().addAll(progress, label); 
        vbox.getChildren().addAll(menuBar, toolBar, firstQuerySetup, hbox);

	scene = new Scene(vbox);
	
        stage.setMaxWidth(500);
        stage.setMaxHeight(800);
        stage.setTitle("ITunes Gallery App!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        this.stage.show();      
    }


    /**
     *  Box appears with this error if any without affecting code. More user friendly
     * 
     *  @param String print of error
     *  @return Alert alert in a box
     */
    private Alert errorAlert(String error) {  //User friendly error message
	
        Alert errorAlert = new Alert(AlertType.ERROR); 
        errorAlert.setHeaderText("Invalid");
        errorAlert.setContentText(error);
        errorAlert.showAndWait(); 
        return errorAlert;    
    }
  

    /**
     * Our helper stage for presenting my short about me display!
     *
     */
    
    public void stageTwo() {
	
        Stage stageTwo = new Stage();
        VBox vbox = new VBox();	
        File steph = new File("src/main/java/cs1302/gallery/ClearskinWhiteTeeth.jpg");
	
        Image pic = new Image(steph.toURI().toString()); //pic url to string
        ImageView imageView = new ImageView(pic); //presentable picture
	
        imageView.setFitWidth(250);
        imageView.setFitHeight(300);
        Label name = new Label("Name: Stephanie Delgadillo");
	
        name.setMaxWidth(Double.MAX_VALUE);
        name.setAlignment(Pos.CENTER); //centers
        name.setPadding(new Insets(20));
	
        Label email = new Label("Email: stephanie_71995@yahoo.com");
        email.setMaxWidth(Double.MAX_VALUE);
        email.setAlignment(Pos.CENTER); 
        email.setPadding(new Insets(20)); //margins

	
        Label ver = new Label("Version: ver. 14");
        ver.setMaxWidth(Double.MAX_VALUE);
        ver.setAlignment(Pos.CENTER); 
        ver.setPadding(new Insets(20));
	
        vbox.getChildren().addAll(imageView, name, email, ver);
	
        Scene scene = new Scene(vbox);
        stageTwo.setTitle("About Stephanie Delgadillo");
	
        stageTwo.setScene(scene);
        stageTwo.sizeToScene();
        stageTwo.setResizable(false); 
        stageTwo.initModality(Modality.APPLICATION_MODAL);
	
        stageTwo.show();  //displays this stage instead
        this.stage.hide(); //main screen is hidden
	
        stageTwo.setOnCloseRequest(event -> { 

		stageTwo.close();  //when exited
            this.stage.show();
        });
    } 
  
     /**
     *  This method creates all the components of the Menu bar.
     *
     * @return MenuBar returns a temp menu bar
     */

   
    private MenuBar createMenuBar() {	
	    
	  
	Menu dropDown = new Menu("File");
	MenuItem theExit = new MenuItem("Exit");

	theExit.setOnAction(event -> {
		Platform.exit();
		System.exit(0);	    
	    });

	MenuItem oneTheme = new MenuItem("Yellow"); //based on files     
    	  oneTheme.setOnAction(event -> {
            File yellow = new File("src/main/java/cs1302/gallery/Yellow.css"); //path
            this.scene.getStylesheets().add(yellow.toURI().toString());; 
        });
	  
        MenuItem twoTheme = new MenuItem("Red"); 
        twoTheme.setOnAction(event -> {
            File red = new File("src/main/java/cs1302/gallery/Red.css"); //
            this.scene.getStylesheets().add(red.toURI().toString());
        });
	
        MenuItem aboutItem = new MenuItem("About");  
	aboutItem.setOnAction(event -> stageTwo());

	dropDown.getItems().addAll(theExit); //add exit component in file tab

	Menu themes = new Menu("Theme"); //theme tab
	themes.getItems().addAll(oneTheme, twoTheme); //options

	Menu aboutMe = new Menu("About"); //aboutme tab
	aboutMe.getItems().addAll(aboutItem); //single 
		
	MenuBar menuBar2 = new MenuBar(); //temp menubar
	menuBar2.getMenus().addAll(dropDown, themes, aboutMe); //add all things

	return menuBar2;	    

    } 
  

    /**
     * This method creates a temp toolbar to be set equal to our instance toolbar. 
     * Here, our main components, of the play/pause button are given its actions,
     * the search query is set to a default text, and upload is given its actions.
     *
     */
   
    private ToolBar createToolBar() {  
        ToolBar temp = new ToolBar();
        
        this.playPause = new Button("Play"); 
        createPlayPause(playPause);
        playPause.setPrefWidth(100);
        
        Separator separate = new Separator(); 
        
        Label query = new Label("Search Query: "); 
        this.text = new TextField(); //text inside query box
        text.setPromptText("britney"); 
        text.setPrefWidth(175);
        
        this.update = new Button("Update"); 
        createUpdate(update); //gives update button its actions
        
        temp.getItems().addAll(playPause, separate, query, text, update); 
        
        return temp;     
    }

    /**
     * Method that gives the update button its attributes when clicked.
     *
     * @param Button update button that triggers the whole grid to change based on query.
     */
     private void createUpdate(Button refresh) {
	 
	 refresh.setOnAction(event -> { //not the play button but updates change the whole grid
            Thread holdOn = new Thread(() -> {      
		    Platform.runLater(() -> {        //slows it down to two seconds
                    if(this.text.getText().equals(null) || this.text.getText().equals("") ) {
			
                        errorAlert("Text must be present");        
                    }         
                    else {          
                        this.progress = new ProgressBar(0); 
			
                        this.firstQuerySetup = searchQuery(text.getText())
			    ;        
                        hbox.getChildren().setAll(progress, label);
                        vbox.getChildren().setAll(menuBar, toolBar, firstQuerySetup, hbox);            
                    }        
                });  
            });
	    
            holdOn.setDaemon(true);
            holdOn.start();               
        });        
    }


    /**
     * This method swaps photos on the grid individually at random when called.
     *
     */
    private void switchAround(){

       	int index;
        Random ran = new Random();

	
        if(numResults >= 20){   
            index = ran.nextInt(numResults - 20) + 20;        
        }      
        else{     
            index = ran.nextInt(numResults);      
        }
	
        String oneUrl = this.allUrls[index];
	
	updateImageGrid(oneUrl);

    }

    /**
     * This method gives an action when the user clicks on the play button.
     *
     * @param Button that is given the play and pause attributes
     */
    private void createPlayPause(Button theButton) {    
      
                 
	EventHandler<ActionEvent> handler = event -> switchAround();

	
        KeyFrame frame = new KeyFrame(Duration.seconds(2.0), handler); 
        Timeline tl = new Timeline();
	
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.getKeyFrames().add(frame);
        
        theButton.setOnAction(event -> {
		
            String text = theButton.getText().toString();

	    if(text == "Play") {
		
                theButton.setText("Pause");
                tl.play();      
            }
	    
            else if(text == "Pause") {  
                theButton.setText("Play");
                tl.pause();     
            }
	                   
        });     
    }
  
   
    
    /**
     * Method that outputs a whole new grid based on the query.
     *
     * @param String string that contains the default search from itunes api 
     */
    
    private GridPane searchQuery(String myURL) {
	
        this.counter = 0;  

	try {
	    
            breakDownUrl(myURL);  //splits urls into an array
            this.progress = new ProgressBar(0);

            for(int i = 0; i < 5; i++) { //rows
                for(int j = 0; j < 4; j++) { //cols
		    
                    Image image = new Image(this.allUrls[counter]); //goes thru url array
                    ImageView imageView = new ImageView(image);
		    
                    GridPane.setConstraints(imageView, i, j); 
		    
                    this.layout.setPrefSize(150, 150); 
                    this.layout.setPadding(new Insets(0));
		    
                    this.layout.getChildren().add(imageView);
                    this.progress.setProgress(this.counter * 0.05);//increases by multiplying

		    counter++;
                }        
            }                                                                                 
        }                               
        catch(Exception e) { //if exception is caught 
            errorAlert(e.toString()); 
            e.printStackTrace();   
        }
	
        return layout;                          
    }

    /**
     * This method pics a random coordinate to change the picture based on the url 
     * inserted.
     *
     * @param String the url input
     */
   
    private void updateImageGrid(String newer) {  

	Random ran = new Random(); 
        int row = ran.nextInt(3); 
        int col = ran.nextInt(4);
	
        Image image = new Image(newer); 
        ImageView viewImage = new ImageView(image);
	
        GridPane.setConstraints(viewImage, row, col); 
        this.layout.setPrefSize(150, 150); 
        this.layout.setPadding(new Insets(0));
	
        this.layout.getChildren().add(viewImage);                         
    }
  


    /**
     * This method creates the array of urls we are able to use to change the
     * grid when needed 
     * based on itunes search query.
     *
     * @param String the url from itunes
     */
    private void breakDownUrl(String bigUrl) {
        try {
	    
            String split="https://itunes.apple.com/search?term="+URLEncoder.encode(bigUrl,"UTF-8");     
            URL url = new URL(split); 
            InputStreamReader reader = new InputStreamReader(url.openStream());         
            JsonParser jp = new JsonParser(); 
            JsonElement je = jp.parse(reader); 
            JsonObject root = je.getAsJsonObject();
            JsonArray results = root.getAsJsonArray("results");
	    
            this.numResults = results.size(); 
            this.allUrls = new String[numResults];
	    
            if(numResults < 21) {         
                errorAlert("Size of the search is less than 21");           
            }      
            else {  
                for(int i = 0; i < numResults; i++) {                                           
                    JsonObject result = results.get(i).getAsJsonObject();                                
                    JsonElement artworkUrl100 = result.get("artworkUrl100");          
                    if (artworkUrl100 != null) {                                                                          
                        String urlString = artworkUrl100.getAsString();  
                        this.allUrls[i] = urlString;                                                         
                    }
                }                                                                  
            }
        }
        catch(Exception e) {
            errorAlert(e.toString());
            e.printStackTrace();
        }  
    }
  
   
  
  
    /**
     * Our main method where the application is launched.
     *
     */
    public static void main(String[] args) {    
        try {    
            Application.launch(args);      
        }       
        catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println(e);
            System.exit(1);             
        }         
    } 
} 
