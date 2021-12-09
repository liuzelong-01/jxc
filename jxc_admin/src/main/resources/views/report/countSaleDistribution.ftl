<!DOCTYPE html>
<html>
<head>
    <title>销售金额占比统计</title>
    <#include "../common.ftl">
    <script type="text/javascript" src="${ctx.contextPath}/js/echart/echarts.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
</head>
<body class="childrenBody">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <fieldset class="layui-elem-field layui-field-title" >
                <legend style="font-size: 15px">销售金额占比统计</legend>
            </fieldset>
            <div class="layui-card">

                <div class="layui-card-body">

                    <!-- EChart表-->
                    <div id="main" style="width: 1200px;height:600px;"></div>
                    <!--填充-->
                    <script type="text/javascript">
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('main'));
                        $.get('/report/getSaleEchartsData').done(function (data) {
                            myChart.setOption({
                                title: {
                                    text: ''
                                },
                                tooltip: {
                                    formatter:function(params)
                                    {

                                        return "销售金额占比"+'<br/>' +params.marker+ params.name + '  :  ' + params.value.toFixed(2) + "%";
                                    }
                                },
                                legend: {},
                                series: [
                                    {

                                        name: '销售金额',
                                        type: 'pie',
                                        radius: '50%',
                                        data: (function(){
                                            var rs = [];
                                            var sum=0;
                                            for (let j=0;j<data.YData.length;j++){
                                                sum=sum+data.YData[j];
                                            }
                                            for(let i=0;i<data.XData.length;i++){
                                                rs.push({
                                                    name :data.XData[i],
                                                    value:data.YData[i]/sum*100,
                                                });
                                            }
                                            return rs;
                                        })(),

                                        emphasis: {
                                            itemStyle: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        }
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