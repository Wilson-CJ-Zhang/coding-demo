# Chengjie's Demo for coding test

### Summary
This is a standard Restful API integrated with SpringBoot

The API '/api/v1/bill' consume the shopping list in List<String> format, and return shopping result including details and total cost in Bill format

Currently products and discounts data are hard code. I create interfaces for loading products for other datasource(database, other api, etc). I also create interfaces to manage different types of discounts.

### Further developing proposal
To provide more flexible and expandable architecture, I propose to separate the products and discounts logic into two microservices and setup a distributed systems

### How to start the demo
1) Run [DemoApplication.java](src%2Fmain%2Fjava%2Fpers%2Fwilson%2Fdemo%2FDemoApplication.java)
2) Visit below url in your browser http://localhost:8085/swagger-ui/index.html

### Swagger example
#### 1) call API
![example-call-bill-api.png](docs%2Freadme%2Fimages%2Fexample-call-bill-api.png)

#### 2) response
![example-response-bill-api.png](docs%2Freadme%2Fimages%2Fexample-response-bill-api.png)