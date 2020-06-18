package com.example.englishpractice;

public class Question {
    private String name,question,chooice1,chooice2,chooice3,chooice4,answer;

    public Question(String name, String question, String chooice1, String chooice2, String chooice3, String chooice4, String answer) {
        this.name = name;
        this.question = question;
        this.chooice1 = chooice1;
        this.chooice2 = chooice2;
        this.chooice3 = chooice3;
        this.chooice4 = chooice4;
        this.answer = answer;
    }

    public static final Question[] questions = {
            new Question("Question 1",
                    "How many students _________ in your class?","there is","there are","are there","is there","are there"),
            new Question("Question 2",
                    "My jackets are in the _________ in my bedroom.","cupboard","fridge","cooker","pillow","cupboard"),
            new Question("Question 3",
                    "It’s my mother’s birthday today. She’s _________","fourteen","fourth","forty","forthy","forty"),
            new Question("Question 4",
                    "There are lots of _________ here.","tourist","tourists","tourist’s","trip","tourists"),
            new Question("Question 5",
                    "Do you live with your parents?","No, I’m not.","No, I didn’t.","No, I don’t","No, I haven’t","No, I don’t"),
            new Question("Question 6",
                    "She can’t talk to you now. She _________ her hair.","washes","washed","is washing","are washing","is washing"),
            new Question("Question 7",
                    "Whatever  you _________ for  from  a  visit  to  San  Francisco in  the  USA,  you  won’t  be  disappointed.","guess","excuse","question","surprise","surprise"),
            new Question("Question 8",
                    "She can’t talk to you now. She _________ her hair.","washes","washed","is washing","are washing","is washing"),
            new Question("Question 9",
                    "_________ we go on holiday? Yes! That's a great Idea.","Will","Have","Shall","Do","Shall"),
            new Question("Question 10",
                    "What is rubbish?","something which students eat","something which is made of wood","something which pollutes","something which people like to play with","something which pollutes"),
            new Question("Question 11",
                    "He really likes fruits and vegetables. He eats lots of _______ .","strawberries","yogurt","cartons","chicken","strawberries"),
            new Question("Question 12",
                    "Which word means nearly the same as scream ?","find","stop","sell","shout","shout"),
            new Question("Question 13",
                    "We don't use THE before _______.","classroom objects","names of cities","uncountable nouns","animals","names of cities"),
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChooice1() {
        return chooice1;
    }

    public void setChooice1(String chooice1) {
        this.chooice1 = chooice1;
    }

    public String getChooice2() {
        return chooice2;
    }

    public void setChooice2(String chooice2) {
        this.chooice2 = chooice2;
    }

    public String getChooice3() {
        return chooice3;
    }

    public void setChooice3(String chooice3) {
        this.chooice3 = chooice3;
    }

    public String getChooice4() {
        return chooice4;
    }

    public void setChooice4(String chooice4) {
        this.chooice4 = chooice4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
