<%@ page import="ru.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" ; charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullname}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.webapp.model.ContactType, java.lang.String>"/>
                <%contactEntry.getKey().toHtml(contactEntry.getValue());%><br/>
        </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>