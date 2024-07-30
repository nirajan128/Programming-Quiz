/* @Author : Nirajan Shrestha - 000934040 - July 6,2024*/
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 * This class is a QuizModal class which contain two data in 3*3 array (questions and answer)
 * After a constructor is created the questions and answer array and loaded
 * then random questions are generated based on difficulty level, the answer are checked using the difficulty level index and a parameter which would be a user answer
 * the score and chance are increment or decremented accordingly.
 * Finally, there are helper method to set visibility and disable for GUI component in view and method to draw a stroke rectangle
 * also used in view.
 */
public class QuizModal {
    //variables
    private int score;
    private int currentQuestionIndex;
    private String[][] questions;
    private String[][] answers;
    private int difficultyIndex;
    private int chance;
    private final Random rand;

    /**
     * Constructor for quiz modal
     */
    public QuizModal() {
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.difficultyIndex = 0;
        this.chance =3;
        this.rand = new Random();
        loadQuestionsAndAnswers(); //This method is called to get the questions and answers
    }

    /**
     * This method stores 3 level of question in question and answer String array
     * each array is a 3*3 type array and contain 3 question/answers in each of them
     *
     */
    private void loadQuestionsAndAnswers() {
        questions = new String[][]{
                {
                        "What does HTML stands for",
                        "Which language is used for styling web pages?",
                        "Which programming language is known for its use in developing Android apps?",
                        "What is the file extension for JavaScript files?",
                        "Which HTML element is used for the largest heading?",
                        "What does CSS stand for?"
                },
                {
                        "What is the output of the following Python code: `print(2 ** 3)`?",
                        "In JavaScript, which method is used to parse a JSON string into a JavaScript object?",
                        "Which data structure uses LIFO (Last In, First Out) principle?",
                        "What is the value of pi to two decimal places?",
                        "What is the base language of the web?",
                        "Which HTML attribute is used to define inline styles?"
                },
                {
                        "What is the time complexity of binary search?",
                        "In C++, what is the purpose of the `virtual` keyword in a base class method?",
                        "Explain what a closure is in JavaScript.",
                        "What is the main language used to create interactive web elements?",
                        "Which SQL command is used to delete data?",
                        "What does HTTP stand for?"
                }
        };

        answers = new String[][]{
                {
                        "HyperText Markup Language",
                        "CSS",
                        "Java",
                        "js",
                        "h1",
                        "CSS"
                },
                {
                        "8",
                        "JSON.parse()",
                        "Stack",
                        "3.14",
                        "HTML",
                        "style"
                },
                {
                        "O(log n)",
                        "Override",
                        "Function",
                        "JavaScript",
                        "DELETE",
                        "Hypertext"
                }
        };
    }

    /**
     * Setter for difficulty Index
     * @param level an integer value that determines level of difficulty
     */
    public void setDifficultyIndex(int level) {
       this.difficultyIndex = level;
    }

    /**
     * Getter for questions
     * generates random question from question array using Random class of java, where its parameter value is questions arrays length and store
     * in integer called currentQuestionIndex.The difficulty index is used to determine the index of the question array and currentQuestionIndex
     * gets random index from the difficulty index which returns random question from the difficultyIndex.
     * @return question String from question array
     */
    public String getQuestions() {
        currentQuestionIndex = rand.nextInt(6);
       return questions[difficultyIndex][currentQuestionIndex];
    }

    /**
     * This method checks answers, since answer array is also stored in same settings as question array,
     * the question index and answer index are equal, based on this the method uses .equalIgnoreCase method
     * and has the userAnswer as the parameter and compares it with answer array Index (which is equal to current question Index)
     * id these two are equal the method returns true else false
     * @param userAnswer a String for user answer
     * @return boolean if condition met
     */
    public boolean checkAnswer(String userAnswer){
        return answers[difficultyIndex][currentQuestionIndex].equalsIgnoreCase(userAnswer);
    }

    /**
     * Getter for score
     * @return value of score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for Chance
     * @return value of chance
     */
    public int getChance() {
        return chance;
    }

    /**
     * Increments the score by 1
     */
    public void incrementScore(){
        this.score++;
    }

    /**
     * Decrements the chance by 1
     */
    public void decrementChance() {
        this.chance--;
    }

    /**
     * Sets the value of score and chance to 0 and 3 when called
     */
    public void restartValue(){
        this.score = 0;
        this.chance = 3;
    }

    //HELPER METHODS
    /**
     * This method is a visibility setter for javafx nodes(GUI components)
     * ... is a vararg parameter, which allows the multiple arguments of specific types.
     * nodes acts like an array so, we can use foreach loop
     * takes two parameters a boolean value, and variable number of javafx.scene.Nodes Object
     * then runs foreach loop, for each node in [nodes array] setVisible to boolean visible value
     * @param visible boolean value
     * @param nodes javafx.Scene.node elements
     */
    public void setVisibility(boolean visible,Node... nodes){
        for(Node node : nodes){
            node.setVisible(visible);
        }
    }

    /**
     * This method is a disable setter for buttons, same logic as setVisibility() Method
     * only changes the disable properties for buttons
     * @param disable boolean value
     * @param buttons javafx.sScene.node Named buttons
     */
    public void disableSetter(boolean disable, Node... buttons){
        for (Node button: buttons){
            button.setDisable(disable);
        }
    }


    /**
     * This method draws a rectangle with stroke and uses GraphicContext
     * @param gc GraphicContext
     */
    public void drawQuizScreenStrokeRectangle(GraphicsContext gc){
        gc.setStroke(Color.web("#D4D4D4"));
        gc.strokeRect(250,50, 230,125);
        gc.strokeRect(250,190, 230,125);
        gc.strokeRect(250,330, 230,125);
    }

}




