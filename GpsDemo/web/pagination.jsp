<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style type="text/css">
    .paging_full_numbers { height: 22px; line-height: 22px; }
    .paging_full_numbers .next:active, .paging_full_numbers .first:active, .paging_full_numbers .previous:active, .paging_full_numbers .last:active { font-weight: normal!important; }
    .paging_full_numbers a{-webkit-user-select:none;-moz-user-select:none;-o-user-select:none;user-select:none;}
    .paging_full_numbers a:active { outline: none }
    .paging_full_numbers a:hover { text-decoration: none; }
    .paging_full_numbers a.paginate_button , .paging_full_numbers a.paginate_active { border: 1px solid #d3d3d3; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; padding: 4px 7px; margin: 0 3px; cursor: pointer; *cursor: hand; color: #919191; font-size: 11px; box-shadow: 0 1px 0 #fff inset, 0 1px 0px #dfdfdf; -webkit-box-shadow: 0 1px 0 #fff inset, 0 1px 0px #dfdfdf; -moz-box-shadow: 0 1px 0 #fff inset, 0 1px 0px #dfdfdf;  }
    .paging_full_numbers a.paginate_button { background: #f9f9f9; background: -moz-linear-gradient(top,  #f9f9f9 0%, #eeeeee 100%); background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f9f9f9), color-stop(100%,#eeeeee)); background: -webkit-linear-gradient(top,  #f9f9f9 0%,#eeeeee 100%); background: -o-linear-gradient(top,  #f9f9f9 0%,#eeeeee 100%); background: -ms-linear-gradient(top,  #f9f9f9 0%,#eeeeee 100%); background: linear-gradient(top,  #f9f9f9 0%,#eeeeee 100%); filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f9f9f9', endColorstr='#eeeeee',GradientType=0 ); }
    .paging_full_numbers a.paginate_button:hover { background: #f6f6f6; background: -moz-linear-gradient(top,  #f6f6f6 0%, #e8e8e8 100%); background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#f6f6f6), color-stop(100%,#e8e8e8)); background: -webkit-linear-gradient(top,  #f6f6f6 0%,#e8e8e8 100%); background: -o-linear-gradient(top,  #f6f6f6 0%,#e8e8e8 100%); background: -ms-linear-gradient(top,  #f6f6f6 0%,#e8e8e8 100%); background: linear-gradient(top,  #f6f6f6 0%,#e8e8e8 100%); filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f6f6f6', endColorstr='#e8e8e8',GradientType=0 ); }
    .paging_full_numbers a.paginate_active, .paging_full_numbers a.paginate_button:active { background: #6f6f6f; color: #fff; border-color: #646464; box-shadow: 0 1px 2px #545454 inset, 0 1px 0 #fff; -webkit-box-shadow: 0 1px 2px #545454 inset, 0 1px 0 #fff; -moz-box-shadow: 0 1px 2px #545454 inset, 0 1px 0 #fff; font-weight: bold; }
    .paginate_button_disabled, .paginate_button_disabled:active  { background: #eaeaea!important; color: #c5c5c5!important; box-shadow: none!important; font-weight: normal!important; }
    .paginate_button_disabled:active { border-color: #d3d3d3!important; }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $(".pagination a").click(function (){
            var page = $(this).attr("page");
            if (!page || page == '') {
                return;
            }
            $("form[action]").find("input[name=page]").val(page);
            $("form[action]").submit();
        });
    });
</script>

<!--分页 -->
<div class="pagination fg-toolbar tableFooter">
    <div class="dataTables_info">共${param.totalElements}条 </div>
    <div class="dataTables_paginate paging_full_numbers">
        <%
            int length = 6;
            int ignoreLength = 1;
            String getLen = request.getParameter("btnNum");//基础信息,显示的按钮个数，条件允许额外加上第一页和最后一页
            String ignore = request.getParameter("ignoreLength");//省略号所含按钮最小值

            if (ignore != null && !"".equals(ignore)) {
                ignoreLength = Integer.valueOf(ignore);
            }
            if (getLen != null && !"".equals(getLen)) {
                length = Integer.valueOf(getLen);
            }
            int pageNo = Integer.valueOf(request.getParameter("pageNo"));
            int totalPage = Integer.valueOf(request.getParameter("totalPage"));

            int half = length / 2;
            int begin = pageNo - half;
            int end = pageNo + half;
            pageNo = pageNo < 1 ? 1 : pageNo;
            if (begin < 1) {
                begin = 1;
                end = begin + length;
            }
            if (end > totalPage) {
                end = totalPage;
                begin = totalPage - length;
            }
            end = totalPage == 0 ? 1:end;
            begin = begin < 1 ? 1 : begin;
            if (pageNo > 1) {
                out.print("<a page='1' class='first paginate_button'>首页</a>");
                out.print("<a page="+(pageNo - 1)+" class='previous paginate_button'>上一页</a>");
            }else{
                out.print("<a page='' class='first paginate_button paginate_button_disabled'>首页</a>");
                out.print("<a page='' class='previous paginate_button paginate_button_disabled'>上一页</a>");
            }

            if (begin - 1 > ignoreLength) {
                out.print("<a page='1' class='paginate_button'>1</a>");
                out.print("<a page='' style='padding: 4px 4px;cursor:default;'>  ...  </a>");
            }

            for (int index = begin; index <= end; index++) {
                if (index == pageNo) {
                    out.print("<a page=" + index + " class='paginate_active'>" + index + "</a>");
                } else {
                    out.print("<a page=" + index + " class='paginate_button'>" + index + "</a>");
                }
            }
            if (totalPage - end > ignoreLength) {
                out.print("<a page='' style='padding:4px 4px;cursor:default;'>  ...  </a>");
            }

            if (end < totalPage) {
                out.print("<a page=" + totalPage + " class='paginate_button'>" + totalPage + "</a>");
            }

            if (pageNo < end) {
                out.print("<a page="+(pageNo + 1)+" class='next paginate_button'>下一页</a>");
                out.print("<a page="+totalPage+" class='last paginate_button'>末页</a>");
            }else{
                out.print("<a page='' class='next paginate_button paginate_button_disabled'>下一页</a>");
                out.print("<a page='' class='last paginate_button paginate_button_disabled'>末页</a>");
            }
        %>
    </div>
</div>

