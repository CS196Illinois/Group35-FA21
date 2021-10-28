package drivezero.logic;

import com.google.maps.GeoApiContext;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;

public class Routes {

    private GeoApiContext context;
    OkHttpClient client;



    // TODO ...
    public int random;

    public Routes() throws IOException {
        random = 0;
        context = new GeoApiContext().setApiKey("AIzaSyBjHOxqrRoiCl8bJ7aP-EZ9WoqZ7ps_gyU");
        client = new OkHttpClient().newBuilder().build();
    }

    public Response getDistance(String addressPoint) throws IOException {
        Request request = new Request.Builder()
                .url(addressPoint + "=AIzaSyBjHOxqrRoiCl8bJ7aP-EZ9WoqZ7ps_gyU")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}