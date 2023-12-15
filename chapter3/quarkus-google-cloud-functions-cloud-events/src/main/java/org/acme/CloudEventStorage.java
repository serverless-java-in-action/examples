package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import com.google.cloud.functions.CloudEventsFunction;

import io.cloudevents.CloudEvent;

@Named("cloudEventStorage") 
@ApplicationScoped 
public class CloudEventStorage implements CloudEventsFunction { 

    /*
  curl localhost:8080 \
  -X POST \
  -H "Content-Type: application/json" \
  -H "ce-id: 123456789012345" \
  -H "ce-specversion: 1.0" \
  -H "ce-time: 2023-12-14T22:03:00.123Z" \
  -H "ce-type: google.cloud.storage.object.v1.finalized" \
  -H "ce-source: //storage.googleapis.com/projects/_/buckets/MY-BUCKET-NAME" \
  -H "ce-subject: objects/MY_FILE.txt" \
  -d '{
        "bucket": "MY_BUCKET",
        "contentType": "text/plain",
        "kind": "storage#object",
        "md5Hash": "...",
        "metageneration": "1",
        "name": "MY_FILE.txt",
        "size": "352",
        "storageClass": "MULTI_REGIONAL",
        "timeCreated": "2023-12-14T22:03:00.123Z",
        "timeStorageClassUpdated": "2023-12-14T22:03:00.123Z",
        "updated": "2023-12-14T22:03:00.123Z"
      }'
    */
    @Override
    public void accept(CloudEvent cloudEvent) throws Exception { 
        System.out.println("CloudEvent Id: " + cloudEvent.getId());
        System.out.println("CloudEvent Subject: " + cloudEvent.getSubject());
        System.out.println("CloudEvent Type: " + cloudEvent.getType());
        System.out.println("CloudEvent Data: " + new String(cloudEvent.getData().toBytes())); 
         System.out.println("Welcome to Google CloudEvents with Quarkus!");
    }
}