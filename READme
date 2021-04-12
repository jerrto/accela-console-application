Intructions to RUN project:
 Pre-requisites:
    Download sqlite-tools-win32-x86-3350400.zip from link https://www.sqlite.org/download.html
    Extract it into C:\sqlite and our database will be created in here.
    Application runs on port 8086 set in application.properties file.

 Information on Project:
 1. Project can be tested using console and/or POSTMAN.
     1.1: TESTING from console: Run the application and follow instructions.

     1.2: TESTING using POSTMAN: Below are the REST APIs.
         1. Add Person(POST) - localhost:8086/rest/person/add
            Sample request body: Multiple addresses allowed.
            {
                    "personfirstname": "ABC",
                    "personlastname": "XYZ",
                    "address":[{
                        "street": "My Home St",
                        "city": "homecityname",
                        "state": "homwstatename",
                        "zipcode": 12345
                    },
                    {
                        "street": "My Office St",
                        "city": "officecityname",
                        "state": "officestatename",
                        "zipcode": 67890
                    }]
            }

         2. Edit Person(PATCH) - localhost:8086/rest/person/update/{personid}
            Sample request body:
            {
                    "personfirstname": "newfirstname",
                    "personlastname": "newlastname"
            }

         3. Delete Person(DELETE) - localhost:8086/rest/person/delete/{personid}

         4. Add Address to person(POST) - localhost:8086/rest/person/{personid}/address/add
            Sample Request body:
            {
                "street": "My Office St",
                "city": "officecityname",
                "state": "officestatename",
                "zipcode": 67890
            }
         5. Edit Address(PATCH) - localhost:8086/rest/person/update/{personid}/address/{addressid}
            Sample Request body:
            {
                "street": "My Office St",
                "city": "officecityname",
                "state": "officestatename",
                "zipcode": 67890
            }
         6. Delete Address(DELETE) - localhost:8086/rest/person/update/{personid}/address/{addressid}/delete
         7. Count Number of Persons(GET) - localhost:8086/rest/person/count
         8. List Persons(GET) - localhost:8086/rest/person/listdetails


