<%-- 
    Document   : blogRightSider
    Created on : Feb 22, 2022, 10:48:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <div class="col-lg-4 col-md-4 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="card border-0 sidebar sticky-bar rounded shadow">
                            <div class="card-body">
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Tìm Kiếm</h5>
                                    <div id="search2" class="widget-search mt-4 mb-0">
                                        <form action="blogs?action=search" method="POST" id="searchform" class="searchform">
                                            <div>
                                                <input value="${requestScope.content}" type="text" class="border rounded" name="content" id="s" placeholder="Nhập từ khóa">
                                                <input type="submit" id="searchsubmit" value="Search">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Bài Đăng Nổi Bật</h5>
                                    <div class="mt-4">
                                        <c:forEach items="${featured_blogs}" var="fb">
                                            <div class="clearfix post-recent">
                                                <div class="post-recent-thumb float-start"> <a href="jvascript:void(0)"> <img alt="img" src="data:image/png;base64,${fb.img}" class="img-fluid rounded"></a></div>
                                                <div class="post-recent-content float-start"><a href="blogs?action=detail&blog_id=${fb.blog_id}">${fb.title}</a></div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Danh Mục</h5>
                                    <div class="tagcloud mt-4">
                                        <c:forEach items="${categories}" var='c'>
                                            <a href="blogs?action=category&id=${c.id}" class="rounded">${c.name}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
</html>
