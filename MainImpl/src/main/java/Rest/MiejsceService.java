package Rest;

import DAO.Miejsce;
import DAO.Strefa;
import POJO.MiejscePOJO;
import POJO.StrefaPojo;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/miejsca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MiejsceService {

    @POST
    public Response addMiejsce(InputStream input) throws IOException {
        String jsonTxt = IOUtils.toString(input, "UTF-8");
        JSONObject json = new org.json.JSONObject(jsonTxt);
        int strefaId = json.getInt("strefaId");
        StrefaPojo strefa = Strefa.get(strefaId);
        if (strefa==null)
            return Response.status(400).entity("Nie ma takiego miejsca").build();
        MiejscePOJO bilet = new MiejscePOJO(true,strefa);
        Miejsce.add(bilet);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMiejsce(@PathParam("id") int id) {
        MiejscePOJO miejsce = Miejsce.get(id);
        if(miejsce == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Miejsce nie istnieje").build();
        }
        Miejsce.delete(id);
        return Response.status(Response.Status.OK)
                .entity("Miejsce usuniety").build();
    }

    @GET
    @Path("/{id}")
    public Response getMiejsce(@PathParam("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(Miejsce.get(id)).build();
    }

    @GET
    public Response getAllMiejsce() {
        return Response.status(Response.Status.OK).entity(Miejsce.getAll()).build();
    }

}
