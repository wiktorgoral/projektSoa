package Rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public App() {
        singletons.add(new BiletService());
        singletons.add(new MiejsceService());
        singletons.add(new WarningService());
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
