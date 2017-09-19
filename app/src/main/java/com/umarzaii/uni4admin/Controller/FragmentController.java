package com.umarzaii.uni4admin.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentController {

    private FragmentManager fragmentManager;
    private Fragment currentFragment;

    public FragmentController(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void startFragment(Fragment fragmentClass, int containerViewId) {
        currentFragment = fragmentClass;
        startTransaction(containerViewId);
    }

    public void startFragment(Fragment fragmentClass, int containerViewId, Bundle bundle) {
        currentFragment = fragmentClass;
        currentFragment.setArguments(bundle);
        startTransaction(containerViewId);
    }

    public void stackFragment(Fragment fragmentClass, int containerViewId, String stackName) {
        currentFragment = fragmentClass;
        startTransaction(containerViewId, stackName);
    }

    public void stackFragment(Fragment fragmentClass, int containerViewId, Bundle bundle, String stackName) {
        currentFragment = fragmentClass;
        currentFragment.setArguments(bundle);
        startTransaction(containerViewId, stackName);
    }

    public void popBackStack(String stackName) {
        fragmentManager.popBackStack (stackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void startTransaction(int containerViewId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, currentFragment);
        transaction.commit();
    }

    private void startTransaction(int containerViewId, String stackName) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, currentFragment);
        transaction.addToBackStack(stackName);
        transaction.commit();
    }

}