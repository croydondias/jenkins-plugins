package com.croydondias.helloworld;

import hudson.Extension;
import hudson.model.RootAction;

/**
 * Created by croydon on 26/3/17.
 */
@Extension
public class OnlineRootAction implements RootAction {

    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "Connect Online";
    }

    @Override
    public String getUrlName() {
        return "https://www.google.com";
    }

}
