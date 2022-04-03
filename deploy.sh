REPOSITORY=/home/app
PROJECT_NAME=bside-server

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git pull

echo "> start build"

./gradlew build

cd $REPOSITORY

echo "> Build file copy"

cp $REPOSITORY/$PROJECT_NAME/build/libs/study-1.0.jar $REPOSITORY/

echo "> check pid"

CURRENT_PID=$(pgrep -f study-1.0.jar)

echo "> current pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 실행중인 애플리케이션이 없습니다."
else
    echo "> kill -9 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> deploy start"

nohup java -jar $REPOSITORY/study-1.0.jar 2>&1 &