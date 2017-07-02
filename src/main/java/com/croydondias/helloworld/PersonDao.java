package com.croydondias.helloworld;

import jenkins.model.Jenkins;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by croydon on 2/7/17.
 */
public class PersonDao {

    public Person getOrCreateObject() {
        try {
            return (Person) Jenkins.XSTREAM.fromXML(new FileInputStream(getFilePath()));
        } catch (final Exception e) {
            return new Person();
        }
    }

    public void save(Person object) {
        try {
            Jenkins.XSTREAM.toXML(object, getFile());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static final String getFilePath() {
        return Jenkins.getInstance().getRootDir() + System.getProperty("file.separator", "/") + "person.xml";
    }

    private static OutputStreamWriter getFile() throws Exception {
        return new OutputStreamWriter(new FileOutputStream(getFilePath()), "UTF-8");
    }
}
