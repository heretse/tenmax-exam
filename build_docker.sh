./gradlew clean

export SVC_NAME=$(grep -o "rootProject.name = '.*'" settings.gradle | cut -d= -f2 | sed -e 's/ //g' | sed "s/'//g");
export TAG_NAME=$(grep -o "version = '.*'" build.gradle | cut -d= -f2 | sed -e 's/ //g' | sed "s/'//g");

if [ "${SVC_NAME}" ] && [ "${TAG_NAME}" ]; then
export IMAGE_NAME="heretse/${SVC_NAME}:${TAG_NAME}";
echo "service name : ${SVC_NAME}";
echo "tag version : ${TAG_NAME}";
echo "image name : ${IMAGE_NAME}";
./gradlew jibDockerBuild --image="${IMAGE_NAME}"
else echo "Can not find version number from build.gradle";
fi
