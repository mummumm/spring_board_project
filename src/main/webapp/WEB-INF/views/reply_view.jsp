<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
      <form action="reply" method="post">
      	<!-- 댓글에 필요한 값 불러오기 -->
         <input type="hidden" name="bid" value="${reply_view.bid}">
         <input type="hidden" name="bgroup" value="${reply_view.bgroup}">
         <input type="hidden" name="bstep" value="${reply_view.bstep}">
         <input type="hidden" name="bindent" value="${reply_view.bindent}">
         <tr>
            <td> 번호 </td>
            <td> ${reply_view.bid} </td>
         </tr>
         <tr>
            <td> 히트 </td>
            <td> ${reply_view.bhit} </td>
         </tr>
         <tr>
            <td> 이름 </td>
            <td> <input type="text" name="bname" value="${reply_view.bname}"></td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" name="btitle" value="${reply_view.btitle}"></td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> <textarea rows="10" name="bcontent" >${reply_view.bcontent}</textarea></td>
         </tr>
         <tr >
            <td colspan="2"> <input type="submit" value="답변"> &nbsp;&nbsp; <a href="list">목록</a></td>
         </tr>
      </form>
   </table>   
      

</body>
</html>