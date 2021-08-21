# Product-and-Logger-Microservices-with-Docker
Product and Logger Microservices with Docker + Kafka

Instruction How to Run the Spring Boot Application with Docker Container.

1. Make sure you have installed the docker engine in your system. If not, please referring to the docker documentation 
   ->  https://docs.docker.com/desktop/windows/install/
2. Clone/download the project, once done execute this command
   -> mvn clean install package spring-boot:repackage -Dmaven.test.skip=true
3. Next execute this line to build and compose the application in docker 
   ->  docker-compose up
4. After succesfully run, open your browser to run the eureka server with this link 
   http://localhost:9000 (This is where our microservices registered here).
   Our API Gateway also is using with this port 8080 where it handles the request and response
   for both microservice Product and Logger.
   ![eureka-registered-services](https://user-images.githubusercontent.com/15647309/130320704-68cdf7db-5059-45da-acc0-917ab4122cf5.PNG)
5. Open POSTMAN for our client-side API request.
 - View Product
 - http://localhost:8080/product/list
 - ![view-product-list-response](https://user-images.githubusercontent.com/15647309/130321130-e7a78ad5-ec70-452a-bca5-878843c5a5f0.PNG)
 - Add Product 
 - http://localhost:8080/product
 - ![add-product-postman](https://user-images.githubusercontent.com/15647309/130320875-5c43cd6c-fa51-4edd-a3f9-7b1587203788.PNG)
 - ![add-product-response](https://user-images.githubusercontent.com/15647309/130320902-efe3d93e-e0a4-431b-9629-71ff6b668b4d.PNG) 
 - Add Product Order
 - http://localhost:8080/product/1/order
 - ![add-order-request](https://user-images.githubusercontent.com/15647309/130320954-88939b48-520e-4e2a-ae8b-f82269d204d8.PNG)
 - ![add-order-response](https://user-images.githubusercontent.com/15647309/130320957-7722fd6a-1478-46b3-95a7-bcffb91d6f22.PNG)
 - Add More Product(s) by Order ID
 - http://localhost:8080/product/1/order/2
 - ![add-product-by-order-id-request](https://user-images.githubusercontent.com/15647309/130321052-04e58325-4fb9-40ca-8139-85e48d93102d.PNG)
 - ![add-product-by-order-id-response](https://user-images.githubusercontent.com/15647309/130321054-55425b8f-ba55-4865-9bd6-ad6bc591a7b0.PNG)
 - ![add-product-by-order-id-response2](https://user-images.githubusercontent.com/15647309/130321055-ed38a620-70ae-4fc4-be00-3d8d6b907240.PNG)
6. To view Kafka messages: (Kafka and Zookeeper is running through Docker)
- Product Microservice - Producer
- Logger Microservice - Consumer
- ![Kafka-consumed-message-from-broker](https://user-images.githubusercontent.com/15647309/130321178-6edc0866-e0ae-4ef6-a149-b9d385aa50ff.PNG)
7. Let's view our mysql database records in command shell examples. (Mysql is running through Docker)
-![mysql-db-record](https://user-images.githubusercontent.com/15647309/130321226-0e59d922-70d6-4fd9-b175-e13c70cc3b3c.PNG)
9. Structure of my Docker Container that are running.
10.![product-docker-running](https://user-images.githubusercontent.com/15647309/130321265-a8578fa1-3023-40d4-b2a3-504b7684f8fd.PNG)
     
Thats all! This is my first time doing Spring Boot with Docker. This serves as my practice in doing this activity.
   






