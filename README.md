# ProductsData-as-a-Service

This Service is an implementation of RESTAPI style around the products data ``` \data\sample_product_data.tsv``` for a set of 50k Products. The main aim is to build a various search query functionality based on the products data parameters to service.

The Service was developed using the SpringBoot Framework and build with the Apache Maven. The Application Service is designed by following the MVC (Model-View-Control) Design Pattern where the Model Class (MainApplication.java) and Rest Controller class is (MainController.java) is designed to fulfill the HTTP requests.

As this service is mainly concentrated on providing the HTTP responses based on the various Search HTTP Post requests like autocomplete feature when the prefix of a category is given, search feature when the various brandName, categoryName is provided and also this feature supports the pagination feature where it provides the HTTP responses in the form of multiple pages, keywords count feature where the occurences of the keyword in product title is calculated to leverage the importance of each product.

Inorder to run the service clone the respository into the ```\desktop``` local system and then follow the command to cd to the Source code main folder. Make sure to clone the respository into the local system destop.

```
cd ProductsData-as-a-Service/productsRESTAPI/

```

As the Application is developed using the SpringBoot and Maven follow the command below to build the service and generate the jar file named ``` com.product-1.0-SNAPSHOT.jar ``` in ```ProductsData-as-a-Service/productsRESTAPI/target``` folder

```
mvn clean install

```

Inorder to run the Application in Various Platforms/architectures docker containers are used to deploy the application and run it in any platform and architecture. To run the application in docker first install the docker in your local system and start the docker after successfull installation.

So, once the docker is active and running, follow the command below to build the application using the docker and generate the docker image. Execute the below command by making sure the present directory/current directory in Terminal/Command line is ``` /ProductsData-as-a-Service/productsRESTAPI/ ``` .

```
docker build -f Dockerfile -t docker-spring-productsrestapi .

```

By Executing the above command docker image named ``` docker-spring-productsrestapi ``` is generated. One can see the various images in the docker by executing the command below in terminal/command prompt.

```
docker images

```

Once the docker image is generated then follow the command below to run the generated docker image in the docker container where the port 8080 in the docker container is exposed to port 8088 in the host operating system.

* Here as the input data is used in the JavaApplication then it is important to import the data file from the local to the docker container while running the docker image, so inorder to do that make sure to clone the repository to the desktop folder of local system and then follow the command below to simultaneously export the input data file into docker container and run the docker image.

```
docker run -p 8088:8080 -v ~/Desktop/ProductsData-as-a-Service/productsRESTAPI/data:/data docker-spring-productsrestapi

```

By executing the above command then the spring boot is exposed to the Port 8088 and once can send the various HTTP Post requests using the address ``` http://localhost:8088 ``` as the base host address inaddition to the custom HTTP Request.

So, this application service is designed to handle the various the HTTP POST Search requests, using any external HTTP Clients like PostMan, etc...Inorder to check the service I personally used the Postman Client.


# Example HTTP Requests to the Service.

### Endpoint #1 : POST Request.

* This endpoint provides an autocomplete feature where it provides the suggestions for the productName, category and brand for a given prefix.

```
http://localhost:8088/api/products/autocomplete

```

* Request Body in the form of JSON(application/json)

```
{ "type": "brand", "prefix": "Can" }

```

* Example Response of the above HTTP Post Request of 

```
[
    "Candyoo",
    "Canon",
    "Canless Air System",
    "Canonet",
    "Canopy",
    "Cangshan",
    "Canyon Dancer",
    "Cannon Safe",
    "CandyHome"
]

```

#### Design and Building the endpoint Explanation

This Endpoint is implemented by iterating over every product in the input products data and check to match the type and then the desired results are added into the hashset that matches the give input prefix.

#### Time and Space Complexity Analysis:

let us consider ```n``` as the length of input data or number of products in the input data.

#### Time Complexity for this endpoint is : O(n)
#### Space Compelexity for this endpoint is : O(n)  
Here Space Complexity is O(n) because the HashSet used for storing the output results is also considered as a space consumed during the calculation of Space Complexity.



### Endpoint #2 : POST Request.

* This endpoint provides an search feature where it provides the search results for the specified instructions in the conditions in the request body, also the pagination is implemented to provide the output result in the form of a pages. Also, when the multiple conditions are specified then the ouput body satifies all the conditions.


```
http://localhost:8088/api/products/search

```

* Request Body in the form of JSON(application/json)

```
{ "conditions": [
            { "type": "brandName", "values": ["Brother", "Canon"] },
            { "type": "categoryName", "values": ["Printers & Scanners"] }
      ],
      "pagination": { "from": 1, "size": 3 }
    }

```

* Example Response of the above HTTP Post Request of 

```
[
    {
        "productId": "B001UKQ6ES",
        "title": "Honeywell Genesis 11035808 22/4 Solid Unshielded Cable, Black [500']",
        "brandId": "12275",
        "brandName": "Honeywell",
        "categoryId": "200",
        "categoryName": "Printers & Scanners"
    },
    {
        "productId": "B00TON9YK6",
        "title": "HP LaserJet Color Pro M252dw",
        "brandId": "36335",
        "brandName": "HP",
        "categoryId": "200",
        "categoryName": "Printers & Scanners"
    },
    {
        "productId": "B011CZMEL4",
        "title": "Superink Remanufactured Ink Cartridge Set Compatible For HP 901XL & HP 901 CZ722FN CC654AN CC656AN (1 Black, 1 Tri-Color) Officejet 4500",
        "brandId": "11229",
        "brandName": "Greencycle",
        "categoryId": "200",
        "categoryName": "Printers & Scanners"
    }
]

```
#### Design and Building the endpoint Explanation

This Endpoint is implemented by iterating over every product in the input products data and iterating over each condition in the conditions of the request body and iterating over every value in each condition and check to match the condition, value in the condition and then the desired results are added into the LinkedHashSet that matches the condition and values in the condition.

#### Time and Space Complexity Analysis:

let us consider ```n``` as the length of input data or number of products in the input data, ```c``` is the number of conditions given in the request body and ```v``` is the number of values in each condition

#### Time Complexity for this endpoint is : O(n*c*v)
#### Space Compelexity for this endpoint is : O(n)  
Here Space Complexity is O(n) because the LinkedHashSet used for storing the output results is also considered as a space consumed during the calculation of Space Complexity.




### Endpoint #3 : POST Request.

* This endpoint provides an keywords and count of keywords feature in the productTitle category based on the input keywords in the request body.

```
http://localhost:8088/api/products/keywords

```

* Request Body in the form of JSON(application/json)

```
{ "keywords": ["toner", "ink" ] }

```

* Example Response of the above HTTP Post Request of 

```
{
    "keywordFrequencies": [
        {
            "keyword": "toner",
            "count": 11
        },
        {
            "keyword": "ink",
            "count": 1825
        }
    ]
}

```

#### Design and Building the endpoint Explanation

This Endpoint is implemented by iterating over every product in the input products data and keywords values given in the keywords row of the request body where the every keyword is iterated and check whether the product tile has the desired keyword or not. HashMap is used to count the occurences of each keyword in the product titles. The desired keywords and the frequency of the keywords is output as the Keywords class object form.


#### Time and Space Complexity Analysis:

let us consider ```n``` as the length of input data or number of products in the input data, ```m``` is the number of input keywords from the request body

#### Time Complexity for this endpoint is : O(n*m)
#### Space Compelexity for this endpoint is : O(m)  
Here Space Complexity is O(m) because the various intermediate results are stored in the ArrayList and HashMap where the maxnumber of keywords stored in each is m so the complexity is O(m)














