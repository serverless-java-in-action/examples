 gcloud functions deploy quarkus-funqy \
    --gen2 \
    --entry-point=io.quarkus.gcp.functions.http.QuarkusHttpFunction \
    --runtime=java21 \
    --trigger-http \
    --allow-unauthenticated \
    --source=target/deployment \
    --region=europe-west1