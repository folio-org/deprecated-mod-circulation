# mod-circulation


Copyright (C) 2016 The Open Library Foundation

This software is distributed under the terms of the Apache License, Version 2.0. See the file ["LICENSE"](https://github.com/folio-org/mod-circulation/blob/master/LICENSE) for more information.


#### Demo circulation module exposing some circulation apis and objects based on the raml-module-builder framework against a MongoDB

This project is built using the raml-module-builder, using the MongoDB async client to implement some basic circulation APIs. The project also includes a small demo of the drools functionality. It is highly recommended to read the [raml-module-builder README](https://github.com/folio-org/raml-module-builder/blob/master/README.md)


APIs Implemented:

 - Bib CRUD
 - Item CRUD
 - Patron CRUD

Objects / Schemas:

 - Bibs
 - Items
 - Item request
 - Fines
 - Loans
 - Patrons

Can be run in both embedded mongodb mode or with a regular MongoDB server

instructions:

clone / download the raml-module-builder and `mvn clean install`

then do the same for the current project `mvn clean install`

Run:

`java -jar circulation-fat.jar -Dhttp.port=8084 embed_mongo=true`


Or via dockerfile

Note that the embedded mongo is started on a dynamic port chosen at embedded mongo start up - refer to the log ("created embedded mongo config on port 54851")


See https://github.com/folio-org/raml-module-builder#command-line-options for additional cmd line options.


documentation of the APIs can be found at:

http://localhost:8084/apidocs/index.html?raml=raml/circulation/items.raml

Make sure to include appropriate headers as the runtime framework validates them

Authorization: aaaaa

Accept: application/json

Content-Type: application/json


Some Examples (ids should be replaced with actual ids from the DB, Json object examples can be seen after the API list or in the json schema files in the /ramls dir):

### PATRON APIS

Method  | Description | Example URL
------------ | -------------  | -------------
 |  |
GET | get a list of patrons|  http://localhost:8181/patrons
GET | get patrons with filter| http://localhost:8181/patrons?offset=0&limit=10&order=desc
GET | get patrons with query| http://localhost:8081/patrons?query={"$and":[{"total_loans":{"$lt":21}},{"status":"ACTIVE"}]}
POST | add a patron | http://localhost:8181/patrons
GET | get a specific patron (non existing id) | http://localhost:8181/patrons/123456789
GET | get a specific patron ( existing id) | http://localhost:8181/patrons/56dbe25ea12958478cec42ba
DELETE | delete a patron | http://localhost:8181/patrons/56e80c8d0503470ef88ad2f8
PUT  | update a patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba
GET | get fines for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines
POST |add a fine for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines
GET |get a specific fine item for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines/56dc2c3ea129582aa44395b2
POST |pay 50 against a specific fine| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d?amount=50
PUT |update a fine for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d
DELETE|delete a fine for a specific patron | http://localhost:8181/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d
POST |add a loan for a patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans
GET |get all loans for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans
POST |renew a specific loan for a specific (optionly add param operation=renew)| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a
DELETE |delete a specific loan taken by a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a
GET |get a specific loan for a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a
PUT |update specific loan info for specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a
GET |get requests made by a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/requests
POST |add a request for an item by a specific patron| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/requests
PUT |update request info| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259
DELETE |delete a request| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259
GET |get a specific request| http://localhost:8181/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259



### ITEMS APIS

Method   | Description  | Example URL
------------ | -------------  | -------------
 |  |
POST|add an item (bib id is included in the object)|http://localhost:8181/items
GET|get items|http://localhost:8181/items
PUT|update an item| http://localhost:8181/items/56dbe160a129584dc8de7973
DELETE|delete an item| http://localhost:8181/items/56dbe25ea12958478cec42b9
GET|get a specific item| http://localhost:8181/items/56dbe160a129584dc8de7973
GET|get fines associated with this item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines
POST|add a fine to an  item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines
PUT|update a specific fine associated with this item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines/56eac6ea4d700c4ef475c2b1
GET|get a specific fine associated with this item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines/56eac6ea4d700c4ef475c2b1
DELETE|delete a fine associated with an item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines/56e9181a0503475f20830f90
POST|actions on a specific fine for a specific item | http://localhost:8181/items/56dbe160a129584dc8de7973/fines/56eac6ea4d700c4ef475c2b1
POST|pay 50 against the existing fine for this specific item| http://localhost:8181/items/56dbe160a129584dc8de7973/fines/56eac6ea4d700c4ef475c2b1?amount=50&op=pay
GET|get all requests for a specific item| http://localhost:8181/items/23344156380001021/requests
POST|put a request on this item| http://localhost:8181/items/23344156380001021/requests
PUT|update a specific request | http://localhost:8181/items/23344156380001021/requests/56eadfe806495d6260cc65d4
GET|get a specific request for a specific item| http://localhost:8181/items/23344156380001021/requests/56eadfe806495d6260cc65d4
DELETE|delete a specific request for a specific item| http://localhost:8181/items/23344156380001021/requests/56eadfe806495d6260cc65d4




### BIBS APIS

Method  | Description | Example URL
------------ | -------------  | -------------
 |  |
POST |create a bib record|http://localhost:8181/bibs
GET |get all bibs|http://localhost:8181/bibs
DELETE |delete a specific bib|http://localhost:8181/bibs/56dbe25ea12958478cec42b7
PUT |update a specific bib record|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f
GET |get a specific bib|http://localhost:8181/bibs/56eaf06d4d700c262c1c7e10
GET |get items based on this bib|http://localhost:8181/bibs/99100383909999/items
POST |create an item record based on bib|http://localhost:8181/bibs/99100383909999/items
GET |get a specific item|http://localhost:8181/bibs/99100383909999/items/56eaf6884d700c262c1c7e11
DELETE |delete a specific item|http://localhost:8181/bibs/99100383909999/items/56eabff606495d5a98a2bbaf
PUT |update a specific item|http://localhost:8181/bibs/99100383909999/items/56eaf6884d700c262c1c7e11
POST |create a bib level request|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f/requests
GET |retrieve requests for a bib|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f/requests
GET |retrieve a specific request for a bib|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f/requests/56eb13ac06495d0ebc3adfab
PUT |update a specific request for a bib|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f/requests/56eb13ac06495d0ebc3adfab
DELETE |delete a specific request for a bib|http://localhost:8181/bibs/56dbe7b1a129584bc475e87f/requests/56eb13ac06495d0ebc3adfab




Patron Object:
```sh
{
 "status": "ACTIVE",
 "user_id": "1234567",
 "patron_barcode": "00007888",
 "patron_local_id": "abcdefd",
 "total_loans": 50,
 "total_fines": "100$",
 "total_fines_paid": "0$",
 "patron_code": {
  "value": "CH",
  "description": "Child"
 }
}

```

Fine Object:

```sh

{
  "fine_amount": 10,
  "fine_outstanding": 0,
  "fine_date": 1413879432,
  "fine_pay_in_full": true,
  "fine_pay_in_partial": false,
  "fine_note": "aaaaaa",
  "item_id": "56dbe160a129584dc8de7973",
  "fine_forgiven": {
 "user": "the cool librarian",
 "amount": "none"
  },
  "patron_id": "56dbe25ea12958478cec42ba"
}

```

Loan Object:

```sh

{
  "circ_desk": {
    "value": "MAIN_CIRC",
    "desc": "Joe's Circulation Desk"
  },
  "library": {
    "value": "ULAW",
    "desc": "Main Library"
  },
  "patron_id": "56dbe25ea12958478cec42ba",
  "item_barcode": "000000055510000222",
  "item_id": "23344156380001021",
  "due_date": 1413813699999,
  "loan_status": "ACTIVE",
  "loan_date": 1403434999999,
  "loan_fine": 0,
  "title": "History today",
  "author": "me",
  "description": "",
  "publication_year": 2000,
  "call_number": "1234567890",
  "renewable": false,
  "location_code": {
    "value": "1234567890",
    "name": "1234567890"
  },
  "item_policy": {
    "value": 2,
    "description": "2 Week Loan"
  }
}

```

Item Object:


```sh

{
  "barcode": "39031031697261",
  "location": {
    "value": "STACK",
    "desc": "Stacks (STACK)"
  },
  "status": {
    "value": "01",
    "desc": "ITEM_STATUS_MISSING"
  },
  "material_type": {
    "value": "BOOK",
    "desc": "Book",
    "icon": "book.png"
  },
  "shelf_location": {
    "classification_number": "1234567890",
    "shelf_listing_number": "12345678"
  },
  "copy_id": "1",
  "item_link": "/bibs/99100383909999/item/1234567890009999",
  "bib_id": "99100383909999"
}

```


Request Item Object:

```sh

{
  "request_status": "NOT_STARTED",
  "place_in_queue": 11,
  "request_date": "2015-12-12",
  "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
  "pickup_location": "MATHLIB",
  "item_id": "23344156380001021",
  "bib_id": "13344156380001021",
  "patron_id": "Me123"
}

```


