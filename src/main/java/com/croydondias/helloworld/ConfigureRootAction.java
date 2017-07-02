package com.croydondias.helloworld;

import hudson.Extension;
import hudson.model.RootAction;

/**
 * Created by croydon on 2/7/17.
 */
@Extension
public class ConfigureRootAction implements RootAction {

    @Override
    public String getIconFileName() {
        return "gear.png";
    }

    @Override
    public String getDisplayName() {
        return "Configure";
    }

    @Override
    public String getUrlName() {
        return "configure";
    }
}
