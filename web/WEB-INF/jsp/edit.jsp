<%@ page import="ru.webapp.model.ContactType" %>
<%@ page import="ru.webapp.model.ListSection" %>
<%@ page import="ru.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(sectionType)}"/>
            <jsp:useBean id="section" type="ru.webapp.model.Section"/>
            <dl>
                <dt>${sectionType.title}</dt>
                <c:choose>
                    <c:when test="${sectionType.name() == 'PERSONAL'}">
                        <input type='text' name='${sectionType}' size='100' value='${section}'/>
                    </c:when>
                    <c:when test="${sectionType.name() == 'OBJECTIVE'}">
                        <input type='text' name='${sectionType}' size='100' value='${section}'/>
                    </c:when>
                    <c:when test="${sectionType.name() == 'ACHIEVEMENT'}">
                        <c:forEach var="item" items="<%=((ListSection) section).getListSection()%>">
                            <c:set var="text" value="${text}${empty text ? '' : '\n'}${item}"/>
                        </c:forEach>
                        ​<textarea id="txtArea" rows="10" cols="70">${text}</textarea>
                    </c:when>
                </c:choose>


            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>