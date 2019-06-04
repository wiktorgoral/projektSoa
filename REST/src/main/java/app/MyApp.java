package app;

import service.BiletServiceImpl;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MyApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public MyApp() {
        singletons.add(new BiletServiceImpl());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
}