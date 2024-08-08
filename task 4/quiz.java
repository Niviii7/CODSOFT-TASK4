import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}

public class QuizApp {
    private List<Question> questions;
    private int score;
    private List<String> resultSummary;

    public QuizApp() {
        questions = new ArrayList<>();
        resultSummary = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Berlin", "4. Madrid"}, 0));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 1));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic", "2. Indian", "3. Pacific", "4. Arctic"}, 2));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        for (int i = 0; i < questions.size(); i++) {
            final int questionIndex = i;  // Create a final copy of the index
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    resultSummary.add("Question " + (questionIndex + 1) + ": Time's up! No answer given.");
                }
            };

            timer.schedule(task, 15000);  // 15-second timer for each question

            System.out.print("Enter your choice (1-4): ");
            int userAnswer = scanner.nextInt() - 1;

            task.cancel();  // Cancel the timer if user answers within time

            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
                resultSummary.add("Question " + (i + 1) + ": Correct");
            } else {
                System.out.println("Incorrect.");
                resultSummary.add("Question " + (i + 1) + ": Incorrect");
            }
        }

        displayResult();
        scanner.close();
        timer.cancel();
    }

    private void displayResult() {
        System.out.println("\nQuiz Over! Your final score is: " + score + "/" + questions.size());
        System.out.println("\nSummary of your answers:");
        for (String result : resultSummary) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        quiz.startQuiz();
    }
}
