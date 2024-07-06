#!/bin/bash

if [ -z "$1" ]; then
  echo "Informe a tag para a imagem."
else
  docker-compose up -d --build --force-recreate
fi