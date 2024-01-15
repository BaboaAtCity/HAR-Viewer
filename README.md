## HAR File Viewer In Java
This simple Java project serves as a tool for viewing the contents of HAR (HTTP Archive) files, which are commonly used in web development and automation. HAR files capture precise information about a web page's interactions, including requests, responses, and timing metrics. 

___

### Usage
To use this HAR file viewer, follow these steps:

  1. Clone the repository or download the source code.
  1. Specify the path to your HAR file (as of right now it only takes one at a time) in the filePath variable within the Main class.
  1. Compile and run the Main class.

___

### Output
The output of this tool is broken down below:
- HTTP Method: Displays whether the request was GET, POST ETC
- Status Code: Shows the response status code of the request
- URL: Shows the URL associated with the request
- Timestamp: Displays the timestamp of when the request was initiated
- Entry Number: The index of the entry with in the entry array

```
GET 200
https://example.com/resource1
2023-12-29T14:35:41.093Z
Entry number: 1

POST 404
https://example.com/api/endpoint
2023-12-29T14:35:41.098Z
Entry number: 2
```
