package com.croydondias.helloworld;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import org.apache.tools.ant.taskdefs.Sleep;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

/**
 * Created by croydon on 26/3/17.
 */
public class SleepBuilderTest {

    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void checkSleepOutput() throws IOException, ExecutionException, InterruptedException {

        long time = 12;
        SleepBuilder builder = new SleepBuilder(time);
        FreeStyleProject p = r.createFreeStyleProject();
        p.getBuildersList().add(builder);


        FreeStyleBuild build = p.scheduleBuild2(0).get();
        assertTrue(build.getLog().contains(String.format("Sleeping for %d ms.", time)));

    }
}
