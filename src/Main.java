
/* @Author : Nirajan Shrestha - 000934040 - July 6,2024*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import  javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This is the main method, which is the Quiz game
 *
 */
public class Main extends Application {

    // TODO: Instance Variables for View Components and Model
    QuizModal quiz;
    Label question, difficultyLevel, score, life, quizName, credit, correctAnswer, wrongAnswer;
    TextField enterAnswer;
    Button submitAnswer, restart, easy,medium,hard, startButton,startButtonShadow, redButton, greenButton, yellowButton;

    //Graphics elements variables
    Canvas canvas;
    Pane root;
    ImageView feedbackImage;

    // TODO: Private Event Handlers and Helper Methods
    //HELPER METHOD

    /**
     * This helper method initializes all components that are used in this GUI project and initializes them,adds them to root
     * and calls addStylesToComponent() method to add styles and event handlers
     * This method is called in start method.
     */
    private void componentHandler(){
        question =new Label("Choose a level \n [easy, medium, hard]");
        score = new Label("Score: " + quiz.getScore());
        life = new Label("Life: " + quiz.getChance());
        difficultyLevel = new Label("Difficulty Level ");
        correctAnswer = new Label("Correct!");
        wrongAnswer = new Label("Wrong answer!");

        //Name/logo of the quiz game
        quizName = new Label("<PROGRAMMING QUIZ>");
        quizName.getStyleClass().add("logo");

        //credit
        credit =new Label("@NIRAJAN SHRESTHA - 000934040");
        credit.getStyleClass().add("credit");

        //components for textField
        enterAnswer = new TextField("");

        //component for buttons
        submitAnswer = new Button("Submit");
        restart = new Button("Play Again");
        easy = new Button("Easy");
        medium = new Button("Medium");
        hard = new Button("Hard");
        redButton =new Button();
        yellowButton = new Button();
        greenButton = new Button();

        //start button for landingScreen and its shadow
        startButtonShadow = new Button();
        startButtonShadow.getStyleClass().add("buttonShadow");

        startButton = new Button("START");
        startButton.getStyleClass().add("customButton");

         //Add components to the root
        root.getChildren().addAll(
                canvas,credit ,quizName,startButtonShadow, startButton,
                question,score,life,difficultyLevel,
                enterAnswer,submitAnswer,restart,
                easy,medium,hard,
                redButton,yellowButton,greenButton,
                correctAnswer,wrongAnswer
        );
        root.getChildren().add(feedbackImage);
        addStylesToComponents();
    }

    /**
     * This helper method add styles to all the GUI components used and also adds event handlers for the buttons
     */
    private void addStylesToComponents(){
        //location for labels
        question.relocate(30,150);
        correctAnswer.relocate(30,285);
        wrongAnswer.relocate(30,285);
        difficultyLevel.relocate(15,65);
        score.relocate(330,230);
        life.relocate(330,250);
        quizName.relocate(23,60);
        credit.relocate(160, 480);

        //location for textField
        enterAnswer.relocate(30,240);

        //location for buttons
        submitAnswer.relocate(30, 320);
        redButton.relocate(155,60);
        yellowButton.relocate(180,60);
        greenButton.relocate(205,60);
        restart.relocate(30,320);
        startButton.relocate(180,250);
        startButtonShadow.relocate(175,245);

        //location for difficulty label buttons
        easy.relocate(265,100);
        medium.relocate(328,100);
        hard.relocate(410,100);

        //GUI built-in method applied to component
        startButton.setPrefSize(150,50);
        startButtonShadow.setPrefSize(150,50);
        question.setPrefWidth(230);
        question.setWrapText(true);
        enterAnswer.setPrefWidth(200);

        //Stylesheet for component - (using styles.css as stylesheet)
        restart.getStyleClass().addAll("custom-button", "darkGreen");
        easy.getStyleClass().addAll("custom-button", "green");
        medium.getStyleClass().addAll("custom-button", "yellow");
        hard.getStyleClass().addAll("custom-button", "red");
        submitAnswer.getStyleClass().addAll("custom-button","darkGreen");
        correctAnswer.getStyleClass().add("correct-text");
        wrongAnswer.getStyleClass().add("wrong-text");

        //Colors and fonts
        difficultyLevel.getStyleClass().add("normal-text");
        redButton.getStyleClass().add("circle-button-1");
        greenButton.getStyleClass().add("circle-button-2");
        yellowButton.getStyleClass().add("circle-button-3");
        question.getStyleClass().add("question-text");
        life.getStyleClass().add("normal-text");
        score.getStyleClass().add("normal-text");

        //component for images
        feedbackImage.setFitHeight(120);
        feedbackImage.setFitWidth(120);
        feedbackImage.setX(300);
        feedbackImage.setY(335);

        //setVisibility method is called and the boolean parameter is set to false foreach javafx nodes
        quiz.setVisibility(false,
                question,enterAnswer,submitAnswer,
                restart,easy,medium,hard,
                score,life,difficultyLevel,
                redButton,yellowButton,greenButton,
                wrongAnswer,correctAnswer,restart
        );
        submitAnswer.setDisable(true); //disables the submit button until the difficulty level is selected

        // Event handlers for buttons
        hard.setOnAction(this::loadHardQuestion);
        medium.setOnAction(this::loadMediumQuestion);
        easy.setOnAction(this::loadEasyQuestion);
        submitAnswer.setOnAction(this::submitTheAnswer);
        restart.setOnAction(this::restartGame);
        startButton.setOnAction(this::startTheGame);


    }

