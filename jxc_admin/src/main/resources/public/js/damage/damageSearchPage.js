layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //商品列表展示
    var  tableIns02 = table.render({
        elem: '#goodsList02',
        url : ctx+'/damage/damageAllList',
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
            {field: 'num', title: '报损数量', minWidth:50, align:'center'},
            {field: 'unit', title: '单位', align:'center'},
            {field: 'price', title: '成本价(￥)', minWidth:50, align:'center'},
            {field: 'total', title: '报损总金额(￥)', minWidth:50, align:'center'},
            {field: 'damageList', title: '报损时间', minWidth:100, align:'center', templet:'<div>{{d.damageList.damageDate}}</div>'},
            {field: 'damageList', title: '报损单号', minWidth:100, align:'center', templet:'<div>{{d.damageList.damageNumber}}</div>'},
            {field: 'userName', title: '报损人', minWidth:100, align:'center'},

        ]]
    });



});
