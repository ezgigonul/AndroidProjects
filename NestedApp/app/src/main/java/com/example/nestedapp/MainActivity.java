package com.example.nestedapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements LeftFragment.LeftFragmentListener, RightFragment.RightFragmentListener , LeftChildFragment.LeftChildFragmentListener ,RightChildFragment.RightChildFragmentListener{

    private static final String LEFT_TAG = "left";
    private static final String RIGHT_TAG = "right";
    private static final String LEFT_CHILD_TAG = "left_child";
    private static final String RIGHT_CHILD_TAG = "right_child";

    LeftFragment mLeftFragment;
    RightFragment mRightFragment;
    LeftChildFragment mLeftChildFragment;
    RightChildFragment mRightChildFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mLeftFragment = (LeftFragment) fragmentManager.findFragmentByTag(LEFT_TAG);
        mRightFragment = (RightFragment) fragmentManager.findFragmentByTag(RIGHT_TAG);
        if (mLeftFragment == null && mRightFragment == null) {
            mLeftFragment = new LeftFragment();
            mRightFragment = new RightFragment();
            fragmentManager.beginTransaction().add(R.id.left_fragment, mLeftFragment, LEFT_TAG).commit();
            fragmentManager.beginTransaction().add(R.id.right_fragment, mRightFragment, RIGHT_TAG).commit();

        }
    }

    @Override
    public void openLeftChildFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mLeftChildFragment = (LeftChildFragment) fragmentManager.findFragmentByTag(LEFT_CHILD_TAG);
        mLeftChildFragment = new LeftChildFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_left_container, mLeftChildFragment, LEFT_CHILD_TAG).commit();

    }

    @Override
    public void openRightChildFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mRightChildFragment = (RightChildFragment) fragmentManager.findFragmentByTag(RIGHT_CHILD_TAG);
        mRightChildFragment = new RightChildFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_right_container, mRightChildFragment, RIGHT_CHILD_TAG).commit();

    }

    @Override
    public void openLeftParentFragment() {
        mLeftFragment.chanceLeftText();
    }

    @Override
    public void openRightParentFragment() {
        mRightFragment.chanceRightText();
    }
}
