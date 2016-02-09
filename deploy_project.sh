#!/bin/bash
set -x

#  assumes we are in the project home directory
mvn package
ret=$?
if [ $ret -ne 0 ]; then
  echo "Failed to build project"
  exit $ret
fi

set +x
echo $GOOGLE_CREDENTIALS > account.json
/opt/google-cloud-sdk/bin/gcloud auth activate-service-account --key-file account.json
set -x

gsutil cp target/*.jar gs://${GSTORAGE_DEST_BUCKET}/${CIRCLE_PROJECT_REPONAME}
ret=$?
if [ $ret -ne 0 ]; then
  echo "Failed to cp jar files to gstorage"
  exit $ret
fi

curl -XPOST https://circleci.com/api/v1/project/${GITHUB_ORG}/${NEXT_PROJECT}/tree/master?circle-token=${CIRCLE_TOKEN}
