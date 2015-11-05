package command;

import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class Command {
    protected Request request;
    protected Response response;
    protected Map map;

    public Command(Request request, Response response) {
        this.map = new HashMap();
        this.request = request;
        this.response = response;
    }  

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }    

}