    /**
     * This helper method draws a stroke rectangle by calling a method from quiz Object
     */
    private void drawQuizScreenRectangle(){
        quiz.drawQuizScreenStrokeRectangle(canvas.getGraphicsContext2D());
    }

    //EVENT HANDLERS

    /**
     * This method starts the game, it hides the startScreen Components and displays the quiz screen components
     * and call the drawQuizScreenRectangle() method
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void startTheGame(javafx.event.ActionEvent e){
        //Hides the start screen and start button after its clicked -setVisibility method used
        quiz.setVisibility(false, startButton,startButtonShadow);

        //show all the quiz elements - setVisibility Method used
        quiz.setVisibility(true,
                question,enterAnswer,submitAnswer,
                easy,medium,hard,
                score,life,difficultyLevel,
                redButton,yellowButton,greenButton
        );
        //Disable buttons
        quiz.disableSetter(true,redButton,yellowButton,greenButton,submitAnswer);

        quizName.relocate(110,10);
        quizName.getStyleClass().add("small-logo");
        drawQuizScreenRectangle();

    }

    /**
     * This method loads all hard questions randomly by calling setDifficultyIndex(int level) method from quiz Object,
     * and uses disableSetter method to disable or enable the button accordingly
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void loadHardQuestion(javafx.event.ActionEvent e){
        quiz.setDifficultyIndex(2);
        question.setText(quiz.getQuestions());
        quiz.disableSetter(true,yellowButton,greenButton);
        quiz.disableSetter(false,redButton,submitAnswer,submitAnswer);
    }

    /**
     * This method loads all medium questions randomly by calling setDifficultyIndex(int level) method from quiz Object,
     * and uses disableSetter method to disable or enable the button accordingly
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void loadMediumQuestion(javafx.event.ActionEvent e){
        quiz.setDifficultyIndex(1);
        question.setText(quiz.getQuestions());
        quiz.disableSetter(true,redButton,greenButton);
        quiz.disableSetter(false,yellowButton,submitAnswer);

    }

    /**
     * This method loads all easy questions randomly by calling setDifficultyIndex(int level) method from quiz Object,
     * and uses disableSetter method to disable or enable the button accordingly
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void loadEasyQuestion(javafx.event.ActionEvent e){
        quiz.setDifficultyIndex(0);
        question.setText(quiz.getQuestions());
        quiz.disableSetter(true,redButton,yellowButton);
        quiz.disableSetter(false,greenButton,submitAnswer);
    }

    /**
     * This method check the answers by storing user input in a string and calling checkAnswer(String userAnswer) method form
     * quiz Object where the parameter is the stored user input, display correct or wrong label accordingly,
     * increments and decrements the score and life accordingly.
     * If life reaches 0 then the game stopsand restart button is shown in place of start to
     * restart the game
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void submitTheAnswer(javafx.event.ActionEvent e){
        String theAnswer = enterAnswer.getText();
      if (quiz.checkAnswer(theAnswer)){
          quiz.incrementScore();
          quiz.setVisibility(true,correctAnswer);
          quiz.setVisibility(false,wrongAnswer);
          score.setText("Score: " + quiz.getScore());
          Image correctImage = ImageDisplayManager.getRandomCorrectImage();
          if (correctImage != null) {
              feedbackImage.setImage(correctImage);
          }

      }else {
          quiz.decrementChance();
          quiz.setVisibility(false,correctAnswer);
          quiz.setVisibility(true,wrongAnswer);
          life.setText("Life: " + quiz.getChance());

          Image wrongImage = ImageDisplayManager.getRandomWrongImage();
          if (wrongImage != null) {
              feedbackImage.setImage(wrongImage);
          }

      }


      if(quiz.getChance()>0){
          question.setText(quiz.getQuestions());
          submitAnswer.setVisible(true);
          restart.setVisible(false);
          enterAnswer.clear();
      }else {
        question.setText("GAME OVER");
        submitAnswer.setVisible(false);
        restart.setVisible(true);
        enterAnswer.clear();
      }


    }

    /**
     * This method restarts the game, set the value of life and score to default and shows the submit button again
     * @param e a parameter for javafx.event.ActionEvent
     */
    private void restartGame(javafx.event.ActionEvent e){
        quiz.restartValue();
        life.setText("life: " + quiz.getChance());
        score.setText("Score: " + quiz.getScore());
        question.setText("Choose a level \n [easy, medium, hard]");
        quiz.setVisibility(false,wrongAnswer,correctAnswer,restart);
        quiz.setVisibility(true,submitAnswer);
        quiz.disableSetter(true, submitAnswer);
        feedbackImage.setImage(null);
    }




    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception .
     */
    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        Scene scene = new Scene(root, 500, 500); // Game Screen
        stage.setTitle("Programming quiz"); // set the window title here
        stage.setScene(scene);
        canvas = new Canvas(500,500); //the landing screen canvas
        GraphicsContext gc= canvas.getGraphicsContext2D();
        scene.getStylesheets().add(getClass().getResource("resource/styles.css").toExternalForm());

        //landingScreen size, position and background color
        gc.setFill(Color.web("#1E1E1E"));
        gc.fillRect(0,0,500,500);

        //gameScreen background color
         root.getStyleClass().add("defaultBackground");

        //QuizModal object initialization
        quiz = new QuizModal();
        feedbackImage = new ImageView();

        //load GUI components
        componentHandler();

        // Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
