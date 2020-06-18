package com.example.englishpractice;



public class Grammer{
    private String grammer_name;
    private String grammer_description;
    private String grammer_example;
    private int grammer_photo_id;


    public Grammer(String grammer_name, String grammer_description, String grammer_example, int grammer_photo_id) {
        this.grammer_name = grammer_name;
        this.grammer_description = grammer_description;
        this.grammer_example = grammer_example;
        this.grammer_photo_id = grammer_photo_id;
    }

    public String getGrammer_name() {
        return grammer_name;
    }

    public void setGrammer_name(String grammer_name) {
        this.grammer_name = grammer_name;
    }

    public String getGrammer_description() {
        return grammer_description;
    }

    public void setGrammer_description(String grammer_description) {
        this.grammer_description = grammer_description;
    }

    public String getGrammer_example() {
        return grammer_example;
    }

    public void setGrammer_example(String grammer_example) {
        this.grammer_example = grammer_example;
    }

    public int getGrammer_photo_id() {
        return grammer_photo_id;
    }

    public void setGrammer_photo_id(int grammer_photo_id) {
        this.grammer_photo_id = grammer_photo_id;
    }

    @Override
    public String toString() {
        return grammer_name;
    }
}
