#!/bin/bash


docker compose down -v;
docker compose up -d --build;
bash ./apache-jmeter-5.6.3/bin/jmeter.sh


#GRAFANA_URL="http://localhost:3000"
#DASHBOARD_ID=19004
#ADMIN_USER="admin"
#ADMIN_PASSWORD="123456"
#
#curl -u $ADMIN_USER:$ADMIN_PASSWORD "$GRAFANA_URL/api/dashboards/id/$DASHBOARD_ID" -o ./provisioning/dashboards/dashboard.json
