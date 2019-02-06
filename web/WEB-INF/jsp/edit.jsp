<%@ page import="ru.webapp.model.*" %>
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
            <dt><h2>Имя:</h2></dt>
            <dd><input type="text" name="fullName" size="120" value="${resume.fullName}"></dd>
        </dl>
        <h2>Контакты:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(sectionType)}"/>
            <jsp:useBean id="section" type="ru.webapp.model.Section"/>
                <h2>${sectionType.title}</h2>
                <c:choose>
                    <c:when test="${sectionType.name() == 'PERSONAL' || sectionType.name() == 'OBJECTIVE'}">
                        <input type='text' name='${sectionType}' size='120' value='${section}'/>
                    </c:when>
                    <c:when test="${sectionType.name() == 'ACHIEVEMENT' || sectionType.name() == 'QUALIFICATIONS'}">
                        <br>​<textarea style="text-align:left; resize: none" id="txtArea" rows="23" cols="122"><%=(String.join("\n",((ListSection) section).getListSection()))%></textarea>
                    </c:when>
                    <c:when test="${sectionType.name() == 'EXPERIENCE' || sectionType.name() == 'EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                            <br>
                            <strong>Организация: </strong><input type='text' name='Организация' size='105' value='${org.homePage.name}'/>
                            <br>
                            <strong>URL Организации: </strong><input type='text' name='URL Организации' size='20' value='${org.homePage.url}'/>
                            <c:forEach var="position" items="${org.positions}">
                                <jsp:useBean id="position" type="ru.webapp.model.Organization.Position"/>
                                <strong>Позиция: </strong><input type='text' name='Позиция' size='64' value='${position.title}'/>
                                <br>
                                <strong>​Описание:</strong><br><textarea style="text-align:left; resize: none" id="txtArea" rows="10" cols="122"><%=position.getDescription()%></textarea>
                                <br>
                                <strong>Дата начала: </strong><input type='text' name='Дата начала' size='20' value='${position.startDate}'/>
                                <strong>Дата окончания: </strong><input type='text' name='Дата конца' size='20' value='${position.finishDate}'/>
                                <br>
                                <br>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>