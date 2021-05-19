Exercise
Build a Book catalogue microservice that allows consumer to do the following:

Add a book with Title, Author(s), 13-digit ISBN, and Publication Date
Delete a book
Update a book
List books with search criteria - title, author or by ISBN
Send a message to a queue for key business events. e.g. book added, book deleted, book updated
-----------------------------------------------------------------------------------------------
SOLUTION
--------
The solution provided can be reviewed from the code, although it has lot of scope for improvement, e.g. towards validation, exception handling, advance security, JUnit testing etc.
NOTE: I have used HTTP POST command for adding a book, although I would have used HTTP PUT command since the request is Idempotent in this case
Provide below are cURL command for execution after downloading the solution. I will also include the Postman Export for testing using postman tool.

Steps for running the application
=================================
-> Start zookeeper and kafka on local machine.
-> Create a Kafka topic with name "BookEventTopic" using below command from command line
	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic BookEventTopic
-> Run the catalog application from eclipse or jar
-> Send below cURL commands or the command from postman

cURL command are as below
-------------------------
1. Add Book 
===========
curl --location --request POST 'localhost:8081/book/' \
--header 'Authorization: Basic YWRtaW46Y29tbWJhbms=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C795035CF7DC8F898FB1DA45E84E4B1' \
--data-raw '{
    "title": "Books-4",
    "author": [
        "John4",
        "Pete4"
    ],
    "isbn": "1234567890124",
    "publicationDates": "2014-01-01T23:28:56.782Z"
}'

2. Get Book Using ISBN
======================
curl --location --request GET 'localhost:8081/book/isbn/1234567890123' \
--header 'Authorization: Basic YWRtaW46Y29tbWJhbms=' \
--header 'Cookie: JSESSIONID=5C795035CF7DC8F898FB1DA45E84E4B1' \
--data-raw ''

3. Get Book Using Title
=======================
curl --location --request GET 'localhost:8081/book/title/Books-1' \
--header 'Authorization: Basic YWRtaW46Y29tbWJhbms=' \
--header 'Cookie: JSESSIONID=5C795035CF7DC8F898FB1DA45E84E4B1'

4. Update book 
==============
curl --location --request PUT 'localhost:8081/book/' \
--header 'Authorization: Basic YWRtaW46Y29tbWJhbms=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C795035CF7DC8F898FB1DA45E84E4B1' \
--data-raw '{
  "title": "Books-1",
  "author": [
    "Johnathan",
    "Annasthasia",
    "PeterPan"
  ],
  "isbn": 1234567890123,
  "publicationDates": "2014-01-01T23:28:56.782Z"
}'

5. Delete Book
==============
curl --location --request DELETE 'localhost:8081/book/1234567890124' \
--header 'Authorization: Basic YWRtaW46Y29tbWJhbms=' \
--header 'Cookie: JSESSIONID=5C795035CF7DC8F898FB1DA45E84E4B1' \
--data-raw ''