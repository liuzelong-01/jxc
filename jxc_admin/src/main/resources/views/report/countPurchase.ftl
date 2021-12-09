<!DOCTYPE html>
<html>
<head>
    <title>采购统计</title>
    <#include "../common.ftl">
    <script type="text/javascript" src="${ctx.contextPath}/js/echart/echarts.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
</head>
<body class="childrenBody">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <fieldset class="layui-elem-field layui-field-title" >
                <legend style="font-size: 15px">采购数量统计</legend>
            </fieldset>
            <div class="layui-card">

                <div class="layui-card-body">
                    <!-- EChart表-->
                    <div id="main" style="width: 1200px;height:300px;"></div>
                    <!--填充-->
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('main'));
                        $.get('/report/getPurchaseNumEchartsData').done(function (data) {
                            myChart.setOption({
                                title: {
                                    text: '采购数量'
                                },
                                tooltip: {},
                                legend: {},
                                xAxis: {
                                    data: data.XData,
                                    axisLabel: {
                                        interval: 0,
                                        rotate: 40,
                                    },
                                },
                                yAxis: {},
                                series: [
                                    {
                                        name: '采购数量',
                                        type: 'bar',
                                        data: data.YData
                                    }
                                ]
                            });
                        });


                    </script>
                </div>
            </div>
        </div>

        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <fieldset class="layui-elem-field layui-field-title" >
                <legend style="font-size: 15px">采购金额统计</legend>
            </fieldset>
            <div class="layui-card">

                <div class="layui-card-body">
                    <!-- EChart表-->
                    <div id="main-x" style="width: 1200px;height:300px;"></div>
                    <!--填充-->
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart1 = echarts.init(document.getElementById('main-x'));
                        $.get('/report/getPurchaseMoneyEchartsData').done(function (data) {
                            myChart1.setOption({
                                title: {
                                    text: '采购金额'
                                },
                                tooltip: {},
                                legend: {},
                                xAxis: {
                                    data: data.XData,
                                    axisLabel: {
                                        interval: 0,
                                        rotate: 40,
                                    },
                                },
                                yAxis: {},
                                series: [
                                    {
                                        name: '采购金额',
                                        type: 'bar',
                                        data: data.YData
                                    }
                                ]
                            });
                        });


                    </script>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>