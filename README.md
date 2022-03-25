# MailService
:mailbox_with_mail: Microservice for Sending and Receiving Emails.

:scroll: Technologies used:

+ Java JDK 11
+ Spring Boot Framework 2.6.5
+ Lombo Framework 1.18.22
+ Javax Mail API 1.6.2
+ Swagger UI 2.9.2

:memo: Microservice Documentation:
http://localhost:8081/mail-service/swagger-ui.html

:rocket: How to Build the Project JAR and Run the Service:

1. Open the Project in Spring Tools.

2. Configure the SMTP server settings in the "\MailService\src\main\java\app\controller\EmailConfigController.java" class in the "getConfigServerSmtp" method.

3. Run JAR Creation and project build by running File "mvnw-project-mv-clean-package.cmd".

4. Run the Service by running the File "mvnw-project-start-service.cmd"

:construction_worker: https://www.luishenriqueborgesviana.com/2022/03/mail-service-api.html

