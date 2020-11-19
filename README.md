1155755184807198781

Запуск віртуальних машин
gcloud compute ssh daemon-1
gcloud compute ssh daemon-2
gcloud compute ssh hosts-server
gcloud compute ssh app

Старт демонів
java -jar Daemon.jar
java -jar Daemon.jar

Запуск сервера
java -jar TCPHostsServer.jar

В app
cd test3
echo 10.156.0.5 > out/server
make

nano hosts.list
