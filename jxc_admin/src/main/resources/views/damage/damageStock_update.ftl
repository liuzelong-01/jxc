<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">


<form class="layui-form" style="width:80%;" name="test" >
    <input name="goodsId" type="hidden" value="${(damageListGoods.goodsId)!}"/>
    <input name="damageListId" type="hidden" value="${damageList.id}">
    <fieldset class="layui-elem-field layui-field-title" >
        <legend style="font-size: 15px">报损信息</legend>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品名</label>
            <div class="layui-input-block">
                <input type="text" name="name" class="layui-input name"
                       readonly="readonly" id="name" value="${(damageListGoods.name)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                       readonly="readonly" name="code" id="code" value="${(damageListGoods.code)!}">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="model" id="model" value="${(damageListGoods.model)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">成本价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                      readonly="readonly" name="price" id="price"  value="${(damageListGoods.price)!}">
            </div>
        </div>
    </div>
    <br/>

    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">报损数量</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input inventoryQuantity"
                       readonly="readonly" name="num" id="num"  value="${damageListGoods.num}">
            </div>
        </div>
         <div class="layui-col-xs6">
             <label class="layui-form-label">报损总金额</label>
             <div class="layui-input-block">
                 <input type="text" class="layui-input purchasingPrice"
                       readonly="readonly" name="amountPayable" id="amountPayable"  value="${damageListGoods.total}" >
             </div>
         </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">报损号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       readonly="readonly"  name="customerReturnNumber" id="customerReturnNumber" value="${damageList.damageNumber}" >
            </div>
        </div>
        <div class="layui-col-xs6">
                <label class="layui-form-label">报损人</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input purchasingPrice"
                          readonly="readonly" name="userName" id="userName"  value="${(userName.username)!'lzl'}" >
                </div>
        </div>
    </div>
        </div>
    </fieldset>
    <br/>



    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateGoods">报损审批
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>


<script type="text/javascript" src="${ctx.contextPath}/js/damage/damageStock.update.js"></script>
</body>
</html>