#!/bin/bash

APP_URL="http://localhost:8080/person/health_check"  

echo $APP_URL
response=$(curl -s -o /dev/null -w "%{http_code}" "$APP_URL")

if [[ $response -eq 200 ]]; then
    # log Ok and time
    echo "Person service is healthy."
else
    # log fail and time, email or text someone
    echo "Error: Person service is unreachable!"
fi