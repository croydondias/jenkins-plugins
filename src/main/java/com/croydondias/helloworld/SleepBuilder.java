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
    private String hostname;
    private int port;

    @DataBoundConstructor
    public SleepBuilder(long time, String hostname, int port) {
        this.time = time;
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public boolean perform(Build<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException {
        listener.getLogger().println(String.format("Sleeping for %d ms on %s:%d.", time, hostname, port));
        Thread.sleep(time);
        return true;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
                return (number >= 0) ? FormValidation.ok() : FormValidation.error("Please enter a number >= 0");
            } catch (NumberFormatException e) {
                return FormValidation.error("Please enter a valid number");
            }
        }

        public FormValidation doCheckHostname(@QueryParameter String hostname) {
            if (hostname == null || hostname.length() == 0 || hostname.trim().length() == 0)
                return FormValidation.error("Please enter a valid hostname value");
            return FormValidation.ok();
        }

        public FormValidation doCheckPort(@QueryParameter String port) {
            try {
                int number = Integer.valueOf(port);
                return (number >= 0) ? FormValidation.ok() : FormValidation.error("Please enter a number >= 0");
            } catch (NumberFormatException e) {
                return FormValidation.error("Please enter a valid number");
            }
        }
    }
}
