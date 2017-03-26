package com.croydondias.helloworld;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Build;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import org.apache.tools.ant.taskdefs.Sleep;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * Created by croydon on 26/3/17.
 */
public class SleepBuilder extends Builder {
    private long time;

    @DataBoundConstructor
    public SleepBuilder(long time) {
        this.time = time;
    }

    @Override
    public boolean perform(Build<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException {
        listener.getLogger().println(String.format("Sleeping for %d ms.", time));
        Thread.sleep(time);
        return true;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return aClass == FreeStyleProject.class;
        }

        @Override
        public String getDisplayName() {
            return com.croydondias.helloworld.Messages.SleepBuilder_DisplayName();
        }

        public FormValidation doCheckTime(@QueryParameter String time) {
            try {
                long number = Long.valueOf(time);
                return (number >= 0) ? FormValidation.ok() : FormValidation.error("Please enter a number >= 0" +
                        "") ;
            } catch (NumberFormatException e) {
                return FormValidation.error("Please enter a valid number");
            }
        }
    }
}
