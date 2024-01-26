package org.acme.funqy;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import io.quarkus.funqy.Funq;
import static io.cloudevents.core.CloudEventUtils.mapData;


public class LandingRequestFunction {

    @Funq
    public void landingRequest(CloudEvent ce) {
        var objectMapper = new ObjectMapper();

        PojoCloudEventData<LandingDetails> cloudEventData = mapData(
            ce,
            PojoCloudEventDataMapper.from(objectMapper,LandingDetails.class)
        );
        LandingDetails details = cloudEventData.getValue();

        System.out.println("Approved for landing on planet " + details.getPlanet());
    }
}
