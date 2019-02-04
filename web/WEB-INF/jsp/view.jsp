<%@ page import="ru.webapp.model.SimpleTextSection" %>
<%@ page import="ru.webapp.model.ListSection" %>
<%@ page import="ru.webapp.model.OrganizationSection" %>
<%@ page import="ru.webapp.util.HtmlUtil"%>
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
<section style="width: 900px; margin: auto">
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.webapp.model.SectionType, ru.webapp.model.Section>"/>
        <c:set var="sectionType" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section" type="ru.webapp.model.Section"/>
        <h2>${sectionType.title}</h2>

        <c:choose>
            <c:when test="${sectionType == 'OBJECTIVE' || sectionType == 'PERSONAL'}">
                <li><%=((SimpleTextSection) section).getTextSection()%></li>
            </c:when>
            <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">
                <c:forEach var="text" items="<%=((ListSection) section).getListSection()%>">
                    <li>${text}</li>
                </c:forEach>
            </c:when>
            <c:when test="${sectionType == 'EXPERIENCE' || sectionType == 'EDUCATION'}">
                <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>">
                    <c:choose>
                        <c:when test="${empty organization.homePage.url}">
                            <h3>${organization.homePage.name}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3><a href="${organization.homePage.url}">${organization.homePage.name}</a></h3>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="position" items="${organization.positions}">
                        <jsp:useBean id="position" type="ru.webapp.model.Organization.Position"/>
                        <%=HtmlUtil.formatDates(position)%>&nbsp;<strong>${position.title}</strong><br><br>${position.description}
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>