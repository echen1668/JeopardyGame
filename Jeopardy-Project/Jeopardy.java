import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.Clip;
import java.util.*;
import java.util.concurrent.TimeUnit;

class Pair{
    String question;
    String [] answer = new String[2];
    
    public Pair(String question, String[] answer){
        this.question = question;
        this.answer[0] = answer[0];
        this.answer[1] = answer[1];
    }
    
    String getQuestion(){
        return this.question;
    }
    
    String [] getAnswer(){
        return this.answer;
    }
}

public class Jeopardy implements ActionListener {
    
    String [] category = {"SPORTS", "HISTORY", "MATH", "CULTURE"};
    
    private Pair sportQ1[] = {new Pair("He wore the number 23 and played for the Chicago Bulls.", new String[]{"Michael Jordan", "michael jordan"}),
    new Pair("He currently plays for the New York Yankees and wears the number 99", new String[]{"Aaron Judge", "aaron judge"}),
    new Pair("He is the quarterback of the Kansas City Chiefs", new String[]{"Patrick Mahomes", "patrick mahomes"}),
    new Pair("This NFL quarterback has 7 Super Bowl rings", new String[]{"Tom Brady", "tom brady"})};
    private Pair sportQ2[] = {new Pair("This baseball player batted .406 in 1941", new String[]{"Ted Williams", "ted williams"}),
    new Pair("This NFL player rushed over 2000 yards in 2020", new String[]{"Derrick Henry", "derrick henry"}),
    new Pair("This basketball player is often labeled as the greatest 3-pt shooter of all time", new String[]{"Stephen Curry", "stephen curry"}),
    new Pair("This NFL player has 1,400 receiving yards in his rookie year in 2020", new String[]{"Justin Jefferson", "justin jefferson"})};
    private Pair sportQ3[] = {new Pair("Mike Trout won 3 for these awards", new String[]{"MVP", "mvp"}),
    new Pair("This quarterback led the Giants to two Super Bowl wins in 2007 and 2011", new String[]{"Eli Manning", "eli manning"}),
    new Pair("This NBA player let the Cavaliers to an NBA Finals win in 2016 coming back from a 3-1 defict", new String[]{"Lebron James", "lebron james"}),
    new Pair("He won 3 Cy Young Awards in 2011, 2013, and 2014", new String[]{"Clayton Kershaw", "clayton kershaw"})};
    private Pair sportQ4[] = {new Pair("Michael Jordan played his final two years on this team", new String[]{"Wizards", "Washington Wizards"}),
    new Pair("Mark Cuban owns this NBA team", new String[]{"Dallas Mavericks", "Mavericks"}),
    new Pair("This Chargers quarterback threw for 5,014 yards and 38 touchdowns in 2021", new String[]{"Justin Herbert", "justin herbert"}),
    new Pair("Dan Mario never won this", new String[]{"Super Bowl", "super bowl"})};
    
    private Pair histQ1[] = {new Pair("He is the first president of the United States", new String[]{"George Washington", "george washington"}),
    new Pair("He is the first leader of the Soviet Union", new String[]{"Vladimir Lenin", "vladimir lenin"}),
    new Pair("In 1991, this nation collapsed", new String[]{"Soviet Union", "soviet union"}),
    new Pair("Napoleon Bonaparte was emperor of this nation", new String[]{"France", "france"})};
    private Pair histQ2[] = {new Pair("FDR passed these bills in the 1930s", new String[]{"New Deal", "new deal"}),
    new Pair("The Declaration of Independence was written in 1776 by this man", new String[]{"Thomas Jefferson", "thomas jefferson"}),
    new Pair("The United States dropped two atomic bombs on this country in WW2", new String[]{"Japan", "japan"}),
    new Pair("The European front of WW2 ended when the Soviet Union took this city in Germany", new String[]{"Berlin", "berlin"})};
    private Pair histQ3[] = {new Pair("This country once controlled around a quarter of the world's landmass including India.", new String[]{"UK", "Britain"}),
    new Pair("Kim Il-sung was the first leader of this nation", new String[]{"North Korea", "north korea"}),
    new Pair("He was the leader of the Soviet Union during WW2", new String[]{"Joseph Stalin", "joseph stalin"}),
    new Pair("This island still to this day refers to itself as the Republic of China.", new String[]{"Taiwan", "taiwan"})};
    private Pair histQ4[] = {new Pair("He was the leader of the Soviet Union in the 1970s", new String[]{"Leonid Brezhnev", "leonid brezhnev"}),
    new Pair("He was Abraham Lincoln's vice president and took office after Lincoln's assassination", new String[]{"Andrew Johnson", "andrew johnson"}),
    new Pair("He was the first president of Russia", new String[]{"Boris Yeltsin", "boris yeltsin"}),
    new Pair("His reforms led to the China's massive economic growth and modernization starting in the 1970s", new String[]{"Deng Xiaoping", "deng xiaoping"})};
    
