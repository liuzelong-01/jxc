layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //商品列表展示
    var  tableIns02 = table.render({
        elem: '#goodsList02',
        url : ctx+'/sale/saleList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        //toolbar: "#toolbarDemo02",
        id : "goodsListTable02",
        cols : [[
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:50, align:'center'},
            {field: 'num', title: '销售数量', minWidth:50, align:'center'},
            {field: 'unit', title: '单位', align:'center'},
            {field: 'price', title: '销售价(￥)', minWidth:50, align:'center'},
            {field: 'total', title: '总金额(￥)', minWidth:50, align:'center'},
            {field: 'saleList', title: '销售时间', minWidth:100, align:'center', templet:'<div>{{d.saleList.saleDate}}</div>'},
            {field: 'saleList', title: '销售单号', minWidth:100, align:'center', templet:'<div>{{d.saleList.saleNumber}}</div>'},
            {field: 'customerName', title: '顾客', minWidth:100, align:'center'},
            {field: 'userName', title: '销售人', minWidth:100, align:'center'},
        ]]
    });



});
