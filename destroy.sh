!/bin/bash

Destroy infrastructure using Terraform
cd infra
terraform init
terraform destroy -auto-approve -var "aws_access_key=${AWS_ACCESS_KEY_ID}" -var "aws_secret_key=${AWS_SECRET_ACCESS_KEY}"
cd ..

echo "Destroyed completed!"