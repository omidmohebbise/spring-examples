#!/bin/bash
export MIGRATION_LABEL="to-be-changed"
export DATE_WITH_TIME=$(/bin/date "+%Y%m%d%H%M%S")
echo $MIGRATION_LABEL
echo $DATE_WITH_TIME
gradle liquibase:diff -D diffChangeLogFile=src/main/resources/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.yaml

