The code is about the challeange.


I used postman for test my api rest.
For test this api, it's necessary download the code and import in sts-eclipse and run spring boot application



Routes:

goal

list all books    |  Get localhost:8080/book?auth=True

create book		  |  Post localhost:8080/book?auth=True | ```json payload 

{
    "name": "teste titulo2",
    "publisher": "atica",
    "yearOfPublication": 2000,
    "sumary": "sumary",
    "authors" : [
                    {
                        "name": "lucas3"
                    },
                    {
                        "name": "maria1"
                    }

    ],
    "bookInventory": {
        "quantity": 0
    },
    "ebook": {
        "format" : "ebook3"
    }
} 
```


The api have filter for authenticate, the auth is true for allow requests