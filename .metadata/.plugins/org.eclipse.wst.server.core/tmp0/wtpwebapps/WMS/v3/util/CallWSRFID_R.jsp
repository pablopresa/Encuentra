<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dataTypes.DataIDDescripcion"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
${EAN}