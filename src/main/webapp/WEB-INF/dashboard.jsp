<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>dashboard JSP</title>
    </head>
<body>
	<div style="display:flex;justify-content:space-evenly;">
		<a class="btn btn-primary" href="/song/new">Add new</a>
		<a class="btn btn-success" href="/topten">Top songs</a>
 		<form action="/search" method="post">
		        <input type="search" name="searchArtist"/>
		 		<input type="submit" class="btn btn-link" value="search"/>
		</form>
	</div>
	<table class="container table table-dark">
		<tbody>
		<c:forEach items="${songs}" var="song">
		<tr>
			<td>${song.title}</td>
			<td>${song.rating}</td>
			<td>
				<form action="/song/${song.id}" method="post">
				    <input type="hidden" name="_method" value="delete"/>
				    <input type="submit" class="btn btn-danger" value="Delete"/>
				</form>
			</td>
		</tr>		
		</c:forEach>

		</tbody>
	</table>
</body>
</html>
