package com.example.englishpractice;

import java.util.Comparator;

public class ScoreSorter implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getScore().compareTo(u2.getScore());
    }
}
