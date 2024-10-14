#!/bin/zsh

curl --request GET -sL \
     --url 'http://localhost:8080/info'

curl --request GET -sL \
    --url 'http://localhost:8080/debug'

curl --request GET -sL \
     --url 'http://localhost:8080/error'

curl --request GET -sL \
    --url 'http://localhost:8080/trace'
