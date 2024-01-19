 gcloud functions deploy quarkus-funqy-gcp-cloud-events \
    --gen2 \
    --entry-point=io.quarkus.funqy.gcp.functions.FunqyCloudEventsFunction \
    --runtime=java21 \
    --trigger-http \
    --allow-unauthenticated \
    --source=target/deployment \
    --region=europe-west1