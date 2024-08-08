#!/bin/bash

#############################################
# Deploy infrastructure using Terraform
#############################################

cd infra
terraform init
terraform validate
terraform apply -auto-approve \
  -var "aws_access_key=${AWS_ACCESS_KEY_ID}" \
  -var "aws_secret_key=${AWS_SECRET_ACCESS_KEY}" \
  -var "aws_region=${AWS_REGION}"

#cd ..

echo "Deployment completed!"