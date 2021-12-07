<!DOCTYPE html>
<html>
<head>
    <title>销售</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <fieldset class="layui-elem-field layui-field-title" >
                <legend style="font-size: 15px">货物展示</legend>
            </fieldset>
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" >

                        <table id="goodsList02" class="layui-table"  lay-filter="goods02"></table>

                        <#--操作-->
                        <script id="goodsListBar02" type="text/html">
                            <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">销售</a>
                        </script>
                    </form>

                </div>
            </div>
        </div>

    </div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/sale/sale.js"></script>
</body>
</html>