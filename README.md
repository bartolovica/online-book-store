# **WELCOME TO THE online-book-store APP**

# HOW TO RUN:

**Prerequisites**

Java Development Kit (JDK) version 11 or later
Apache Maven

**Building the Application:**
Open a command prompt or terminal window.
Navigate to the root directory of the project that contains the pom.xml file.
Run the following command to build the project:

mvn clean install

**Running the Application:**

Open a command prompt or terminal window.
Navigate to the directory that contains the JAR file for the application (usually located in the target directory).
Run the following command to start the application:

java -jar online-book-store-0.0.1-SNAPSHOT.jar

# Project description:

The application is Written in java using Spring Boot Framework
For Database I have chosen embedded H2 database.
The reason because it's easy and lightweight and I didn't want to lose time on writing DDL queries.
For ORM I have chosen Hibernate. All tables are created with help of JPA annotations.
The data is inserted with after the app initialization using ApplicationRunner and JPA functions.
I used lombok to speed up the coding. For api validation I have used Jakarta validation.
I made the main APIs for the assessment and few others, mostly getAll methods that helped me to test the application.
I focused mostly on app business logic, implementing the discounts and pointing. Also, I added invoice with all details
(price,discount,quantity totalPrice) after the purchase.
I added on my hand to give customer the cheapest book in shopping cart if he has 10 loyalty points cause this is usual
practice in web shops.

**link for Postman Collection:**
https://api.postman.com/collections/6578148-7dbbb950-4463-4602-9d4e-26f0f4737286?access_key=PMAT-01GTYDXVM85MJ08GTVNZAFNBAT
