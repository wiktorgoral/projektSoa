package Rest;


import model.Bilet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Path("/bilety")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_JSON)
public class BiletService implements BiletService {

    private static Map<String, Bilet> bilets = new HashMap<String, Bilet>();

    @POST
    public Response addBilet(Bilet p) {
        if(bilets.get(p.getId()) != null){
            return Response.status(Response.Status.CONFLICT)
                    .entity("Bilet exists").build();
        }
        bilets.put(p.getId(), p);
        return Response.status(Response.Status.OK)
                .entity("Bilet added").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilet(@PathParam("id") String id) {
        if( bilets.get(id) == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Bilet doesn't exists").build();
        }
        bilets.remove(id);
        return Response.status(Response.Status.OK)
                .entity("Bilet deleted").build();
    }

    @GET
    @Path("/{id}")
    public Response getBilet(@PathParam("id") String id) {
        return Response.ok(bilets.get(id)).build();
    }

    @GET
    public Response getAllBilet() {
        Set<String> ids =  bilets.keySet();
        Bilet[] p = new Bilet[ids.size()];
        int i=0;
        for(String id : ids){
            p[i] =  bilets.get(id);
            i++;
        }
        return Response.ok(p).build();
    }


}