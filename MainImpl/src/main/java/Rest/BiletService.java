package Rest;


import DAO.Bilet;
import DAO.Miejsce;
import POJO.BiletPOJO;
import POJO.MiejscePOJO;
import Warning.Warningi;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.naming.InitialContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

@Path("/bilety")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BiletService {

    @POST
    public Response addBilet(InputStream input) throws IOException {
        String jsonTxt = IOUtils.toString(input, "UTF-8");
        JSONObject json = new JSONObject(jsonTxt);
        int miejsceId = json.getInt("miejsceId");
        int czas = json.getInt("czas");
        MiejscePOJO miejsce = Miejsce.get(miejsceId);
        if (miejsce==null)
            return Response.status(400).entity("Nie ma takiego miejsca").build();
        Date data = new Date();
        long czass = data.getTime();
        Timestamp start = new Timestamp(czass);
        Timestamp koniec = new Timestamp(czass+czas*60*1000);
        BiletPOJO bilet = new BiletPOJO(start,koniec,miejsce);
        Bilet.add(bilet);
        try {
            InitialContext ctx = new InitialContext();
            Warningi alertBean;
            alertBean = (Warningi) ctx.lookup("java:global/MainImpl-1.0-SNAPSHOT/Alert!Warning.remote.WarningiRemote");
            alertBean.dodajBilet(bilet);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Response.status(200).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteBilet(@PathParam("id") int id) {
        BiletPOJO bilet = Bilet.get(id);
        if( bilet == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Bilet nie istnieje").build();
        }
        Bilet.delete(id);
        return Response.status(Response.Status.OK)
                .entity("Bilet usuniety").build();
    }

    @GET
    @Path("/{id}")
    public Response getBilet(@PathParam("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(Bilet.get(id)).build();
    }



}