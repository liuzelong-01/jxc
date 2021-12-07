layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    form.on("submit(updateGoods)", function (data) {
        index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        var url=ctx + "/purchase/updateStock";
        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                        res.message, {
                            icon: 5
                        }
                    );
            }
        });
        return false;
    });

    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })

    /**
     * 供应商下拉框展示
     */
    $.ajax({
        type:"post",
        url:ctx+"/supplier/allSuppliers",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    //alert($("input[name='goodsUnit']").val()==item.id));
                    if($("input[name='supplier']").val()==item.id){
                        $("#supplierId").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
                    }else{
                        $("#supplierId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                    }

                });
            }
            //重新渲染
            form.render("select")
        }
    })



});