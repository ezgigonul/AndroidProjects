package com.example.cardviewapplication;

public class Desserts {

    private String name;
    private String card_description;
    private String ingredient;
    private String steps;
    private int photo_id;


    public Desserts(String name, String card_description, String ingredient, String steps, int photo_id) {
        this.name = name;
        this.card_description = card_description;
        this.ingredient = ingredient;
        this.steps = steps;
        this.photo_id = photo_id;
    }

    public static final Desserts[] desserts = {
            new Desserts("Chocolate Tiffin", "Go all out and treat yourself to these chocolate tiffin slices, full of milk and dark chocolate, raisins and biscuit. Be warned: they wont last long!", "3 tbsp caster sugar\n3 tbsp golden syrup\n6 tsp cocoa powder\n110g milk chocolate\n110g dark chocolate", "1.\nButter and line a 15-20cm tin with baking parchment. In a large saucepan, melt the butter, sugar, syrup and cocoa. Stir through the biscuits and raisins.\n2.\nPour the mixture into the prepared tin and press down, then smooth the top with the back of a spoon. Microwave both chocolates in short 20 second bursts, stirring frequently, until melted. Pour them over the mixture in the tin. Use a palette knife or spoon to smooth over, so its completely coated in chocolate.\n3.\nPut the tin into the fridge and leave for about 2 hrs to set, or overnight. Run a kitchen knife under the hot tap then cut into squares.", R.drawable.chocolate_tiffin),
            new Desserts("Chocolate Molten Cakes", "Bake an impressive dinner party dessert with minimum fuss – these chocolate puddings, also known as chocolate fondant or lava cake, have a lovely gooey centre", "100g butter\n3 large eggs\n6 tsp cocoa powder\nsingle cream, to serve\n½ tsp vanilla extract", "1.\nButter and line a 15-20cm tin with baking parchment. In a large saucepan, melt the butter, sugar, syrup and cocoa. Stir through the biscuits and raisins.\n2.\nUsing an electric hand whisk, mix in 150g light brown soft sugar, then 3 large eggs, one at a time, followed by ½ tsp vanilla extract and finally 50g plain flour. Divide the mixture among the darioles or basins.\n3.\nYou can now either put the mixture in the fridge, or freezer until you're ready to bake them. Can be cooked straight from frozen for 16 mins, or bake now for 10-12 mins until the tops are firm to the touch but the middles still feel squidgy.", R.drawable.easy_chocolate_molten_cakes),
            new Desserts("Tracle Tart", "Treat family and friends to a comforting treacle tart with crumbly pastry and rich filling. A classic British dessert, serve with ice cream or clotted cream", "250g plain flour\n½ tsp fine salt\n6 tsp cocoa powder\n2-3 tbsp cold water\n2 medium egg yolks", "1.\nSieve the flour and salt into a large bowl. Add the butter and rub together with your fingers to a fine breadcrumb-like texture (you can also do this part in a food processor).\n2.\nHeat the oven to 200C/180C fan/gas 6. Put a baking sheet into the oven to heat up.\n3.\nLower the oven temperature to 160C/140C fan/gas\n4.\n Combine the golden syrup, ginger, ginger syrup, lemon, eggs and breadcrumbs in a bowl, briefly whisking everything together until combined.", R.drawable.treacle_tart),
            new Desserts("Floating Island", "Whip up some floating islands at your next dinner party, the retro-cool French dessert featuring crème anglaise, meringue and caramel", "500ml whole milk\n2 tsp vanilla bean paste\n4 large egg yolks (save the white of 1 egg and freeze the rest for another recipe)\n100g caster sugar\n2 medium egg yolks", "1.\nHeat the milk and vanilla in a pan until steaming. Whisk the egg yolks and sugar in a large bowl until smooth and creamy.\n2.\nPut the egg white in a stand mixer. Whisk for 4-5 mins until you get soft peaks.\n3.\nPour the milk and 200ml water into a wide pan or medium frying pan and heat until steaming, but not boiling.", R.drawable.floating_island),
            new Desserts("White Chocolate Cheesecake", "Make a simple, creamy dessert for a dinner party with very little effort. Pairing this white chocolate cheesecake with fresh fruit offsets the richness", "300g digestive biscuits\n150g unsalted butter, melted, plus extra to grease\n300g full-fat cream cheese (we used Philadelphia)\n300ml double cream\n200g strawberries or raspberries, to serve", "1.\nCrush the biscuits in a food processor until completely ground. Add butter and whizz again until you have the desired crumbly consistency.\n2.\nGrease and line the base of a 23cm deep, loose-bottomed cake tin. Add the biscuit mixture to the cake tin and pat it flat. Leave to set in the fridge for approximately 30 mins.\n3.\negin melting the chocolate in a heatproof glass bowl over a small pan of hot water on a low heat. Stir occasionally to prevent sticking. Remove from the heat and leave to cool for 10 mins until barely warm but still liquid.", R.drawable.white_chocolate_cheesecake),
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_description() {
        return card_description;
    }

    public void setCard_description(String card_description) {
        this.card_description = card_description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }
}
