<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>	<spring:message code="form.name" /></title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

	<div id="global">
		<form:form commandName="product" action="product_save" method="post">
			<fieldset>
				<legend>
					<spring:message code="form.name" />
				</legend>
				<p class="errorLine">
					<form:errors path="name" cssClass="error" />
				</p>
				<p>
					<label for="name"><spring:message code="label.productName" />:</label>
					<form:input id="name" path="name" cssErrorClass="error" tabindex="1" />
				</p>
				<p>
					<label for="description"><spring:message
							code="label.description" />:</label>
					<form:input id="description" path="description" cssErrorClass="error" tabindex="2" />
				</p>
				<p class="errorLine">
					<form:errors path="price" cssClass="error" />
				</p>
				<p>
					<label for="price"><spring:message code="label.price" />:</label>
					<form:input id="price" path="price" cssErrorClass="error" tabindex="3" />
				</p>
				<p class="errorLine">
					<form:errors path="productionDate" cssClass="error" />
				</p>
				<p>
					<label for="productionDate"><spring:message
							code="label.productionDate" />:</label>
					<form:input id="productionDate" path="productionDate" cssErrorClass="error" tabindex="4" />
				</p>
				<p id="buttons">
					<input id="reset" type="reset" tabindex="5"
						value="<spring:message code="button.reset"/>"> <input
						id="submit" type="submit" tabindex="6"
						value="<spring:message code="button.submit"/>">
				</p>
			</fieldset>
		</form:form>
	</div>
</body>
</html>
