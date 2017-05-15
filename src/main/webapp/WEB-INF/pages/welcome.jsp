<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.04.2017
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="error.jsp" %>--%>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        <a href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>

 <a href = '<%= request.getContextPath() %>/only_for_user'> only_for_user</a>
 <a href = '<%= request.getContextPath() %>/only_for_admin'> only_for_admin</a>


</body>
</html>
