# Getting Started

### Pre-requisits
* Any type of REST client, For example: [Postman](https://www.postman.com/downloads/) <br />
* Maven 3.6.0 (version with which software was built)
### Posting
By default we can post new comments (and register new user) by executing following POST request<br />
http://<span></span>localhost:8080/post-new-comment?login={login}<br />
with body of request containing simple comment message, we should use JSON(application/json) found under option raw<br /><br />
For example:<br />
![Screenshot](assets/postCommentPostmanExample.png)
<br /><br />
### Wall<br />
By default we can post view our comments by executing following GET request<br />
http://<span></span>localhost:8080/get-own-comments?login={login}<br /><br />
For example:<br />
http://<span></span>localhost:8080/get-own-comments?login=pawelmielen<br /><br />
### Following<br />
By default we can start following other users by executing following POST request<br />
http://<span></span>localhost:8080/follow/{follower}/{toFollow}<br /><br />
For example:<br />
http://<span></span>localhost:8080/follow?followingLogin=pawelmielen&followedLogin=jankowalski<br /><br />
### Timeline<br />
By default we can view posts of users we follow by executing following GET request<br />
http://<span></span>localhost:8080/get-followed-comments?login={login}<br /><br />
For example:<br />
http://<span></span>localhost:8080/get-followed-comments?login=pawelmielen<br /><br />
### Building<br />
Since this application is created with help of Maven build command is<br />
<b>mvn clean install</b><br /><br />
### Running<br />
To run our application we need to execute following Maven command<br />
<b>mvn spring-boot:run</b><br /><br />

