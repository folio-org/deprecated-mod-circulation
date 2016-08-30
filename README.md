# circulation

Demo circulation module exposing some circulation apis and objects based on the raml-module-builder framework against a MongoDB

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

`mvn clean install`

Run:

`java -jar acquisitions-fat.jar -Dhttp.port=8084 embed_mongo=true`


Or via dockerfile

Note that the embedded mongo is started on a dynamic port chosen at embedded mongo start up - refer to the log ("created embedded mongo config on port 54851")


documentation of the APIs can be found at:
http://localhost:8084/apidocs/index.html?raml=raml/circulation/items.raml

Make sure to include appropriate headers as the runtime framework validates them

Authorization: aaaaa
Accept: application/json
Content-Type: application/json

Some Examples (ids should be replaced with actual ids from the DB):
```sh 
GET  http://localhost:8181/apis/patrons	get a list of patrons
GET http://localhost:8181/apis/patrons?offset=0&limit=10&order=desc	get patrons with filter
POST http://localhost:8181/apis/patrons	add a patron
GET  http://localhost:8181/apis/patrons/123456789   	get a specific patron (non existing id)
GET  http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba	get a specific patron ( existing id)
DELETE http://localhost:8181/apis/patrons/56e80c8d0503470ef88ad2f8	delete a patron
PUT http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba	update a patron
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines	get fines for a specific patron
POST http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines	add a fine for a specific patron
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines/56dc2c3ea129582aa44395b2	get a specific fine item for a specific patron
POST http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d?amount=50	pay 50 against a specific fine belonging to a specific patron (can include op=pay - but it is currently the default)
PUT http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d	update a fine for a specific patron
DELETE http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/fines/56dd73750503472f501dac4d	delete a fine for a specific patron
POST http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans	add a loan for a patron
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans	get all loans for a specific patron
POST http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a	renew a specific loan for a specific (optionly add param operation=renew)
DELETE http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a	delete a specific loan taken by a specific patron
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a	get a specific loan for a specific patron
PUT http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/loans/56eaafe24d700c493401298a	update specific loan info for specific patron
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/requests	get requests made by a specific patron
POST http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/requests	add a request for an item by a specific patron
PUT http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259	update request info
DELETE http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259	delete a request
GET http://localhost:8181/apis/patrons/56dbe25ea12958478cec42ba/requests/56eab85106495d0c48d2c259	get a specific request
```

Patron Object:
```sh
{
 "status": "ACTIVE",
 "patron_name": "Smith,John",
 "patron_barcode": "00007888",
 "patron_local_id": "abcdefd",
 "contact_info": {
  "patron_address_local": {
   "line1": "Main Street 1",
   "line2": "Nice building near the corner",
   "city": "London",
   "state_province": "",
   "postal_code": "",
   "address_note": "",
   "start_date": "2013-12-26Z"
  },
  "patron_address_home": {
   "line1": "Main Street 1",
   "line2": "Nice building near the corner",
   "city": "London",
   "state_province": "",
   "postal_code": "",
   "address_note": "",
   "start_date": "2013-12-26Z"
  },
  "patron_address_work": {
   "line1": "Main Street 1",
   "line2": "Nice building near the corner",
   "city": "London",
   "state_province": "",
   "postal_code": "",
   "address_note": "",
   "start_date": "2013-12-26Z"
  },
  "patron_email": "johns@mylib.org",
  "patron_email_alternative": "johns@mylib.org",
  "patron_phone_cell": "123456789",
  "patron_phone_home": "123456789",
  "patron_phone_work": "123456789",
  "patron_primary_contact_info": "patron_email"
 },
 "total_loans": 50,
 "total_fines": "100$",
 "total_fines_paid": "0$",
 "patron_code": {
  "value": "CH",
  "description": "Child"
 }
}

```
