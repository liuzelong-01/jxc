layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;



    /**
     * 行监听
     */
    table.on("tool(goods02)", function(obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openUpdateGoodsInfoDialog(obj.data.id);
        }
    })

    function  openUpdateGoodsInfoDialog(gid){
        var url  =  ctx+"/sale/toUpdateGoodsInfoPage?gid="+gid;
        layui.layer.open({
            title : "销售单",
            type : 2,
            area:["600px","400px"],
            maxmin:true,
            content : url
        });
    }


    //商品列表展示
    var  tableIns02 = table.render({
        elem: '#goodsList02',
        url : ctx+'/stock/listHasInventoryQuantity',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        //toolbar: "#toolbarDemo02",
        id : "goodsListTable02",
        cols : [[
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:50, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:50, align:'center'},
            {field: 'unitName', title: '单位', align:'center'},
            {field: 'inventoryQuantity', title: '库存', minWidth:50, align:'center'},
            {field: 'sellingPrice', title: '销售价', minWidth:50, align:'center'},
            {title: '操作', minWidth:180, templet:'#goodsListBar02',fixed:"right",align:"center"}
        ]]
    });



});
