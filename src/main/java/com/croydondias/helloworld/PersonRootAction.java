package com.croydondias.helloworld;

import hudson.Extension;
import hudson.Main;
import hudson.model.RootAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * Created by croydon on 2/7/17.
 */
@Extension
public class PersonRootAction implements RootAction {

    private static final Logger LOGGER = Logger.getLogger(PersonRootAction.class.getName());
    private final PersonDao dao = new PersonDao();

    public Person getPerson() {
        if (Main.isDevelopmentMode) {
            LOGGER.log(INFO, "getting the person");
        }
        return dao.getOrCreateObject();
    }

    public void doSave(final StaplerRequest request, final StaplerResponse response) throws Exception {
        JSONObject form = request.getSubmittedForm();

        Person person = new Person();
        person.setName(form.getString("name"));
        person.setPhones(bindToList(form.get("phones")));
        dao.save(person);
        LOGGER.log(INFO, "Persisting the person object");

        response.sendRedirect(request.getContextPath());
    }

    private List<String> bindToList(Object object) {
        List<String> list = new ArrayList<>();

        if (object == null)
            return list;

        final String key = "phone";
        if (object.getClass() == JSONObject.class) {
            JSONObject obj = (JSONObject) object;
            list.add(obj.getString(key));
        }

        if (object.getClass() == JSONArray.class) {
            JSONArray array = (JSONArray) object;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                list.add(obj.getString(key));
            }
        }

        return list;
    }

    @Override
    public String getIconFileName() {
        return "gear.png";
    }

    @Override
    public String getDisplayName() {
        return "Person Root Action Plugin";
    }

    @Override
    public String getUrlName() {
        return "person";
    }
}