    private Pair mathQ1[] = {new Pair("1 + 3", new String[]{"4", "four"}),
    new Pair("8 - 4", new String[]{"4", "four"}),
    new Pair("2 * 2", new String[]{"4", "four"}),
    new Pair("12 / 3", new String[]{"4", "four"})};
    private Pair mathQ2[] = {new Pair("This is y such that 3y = 12", new String[]{"4", "four"}),
    new Pair("This is x such that 2x = 10", new String[]{"5", "five"}),
    new Pair("The square root of 64", new String[]{"8", "eight"}),
    new Pair("The square root of 49", new String[]{"7", "seven"})};
    private Pair mathQ3[] = {new Pair("This is x such that 4x - 4 = 12", new String[]{"4", "four"}),
    new Pair("This is y such that 8y + 2 = 18", new String[]{"2", "two"}),
    new Pair("3.141592653589793238462643383279502884197 is this", new String[]{"pi", "PI"}),
    new Pair("132 / 12", new String[]{"11", "eleven"})};
    private Pair mathQ4[] = {new Pair("The square root of 25 plus 6", new String[]{"11", "eleven"}),
    new Pair("The square of 4 minius 3", new String[]{"13", "thirteen"}),
    new Pair("This is x such that (x/3) + 2 = 5", new String[]{"9", "nine"}),
    new Pair("This is y such that (y/5) + 2 = 4", new String[]{"10", "ten"})};
    
    private Pair cultureQ1[] = {new Pair("This singer sang Blank Space", new String[]{"Taylor Swift", "taylor swift"}),
    new Pair("All I Want For Christmas Is You is sang by this woman", new String[]{"Mariah Carey", "mariah carey"}),
    new Pair("He played Captain American in the Marvel movies", new String[]{"Chris Evans", "chris evans"}),
    new Pair("This franchise featured lightsabers and Wookies", new String[]{"Star Wars", "star wars"})};
    private Pair cultureQ2[] = {new Pair("This TV featured Jennifer Aniston, Matt LeBlanc, and Courteney Cox", new String[]{"Friends", "friends"}),
    new Pair("This TV featured people from varying nations trying to speak English", new String[]{"Mind Your Language", "mind your language"}),
    new Pair("This franchise owns Superman, Batman, and the Flash", new String[]{"DC", "dc"}),
    new Pair("The lead characters of Dragon Ball Z", new String[]{"Goku", "goku"})};
    private Pair cultureQ3[] = {new Pair("Burce Banner becomes this scary monster", new String[]{"Hulk", "hulk"}),
    new Pair("Goku transforms into this form in his fight against Beerus", new String[]{"Super Saiyan God", "super saiyan god"}),
    new Pair("Anakin Skywalker got defromed and burned on this planet", new String[]{"Mustafar", "mustafar"}),
    new Pair("Luke Skywalker first appeared in the New Hope movie on this planet", new String[]{"Tatooine", "tatooine"})};
    private Pair cultureQ4[] = {new Pair("Tom Hanks appared in this WW2 movie", new String[]{"Saving Private Ryan", "saving private ryan"}),
    new Pair("Harry Styles breifly appared in this WW2 movie", new String[]{"Dunkirk", "dunkirk"}),
    new Pair("This was the first movie to gross over 1 billion dollars", new String[]{"Titanic", "titanic"}),
    new Pair("He was the host of The Daily Show starting 2015 and until recently now", new String[]{"Trevor Noah", "trevor noah"})};
    
    int score = 0;
    
    int used = 0;
    
    String answer;
    String [] correctanswer = new String[2];
    
    private JLabel label;
    private JFrame frame;
    JButton button;
    private ImageIcon introImage;
    private Clip clip;
    
    private JButton first100;
    private JButton second100;
    private JButton third100;
    private JButton fourth100;
    
    private JButton first200;
    private JButton second200;
    private JButton third200;
    private JButton fourth200;
    
    private JButton first300;
    private JButton second300;
    private JButton third300;
    private JButton fourth300;
    
    private JButton first400;
    private JButton second400;
    private JButton third400;
    private JButton fourth400;
    
