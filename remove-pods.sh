#!/bin/bash

echo 'Removendo pods do ambiente...'

kubectl scale deployment --all --replicas=0
kubectl delete pods --all
kubectl delete services --all
kubectl delete deployments --all