package service;


import model.Bilet;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public interface BiletService {

    public Response addBilet(Bilet p);

    public Response deleteBilet(String id);

    public Response getBilet(String id);

    public Response getAllBilet();


}
