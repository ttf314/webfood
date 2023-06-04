<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>管理员后台</title>
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/question/findBySql2" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/question/add">添加用户</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入用户名称" name="name" class="input" value="${obj.id}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>

    <%--<table class="table table-hover text-center">
        <tr>
            <th>编号</th>
            <th>回答内容</th>
            <th>回答时间</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.id}</td>
                <td>
                    <c:set var="answers" value="${fn:split(data.answerContent, '#')}" />
                    <c:forEach items="${answers}" var="answer">
                        <p>${answer}</p>
                    </c:forEach>
                </td>
                <td>
                    <c:set var="times" value="${fn:split(data.answerTime, '#')}" />
                    <c:forEach items="${times}" var="time">
                        <p>${time}</p>
                    </c:forEach>
                </td>
                <td>
                    <a class="button border-red" href="${ctx}/question/up?id=${data.id}">
                        <span class="icon-trash-o">删除</span>
                    </a>
                </td>
            </tr>
        </c:forEach>--%>
   <%-- <table class="table table-hover text-center">
        <tr>
            <th>编号</th>
            <th>回答内容</th>
            <th>回答时间</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <c:set var="answerContent" value="${data.answerContent}"/>
            <c:set var="answerTime" value="${data.answerTime}"/>
            <c:set var="answers" value="${fn:split(answerContent, '#')}"/>
            <c:set var="times" value="${fn:split(answerTime, '#')}"/>
            <c:set var="answerCount" value="${fn:length(answers)}"/>

            <tr>
                <td>${data.id}</td>
                <td>
                    <c:forEach var="answer" items="${answers}" varStatus="i">
                        <c:out value="${answer}"/>
                        <c:if test="${i.index lt answerCount-1}">
                            <br/>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="time" items="${times}" varStatus="i">
                        <c:out value="${time}"/>
                        <c:if test="${i.index lt answerCount-1}">
                            <br/>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <a class="button border-red" href="${ctx}/question/up?id=${data.id}">
                        <span class="icon-trash-o">删除</span>
                    </a>
                </td>
            </tr>
        </c:forEach>--%>
    <table class="table table-hover text-center">
        <thead>
        <tr>
            <th>编号</th>
            <th>回答内容</th>
            <th>回答时间</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <c:set var="answerContent" value="${data.answerContent}"/>
            <c:set var="answerTime" value="${data.answerTime}"/>
            <c:set var="answers" value="${fn:split(answerContent, '#')}"/>
            <c:set var="times" value="${fn:split(answerTime, '#')}"/>
            <c:set var="answerCount" value="${fn:length(answers)}"/>
            <c:forEach var="answer" items="${answers}" varStatus="i">
                <tr>
                    <td><c:out value="${data.id}"/></td>
                    <td><c:out value="${answer}"/></td>
                    <td><c:out value="${times[i.index]}"/></td>
                    <td>
                        <a class="button border-red" href="${ctx}/question/delet?answerContent=${answer}&answerTime=${times[i.index]}&id=${data.id}">
                            <span class="icon-trash-o">删除</span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
<%--
    <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/question/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
                        <pg:last>
                            共${pagers.total}记录，共${pageNumber}页，
                        </pg:last>
                        当前第${curPage}页
                        <pg:first>
                            <a href="${pageUrl}">首页</a>
                        </pg:first>
                        <pg:prev>
                            <a href="${pageUrl}">上一页</a>
                        </pg:prev>
                        <pg:pages>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">[${pageNumber}]</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">${pageNumber}</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:pages>
                        <pg:next>
                            <a href="${pageUrl}">下一页</a>
                        </pg:next>
                        <pg:last>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">尾页</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">尾页</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:last>
                    </pg:pager>
                </div>
            </td>
        </tr>
    </table>--%>
</div>
<script>
    function changeSearch(){
        $("#listform").submit();
    }
</script>
</body>

</html>