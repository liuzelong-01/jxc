<!DOCTYPE html>
<html>
<head>
    <title>顾客退货单据查询</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <fieldset class="layui-elem-field layui-field-title" >
                <legend style="font-size: 15px">顾客退货单</legend>
            </fieldset>
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" >

                        <table id="goodsList02" class="layui-table"  lay-filter="goods02"></table>

                    </form>

                </div>
            </div>
        </div>

    </div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/customerReturn/searchPage.js"></script>
</body>
</html>