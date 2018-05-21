package com.michal.pickMeUp.Utills;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleConnector {

    public HashMap<String,String> getElements(String startingPoint, String endPoint) throws InterruptedException, ApiException, IOException {


        final long ONE_HOUR_MILLIS = 60 * 60 * 1000;
        HashMap<String,String> toReturn = new HashMap<>();

        GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyCjii0qYY_JAKyHj5eePOhlmtQXhRdJv4M")
            .build();

        String[] originAdresses= new String[1];
        String[] destinationAddresses= new String[1];
        originAdresses[0]=startingPoint;
        destinationAddresses[0]=endPoint;
        DistanceMatrixRow[] rows= new DistanceMatrixRow[1];
        DistanceMatrix results =   DistanceMatrixApi.newRequest(context)
            .origins(startingPoint)
            .destinations(endPoint)
            .mode(TravelMode.DRIVING)
            .trafficModel(TrafficModel.PESSIMISTIC)
            .departureTime(new DateTime(System.currentTimeMillis() + ONE_HOUR_MILLIS))
            .await();
        DistanceMatrixElement[] element=results.rows[0].elements;
        String dist = element[0].distance.toString();
        String time = element[0].durationInTraffic.toString();
        System.out.println("distance: "  + dist+" time: "+time);
        toReturn.put("distance",dist);
        toReturn.put("time", time);
        return toReturn;

    }
}
