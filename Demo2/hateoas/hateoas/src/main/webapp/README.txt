*** HATEOAS ***

An example implementation of HATEOAS
This will use XML (not JSON)


*** Testing PersonResource (HATEOAS)  ***

To check use a browser with URI below (will format)

# GET (Note: No previous link here)
http://localhost:8080/hateoas/webresources/persons?start=0&count=2

# GET (Note: Previous and next links here)
http://localhost:8080/hateoas/webresources/persons?start=1&count=1


# To use cURL
curl -v http://localhost:8080/hateoas/webresources/persons?start=0&count=2