# ParentSchooler API
Welcome to our API

## What you need
1. Cloud Environment: Google Cloud Platform (Cloud Storage, Cloud Shell, Container Registry, Cloud Run)
2. Programming Language: Python
3. Web Server: Flask API
4. Server: Cloud Run

# How to setup with Google Cloud Platform

Create Cloud Storage
1. Choose Cloud Storage on navigation menu
2. Click Create Bucket
3. Name your bucket as you wish
4. Location data : Region and choose asia-southeast2 (Jakarta)
5. Create the Bucket
6. Upload the encoder_decoder_model.h5 (https://drive.google.com/file/d/1yxIMxyjZlAwM26sjh6mD4kUp0t12-Quc/view?usp=sharing) to the bucket
7. Upload the fine_tuned_model folder (https://drive.google.com/drive/folders/1ZTUcQ-vGewFDG0vzzCruv3BeDXGrK4Qw?usp=sharing) to the bucket

# Deploy ParentSchooler-API Container image to Container Registry
1. Click Activate Cloud Shell in the top right corner of the page on Google Cloud console. 
2. At a shell prompt, run the following command to prepare the server requirements
- mkdir ParentSchooler
- cd ParentSchooler
- git init
- git remote add -f origin https://github.com/ParentSchooler/ParentSchooler.git
- git config core.sparseCheckout true
- echo "ParentSchooler-API/*" >> .git/info/sparse-checkout
- git pull origin main
- gsutil cp gs://parentschooler/encoder_decoder_model.h5 ParentSchooler/
- gsutil cp -r gs://parentschooler/fine_tuned_model ParentSchooler/
- gcloud builds submit --tag gcr.io/<your-project-id>/parentschooler-api
3. Click Authorize when the popup appears. Wait until the container image building process is complete.

# Deploy ParentSchooler-API Container Registry to Cloud Run
1. Choose Cloud Run on navigation menu
2. Click Create Service
3. Click SELECT for Container image URL
4. Go to tab CONTAINER REGISTRY
5. Select gcr.io/<your-project-id>/parentschooler-api then select your container registry
6. Fill Service name with parentschooler-api
7. Location data : Region and choose asia-southeast2 (Jakarta)
8. Set your Autoscaling as you wish
9. For Authentication select Allow unauthenticated invocations
9. Click Dropdown Container(s), Volumes, Networking, Security 
10. For Container fill port 8080
11. Choose Resources Memory 8 GiB and CPU 2 vCPUs
12. CREATE the Cloud Run service

After successful deployment, you will receive a Service URL.
