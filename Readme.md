The code is about the challeange.


I used postman for test my api rest.
For test this api, it's necessary download the code and import in sts-eclipse and run spring boot application



Routes:

goal

list all books    |  Get localhost:8080/book?auth=True

create book		  |  Post localhost:8080/book?auth=True |  {"name": "teste titulo2","publisher": "atica","yearOfPublication": 2000,"sumary": "sumary","authors" : [{"name": "lucas3"},{"name": "maria1"}],"bookInventory": {"quantity": 0},"ebook": {"format" : "ebook3"}} 

update/modify book| PUT  localhost:8080/book/{ID}?auth=true | 
{"name": "TESTE TITULO3","publisher": "atica2","yearOfPublication": 2000, "sumary": "sumary6","authors" : [{"name": "LUCAS3"},{                      "name": "MARIA1"}],"bookInventory": {"quantity": 0 },"ebook": {"format" : "ebook"}}



delete book | DELETE localhost:8080/book/{ID}?auth=true



improve quantity book inventory | GET localhost:8080/addQuantity/{ID}/{Quantity}?auth=true



remove from inventory | GET localhost:8080/removeQuantity/{ID}/{Quantity}?auth=true



find author and publisher | GET localhost:8080/find/{name}/{publisher}?auth=true



The api have filter for authenticate, the auth is true for allow requests

In file challenger.postman_collection.json has all request that i used for test api rest.