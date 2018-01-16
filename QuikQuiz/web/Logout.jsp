<%-- Document: Logout || Date : Jan 11, 2018, 11:43:19 PM || @author : SKR --%>

<%
   session.invalidate();
   response.sendRedirect("Login.jsp");
%>