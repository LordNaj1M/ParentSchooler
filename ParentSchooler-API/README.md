ParentSchooler API Welcome to our API

What you need
1. Cloud Environment: Google Cloud Platform (Cloud Storage, Cloud Shell, Container Registry, Cloud Run)
2. Programming Language: Python
3. Web Server: Flask API
4. Server: Cloud Run

How to setup with Google Cloud Platform

Create Cloud Storage
1. Choose Cloud Storage on navigation menu
2. Click Create Bucket
3. Name your bucket as you wish
4. Location data : Region and choose asia-southeast2 (Jakarta)
5. Create the Bucket
6. Upload the encoder_decoder_model.h5 to the bucket
7. Upload the fine_tuned_model folder to the bucket
8. Setting up Cloud Shell

Deploy ParentSchooler-API Container image to Container Registry
1. Click Activate Cloud Shell in the top right corner of the page on Google Cloud console. 
2. At a shell prompt, run the following command to prepare the server requirements
- mkdir ParentSchooler
- cd ParentSchooler
- git init
- git remote add -f origin https://github.com/LordNaj1M/ParentSchooler.git
- git config core.sparseCheckout true
- echo "ParentSchooler-API/*" >> .git/info/sparse-checkout
- git pull origin main
- gsutil cp gs://parentschooler/encoder_decoder_model.h5 ParentSchooler/
- gsutil cp -r gs://parentschooler/fine_tuned_model ParentSchooler/
- gcloud builds submit --tag gcr.io/<your-project-id>/parentschooler-api
3. Click Authorize when the popup appears. Wait until the container image building process is complete.

