package com.example.fragmentapplication;

public class Menu {
    private String name;
    private String description;
    private int imageResourceId;

    public Menu(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public static final Menu[] foods = {
            new Menu("ALL MEAT PIZZA",
                    "Premium Crushed Tomato Sauce made of 100% California grown vine ripened tomatoes topped with premium salami, pepperoni, all-natural Italian sausage and seasoned pork.", R.drawable.pizza),
            new Menu("CALIFORNIA VEGGIE",
                    "Premium crushed tomato sauce, fresh green peppers, fresh red onions, fresh mushrooms, diced Roma tomatoes and fresh spinach—flavored up with our Hut Favorite on the crust edge and a balsamic sauce drizzle. Best on our Thin 'N Crispy crust.", R.drawable.veggie),
            new Menu("GARLIC CHICKEN",
                    "Creamy garlic Parmesan sauce, grilled chicken, applewood smoked bacon and diced Roma tomatoes—flavored up with toasted Parmesan on the crust edge. Best on our Hand Tossed crust.", R.drawable.chicken)
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}


