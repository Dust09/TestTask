# Запуск проекта

Для запуска UI теста на Windows необходимо заменить chromedriver по пути src/test/resources/chromedriver 

1) ./gradlew test --rerun-tasks - запустит все API тесты
2) ./gradlew cucumberTest - запустит UI тест

Отчет по API тесту лежит по пути build/reports/tests/test/index.html

Отчет по UI тесту лежит по пути target/cucumber-report.html

Дополнительно:  
Запускалось на Mac OS X x86_64  
Java: openjdk version "1.8.0_312" 