    private JTextField textfield;
    private JTextField textfieldDD;
    private JButton current;
    private JFrame youer_question;
    private JFrame daily_double;
    private  JButton submit;
    private  JButton submitDD;
    private JLabel numScore;
    private JFrame gameover;
    private JButton playagain;
    int amount;
    private String categoryDD1;
    private int amountDD1;
    private String categoryDD2;
    private int amountDD2;
    private String tempS;
    private int tempI;
    
    public Jeopardy(){
        Intro();
        //Set up Daily Double
        Random random = new Random();
        categoryDD1 = category[random.nextInt(3)];
        categoryDD2 = category[random.nextInt(3)];
        while(categoryDD2.equals(categoryDD1)){
            categoryDD2 = category[random.nextInt(3)];
        }
        amountDD1 = random.nextInt(3) + 1;
        amountDD2 = random.nextInt(3) + 1;
        while(amountDD2 == amountDD1){
            amountDD2 = random.nextInt(3) + 1;
        }
        Game();
    }
    
    void Game(){
        frame = new JFrame("Let's Play!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);;
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(new GridLayout(6,4,20,10));
        //Catagories
        Font font = new Font("Verdana", Font.BOLD, 36);
        JLabel cat1 = new JLabel("Sports");
        cat1.setHorizontalAlignment(JLabel.CENTER);
        cat1.setFont(font);
        cat1.setForeground(Color.WHITE);
        JLabel cat2 = new JLabel("History");
        cat2.setHorizontalAlignment(JLabel.CENTER);
        cat2.setFont(font);
        cat2.setForeground(Color.WHITE);
        JLabel cat3 = new JLabel("Math");
        cat3.setHorizontalAlignment(JLabel.CENTER);
        cat3.setFont(font);
        cat3.setForeground(Color.WHITE);
        JLabel cat4 = new JLabel("Culture");
        cat4.setHorizontalAlignment(JLabel.CENTER);
        cat4.setFont(font);
        cat4.setForeground(Color.WHITE);
        frame.add(cat1);
        frame.add(cat2);
        frame.add(cat3);
        frame.add(cat4);
        //Buttons
        first100 = new JButton("100");
        second100 = new JButton("100");
        third100 = new JButton("100");
        fourth100 = new JButton("100");
        first100.addActionListener(this);
        second100.addActionListener(this);
        third100.addActionListener(this);
        fourth100.addActionListener(this);
        
        first200 = new JButton("200");
        second200 = new JButton("200");
        third200 = new JButton("200");
        fourth200 = new JButton("200");
        first200.addActionListener(this);
        second200.addActionListener(this);
        third200.addActionListener(this);
        fourth200.addActionListener(this);
        
        
        first300 = new JButton("300");
        second300 = new JButton("300");
        third300 = new JButton("300");
        fourth300 = new JButton("300");
        first300.addActionListener(this);
        second300.addActionListener(this);
        third300.addActionListener(this);
        fourth300.addActionListener(this);
        
        first400 = new JButton("400");
        second400 = new JButton("400");
        third400 = new JButton("400");
        fourth400 = new JButton("400");
        first400.addActionListener(this);
        second400.addActionListener(this);
        third400.addActionListener(this);
        fourth400.addActionListener(this);
        
        frame.add(first100);
        frame.add(second100);
        frame.add(third100);
        frame.add(fourth100);
        
        frame.add(first200);
        frame.add(second200);
        frame.add(third200);
        frame.add(fourth200);
        
        frame.add(first300);
        frame.add(second300);
        frame.add(third300);
        frame.add(fourth300);
        
        frame.add(first400);
        frame.add(second400);
        frame.add(third400);
        frame.add(fourth400);
        
        numScore = new JLabel("Your Score: " + score);
        numScore.setFont(new Font("Verdana", Font.BOLD, 15));
        numScore.setForeground(Color.WHITE);
        frame.add(numScore);
        
        JTextArea instruc1 = new JTextArea("When answering a question, if possible answer is a name or label, type in the full name.");
        instruc1.setFont(new Font("Verdana", Font.BOLD, 15));
        instruc1.setBackground(Color.BLUE);
        instruc1.setForeground(Color.WHITE);
        instruc1.setLineWrap(true);
        instruc1.setWrapStyleWord(true);
        frame.add(instruc1);
        
        JTextArea example = new JTextArea("Ex: Type in 'Lebron James', not 'Lebron', 'lebron James', or 'LBJ'");
        example.setFont(new Font("Verdana", Font.BOLD, 15));
        example.setBackground(Color.BLUE);
        example.setForeground(Color.WHITE);
        example.setLineWrap(true);
        example.setWrapStyleWord(true);
        frame.add(example);
        
        JTextArea instruc2 = new JTextArea("Please capitalize the first letter of each word of your answer. Spelling Counts");
        instruc2.setFont(new Font("Verdana", Font.BOLD, 15));
        instruc2.setBackground(Color.BLUE);
        instruc2.setForeground(Color.WHITE);
        instruc2.setLineWrap(true);
        instruc2.setWrapStyleWord(true);
        frame.add(instruc2);
        
        frame.setVisible(true);
    }
    
    void Intro(){
        frame = new JFrame("This is Jeopardy!");
        introImage = new ImageIcon("jeop.jpeg");
        label = new JLabel(introImage);
        frame.add(label);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        File music = new File("Jeop.wav");
        PlaySound(music);
        frame.setVisible(false);
    }
    
    void Question(JButton button){
        //Sports catagorty
        current = button;
        if (button == first100){
            IsDailyDouble("SPORTS", 1);
        }
        if (button == first200){
            IsDailyDouble("SPORTS", 2);
        }
        if (button == first300){
            IsDailyDouble("SPORTS", 3);
        }
        if (button == first400){
            IsDailyDouble("SPORTS", 4);
        }
        
        //History Catagory
        if (button == second100){
            IsDailyDouble("HISTORY", 1);
        }
        if (button == second200){
            IsDailyDouble("HISTORY", 2);
        }
        if (button == second300){
            IsDailyDouble("HISTORY", 3);
        }
        if (button == second400){
            IsDailyDouble("HISTORY", 4);
        }
        
        //Math Catagory
        if (button == third100){
            IsDailyDouble("MATH", 1);
        }
        if (button == third200){
            IsDailyDouble("MATH", 2);
        }
        if (button == third300){
            IsDailyDouble("MATH", 3);
        }
        if (button == third400){
            IsDailyDouble("MATH", 4);
        }
        
        //Culture Catagory
        if (button == fourth100){
            IsDailyDouble("CULTURE", 1);
        }
        if (button == fourth200){
            IsDailyDouble("CULTURE", 2);
        }
        if (button == fourth300){
            IsDailyDouble("CULTURE", 3);
        }
        if (button == fourth400){
            IsDailyDouble("CULTURE", 4);
        }
    }
    
    void IsDailyDouble(String cat, int num){
        frame.setVisible(false);
        if((cat.equals(categoryDD1) && num == amountDD1) || (cat.equals(categoryDD2) && num == amountDD2)){
            DailyDouble();
            tempS = cat;
            tempI = num;
        }else{
            amount = (num * 100);
            Answer(cat, num);
        }
    }
    
    void Answer(String cat, int num){
        Random random = new Random();
        
        youer_question = new JFrame("Youre question");
        youer_question.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        youer_question.setLayout(new BorderLayout());
        
        youer_question.getContentPane().setBackground(Color.BLUE);
        submit = new JButton("Submit");
        submit.addActionListener(this);
        
        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(500, 40));
        
        JLabel questionText = new JLabel("Hello");
        questionText.setForeground(Color.WHITE);
        JLabel whatIs = new JLabel("What/Who is ");
        whatIs.setForeground(Color.WHITE);
        
        youer_question.add(questionText, BorderLayout.NORTH);
        youer_question.add(whatIs, BorderLayout.WEST);
        youer_question.add(textfield, BorderLayout.CENTER);
        youer_question.add(submit, BorderLayout.SOUTH);
        youer_question.pack();
        
        int n = random.nextInt(3);
        Pair ques = null;
        if(cat.equals("SPORTS")){
            if(num == 1){
                ques = sportQ1[n];
            }else if(num == 2){
                ques = sportQ2[n];
            }else if(num == 3){
                ques = sportQ3[n];
            }else if(num == 4){
                ques = sportQ4[n];
            }
        }else if(cat.equals("HISTORY")){
            if(num == 1){
                ques = histQ1[n];
            }else if(num == 2){
                ques = histQ2[n];
            }else if(num == 3){
                ques = histQ3[n];
            }else if(num == 4){
                ques = histQ4[n];
            }
        }else if(cat.equals("MATH")){
            if(num == 1){
                ques = mathQ1[n];
            }else if(num == 2){
                ques = mathQ2[n];
            }else if(num == 3){
                ques = mathQ3[n];
            }else if(num == 4){
                ques = mathQ4[n];
            }
        }else if(cat.equals("CULTURE")){
            if(num == 1){
                ques = cultureQ1[n];
            }else if(num == 2){
                ques = cultureQ2[n];
            }else if(num == 3){
                ques = cultureQ3[n];
            }else if(num == 4){
                ques = cultureQ4[n];
            }
        }
        
        correctanswer = ques.getAnswer();
        questionText.setText(ques.getQuestion());
        
        youer_question.setVisible(true);
    }
    
