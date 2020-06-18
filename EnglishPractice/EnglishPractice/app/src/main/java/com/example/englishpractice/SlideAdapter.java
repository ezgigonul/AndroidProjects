package com.example.englishpractice;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by Sumeet Jain on 25-02-2018.
 */

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    //Array
    public int[] list_images = {
            R.drawable.restaurant,
            R.drawable.doctor,
            R.drawable.supermarket,
    };

    public String[] list_title = {
            "Dialogue: Ordering Food in a Restaurant",
            "Conversation about headache in a Hospital",
            "Conversation at the Supermarket",
    };

    public String[] list_description = {
            "Waiter: Hello, I’ll be your waiter today. Can I start you off with something to drink?\n" +
                    "\n" +
                    "Ralph: Yes. I’ll have iced tea, please.\n" +
                    "\n" +
                    "Anna: And I’ll have lemonade.\n" +
                    "\n" +
                    "Waiter: OK. Are you ready to order, or do you need a few minutes?\n" +
                    "\n" +
                    "Ralph: I think we’re ready. I’ll have the tomato soup to start, and the roast beef with mashed potatoes and peas.\n" +
                    "\n" +
                    "Waiter: How do you want the beef — rare, medium or well done?\n" +
                    "\n" +
                    "Ralph: Well done, please.\n" +
                    "\n" +
                    "Anna: And I’ll just have the fish, with potatoes and a salad.",
            "Patient: Doctor, I’ve headache since yesterday evening.\n" +
                    "\n" +
                    "Doctor: Have you taken any medicine so far?\n" +
                    "\n" +
                    "Patient: Saridon, but the headache hasn’t disappeared.\n" +
                    "\n" +
                    "Doctor: You’ve a running nose. Looks like your headache is a result of sinus infection, and not the regular one that results from anxiety and fatigue. Lemme check.\n" +
                    "\n" +
                    "(The doctor checks the patient thoroughly.)\n" +
                    "\n" +
                    "Doctor: It’s quite clear that the infection in your sinus is the reason for your headache. I’ll prescribe an antibiotic to clear the infection and a pain reliever to relieve the pain.\n" +
                    "\n" +
                    "Patient: Thank you, doctor.",
            "Louise: Hey, Julia…Look at those desserts! How about baking some cookies today?\n" +
                    "\n" +
                    "Julia: Hmm…Yeah, that’s a great idea! While we’re here, let’s pick up the ingredients.\n" +
                    "\n" +
                    "Louise: OK, what do we need?\n" +
                    "\n" +
                    "Julia: The recipe calls for flour, sugar and butter. Oh, and we also need eggs and chocolate chips.\n" +
                    "\n" +
                    "Louise: Why don’t you get the dairy ingredients? You’ll find those in the refrigerated section in the back of the store. I’ll get the dry ingredients — they’re in aisle 10.\n" +
                    "\n" +
                    "Julia: Great! Let’s meet at the checkout.\n" +
                    "\n" +
                    "Louise: OK. See you there.",

    };
    public int[] list_color = {
            Color.rgb(255, 230, 255),
            Color.rgb(239, 189, 237),
            Color.rgb(232, 218, 239),

    };

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == (LinearLayout) obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView img = (ImageView) view.findViewById(R.id.slideimg);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription);


        img.setImageResource(list_images[position]);
        txt1.setText(list_title[position]);
        txt2.setText(list_description[position]);
        linearLayout.setBackgroundColor(list_color[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}