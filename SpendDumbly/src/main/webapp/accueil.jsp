<%@ page import="java.util.ArrayList" %>
<%@ page import="wallet.Info" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./stylecss.css">
</head>
<body>
	<nav>
		<div class="logo"></div>
		<div class="action">
			<div class="content">
				<div class="user"></div>
				<div class="name">${user}</div>
			</div>
			<a href="http://localhost:8080/SpenDumbly/add.jsp?user=${user}&pass=${pass}"><div class="append"></div></a>
			<a href="#"><div class="logout"></div></a>
		</div>
		
	</nav>
	<div>
		<table>
			<tr id="grant">
				<th>Date</th>
				<th>Somme</th>
				<th>Type</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		
			<% 
				
				String[][] info = (String[][]) request.getAttribute("info");
            	if (info != null) {
            	for (int i = 0; i < info.length; i++){
            %>		
           			<tr>
           	<%		String idina = "";
               		for (int j = 0; j < info[i].length; j++) {
               			if(j==0){
               				idina = info[i][0];
               			}
               			else	out.println("<td>"+ info[i][j] +"</td>");
            		}
           	%>
           		<td id="action">
           			<a href="http://localhost:8080/SpendDumbly/modify.jsp?user=${user}&id=<%=idina%>&pass=${pass}"><div class="modify"></div></a>
           			<a href="http://localhost:8080/SpendDumbly/Supprimer?user=${user}&id=<%=idina%>&pass=${pass}"><div class="supprimer"></div></a>
           		</td>
           	</tr>
           	<%
           		}
            	}
            %>
		
		</table>
		</div>
</body>
</html>