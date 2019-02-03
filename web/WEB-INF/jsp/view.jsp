<%@ page import="ru.webapp.model.SimpleTextSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <h3>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.webapp.model.SectionType, ru.webapp.model.Section>"/>
            <c:set var="sectionType" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.webapp.model.Section"/>
            <c:choose>
                <c:when test="${sectionType == 'OBJECTIVE'}">
                    <%=((SimpleTextSection) section).getTextSection()%>
                </c:when>
                <c:when test="${sectionType == 'PERSONAL'}">
                    <%=((SimpleTextSection) section).getTextSection()%>
                </c:when>
                <c:when test="${sectionType == ''}">
            </c:choose>
        </c:forEach>
    </h3>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>