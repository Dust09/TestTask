# Запуск проекта

Для запуска UI теста на Windows необходимо заменить chromedriver по пути src/test/resources/chromedriver 

1) ./gradlew test --rerun-tasks - запустит все API тесты
2) ./gradlew cucumberTest - запустит UI тест

Отчет по API тесту лежит по пути build/reports/tests/test/index.html

Отчет по UI тесту лежит по пути target/cucumber-report.html

<img width="786" alt="JunitResult" src="https://user-images.githubusercontent.com/52101777/159156656-3b42e7a7-c1f5-46fb-b48e-be09b3a53969.png">
<img width="1279" alt="CucumberResult" src="https://user-images.githubusercontent.com/52101777/159156674-532293b1-6384-4c1a-b5ab-c44f9c857f7e.png">


Дополнительно:  
Запускалось на Mac OS X x86_64  
Java: openjdk version "1.8.0_312" 
