#!/bin/bash

echo 'Iniciando pods do ambiente...'

kubectl apply -f mysql-secrets.yaml
kubectl apply -f mysql-configMap.yaml
kubectl apply -f db-deploy.yaml
kubectl apply -f app-deploy.yaml

echo '---- PODS CRIADAS ------'
kubectl get pods