This is a mini messaging service (inspired by Twitter) 

It provices a RESTful API where all endpoints require HTTP Basic authentication and generate output in JSON format. It has the the following functionality:

An endpoint to read the message list for the current user (as identified by their HTTP Basic authentication credentials). Include messages they have sent and messages sent by users they follow. Support a “search=” parameter that can be used to further filter messages based on keyword.  
Endpoints to get the list of people the user is following as well as the followers of the user.  
An endpoint to start following another user.  
An endpoint to unfollow another user.


The project provides an H2 in-memory SQL database as a storage backend.   You can use http://localhost:8080/h2-console to interact with it.   
 