    void PlaySound(File sound){
        {
            try{
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sound));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.stop();
            }
            catch (Exception e){
                
            }
        }
    }
    
    void Correct(){
        JFrame correctFrame = new JFrame("That is Correct!");
        ImageIcon correctImage = new ImageIcon("Right.png");
        JLabel correctLabel = new JLabel(correctImage);
        correctFrame.add(correctLabel);
        correctFrame.pack();
        correctFrame.setVisible(true);
        File music = new File("Correct.wav");
        PlaySound(music);
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException ex)
        {
        }
        correctFrame.setVisible(false);
    }
    
    void Wrong(){
        JFrame wrongFrame = new JFrame("That is Wrong!");
        ImageIcon wrongImage = new ImageIcon("Wrong.png");
        JLabel wrongLabel = new JLabel(wrongImage);
        wrongFrame.add(wrongLabel);
        wrongFrame.pack();
        wrongFrame.setVisible(true);
        File music = new File("Wrong.wav");
        PlaySound(music);
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException ex)
        {
        }
        wrongFrame.setVisible(false);
    }
    
    void GameOver(){
        gameover = new JFrame("Game Over!");
        gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameover.setLayout(new BorderLayout());
        gameover.setBackground(Color.BLUE);
        JLabel textG = new JLabel("GAME OVER!");
        textG.setFont(new Font("Verdana", Font.BOLD, 70));
        textG.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel totalScore = new JLabel("Your final score: " + score);
        totalScore.setFont(new Font("Verdana", Font.BOLD, 50));
        //totalScore.setForeground(Color.WHITE);
        
        playagain = new JButton("Play Again?");
        playagain.setHorizontalAlignment(JButton.CENTER);
        playagain.addActionListener(this);
        
        gameover.add(textG, BorderLayout.NORTH);
        gameover.add(totalScore, BorderLayout.CENTER);
        gameover.add(playagain, BorderLayout.SOUTH);
        gameover.pack();
        
        gameover.setVisible(true);
        PlaySound(new File("GameOver.wav"));
    }
    
    void DailyDouble(){
        daily_double = new JFrame("Daily Double");
        daily_double.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        daily_double.setLayout(new BorderLayout());
        
        daily_double.getContentPane().setBackground(Color.BLUE);
        submitDD = new JButton("Submit");
        submitDD.addActionListener(this);
        
        textfieldDD = new JTextField();
        textfieldDD.setPreferredSize(new Dimension(400, 30));
        
        JLabel ddText = new JLabel("Daily Double!");
        ddText.setHorizontalAlignment(JLabel.CENTER);
        ddText.setForeground(Color.WHITE);
        JLabel wager = new JLabel("How much you want to risk? ");
        wager.setForeground(Color.WHITE);
        
        daily_double.add(ddText, BorderLayout.NORTH);
        daily_double.add(wager, BorderLayout.WEST);
        daily_double.add(textfieldDD, BorderLayout.CENTER);
        daily_double.add(submitDD, BorderLayout.SOUTH);
        daily_double.pack();
        
        daily_double.setVisible(true);
        
    }
    
    boolean isInteger(String str) {
        if(str == null || str.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        new Jeopardy();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            answer = textfield.getText();
            if(answer.equals(correctanswer[0]) || answer.equals(correctanswer[1])){
                Correct();
                score += amount;
                numScore.setText("Your Score: " + score);
            }else{
                Wrong();
                score -= amount;
                numScore.setText("Your Score: " + score);
            }
            used++;
            current.setVisible(false);
            frame.setVisible(true);
            youer_question.setVisible(false);
            if (used >= 16){
                frame.setVisible(false);
                GameOver();
            }
        }else if(e.getSource() == playagain){
            gameover.setVisible(false);
            score = 0;
            used = 0;
            new Jeopardy();
        }else if(e.getSource() == submitDD){
            if(isInteger(textfieldDD.getText())){
                int wager = Integer.parseInt(textfieldDD.getText());
                amount = wager;
                daily_double.setVisible(false);
                Answer(tempS, tempI);
            }
        }else{
            Question((JButton) e.getSource());
        }
    }
}
