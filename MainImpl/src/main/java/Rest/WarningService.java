package Rest;


import DAO.Miejsce;
import POJO.MiejscePOJO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/warnings")
@Produces(MediaType.APPLICATION_JSON)
public class WarningService {

    @GET
    @Path("/{id}")
    public Response WarningOK(@PathParam("id") int id){
        MiejscePOJO miejsce= Miejsce.get(id);
        if (miejsce==null)
            return Response.status(404).entity("Brak miejsca z id " + id ).build();
        boolean OK = Miejsce.warningOK(miejsce);
        return Response.status(200).entity(OK).build();
    }

}
