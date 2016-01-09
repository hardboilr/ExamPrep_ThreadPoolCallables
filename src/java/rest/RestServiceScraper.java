package rest;

import com.google.gson.Gson;
import entity.Group;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import service.FacadeGroup;

@Path("group")
public class RestServiceScraper {

    @Context
    private UriInfo context;
    private final Gson gson = new Gson();
    private final FacadeGroup facade;
    List<Group> groups;

    public RestServiceScraper() {
        facade = new FacadeGroup();
        groups = new ArrayList();
    }

    /**
     * api/group returns json formatted group-array
     * @return json formatted Group-array 
     */
    @GET
    @Produces("application/json")
    public Response getGroups() {
        groups = facade.getGroups();
        return Response.ok(gson.toJson(groups)).build();
    }
}
