<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">


<form class="layui-form" style="width:80%;" name="test" >
    <input name="goodsId" type="hidden" value="${(goods.id)!}"/>
    <input name="state" type="hidden" value="1"/>
    <input name="userId" type="hidden" value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.id)!'999'}"/>
    <input name="unit" type="hidden" value="${(goods.unit)!""}">
    <input name="typeId" type="hidden" value="${goods.typeId}">
    <fieldset class="layui-elem-field layui-field-title" >
        <legend style="font-size: 15px">商品信息</legend>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品名</label>
            <div class="layui-input-block">
                <input type="text" name="name" class="layui-input name"
                       readonly="readonly" id="name" value="${(goods.name)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                       readonly="readonly" name="code" id="code" value="${(goods.code)!}">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="model" id="model" value="${(goods.model)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">成本价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="price" id="price"  value="${(goods.purchasingPrice)!}">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
         <div class="layui-col-xs6">
             <label class="layui-form-label">当前库存</label>
             <div class="layui-input-block">
                 <input type="text" class="layui-input sellingPrice"
                        readonly="readonly" name="inventoryQuantity" id="inventoryQuantity" value="${(goods.inventoryQuantity)!}">
             </div>
         </div>
         <div class="layui-col-xs6">
              <label class="layui-form-label">最低库存</label>
              <div class="layui-input-block">
                  <input type="text" class="layui-input purchasingPrice"
                         name="minNum" id="minNum" readonly="readonly" value="${(goods.minNum)!}">
              </div>
         </div>
    </div>
    <br/>
    <div class="layui-row">
         <div class="layui-col-xs6">
              <label class="layui-form-label">应退款</label>
              <div class="layui-input-block">
                  <input type="text" class="layui-input sellingPrice"
                        readonly="readonly"  name="amountPaid" id="amountPaid" onblur="count()" >
              </div>
         </div>
         <div class="layui-col-xs6">
             <label class="layui-form-label">应收退款</label>
             <div class="layui-input-block">
                 <input type="text" class="layui-input purchasingPrice"
                        name="amountPayable" id="amountPayable"  onblur="count()" >
             </div>
         </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">供应商</label>
            <div class="layui-input-block">
                <select id="supplierId" name="supplierId" lay-verify="required"  class="select">
                    <option value="0" >请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
                <label class="layui-form-label">操作人</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input purchasingPrice"
                          readonly="readonly" name="userName" id="userName"  value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!'lzl'}" >
                </div>
        </div>
    </div>
        </div>
    </fieldset>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">退货数量</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input inventoryQuantity"
                       name="num" id="num"  lay-verify="required" placeholder="请输入退货数量"
                       value="${(goods.inventoryQuantity-goods.minNum)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">退货号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="returnNumber" id="returnNumber" value="" placeholder="TH201710270001">
            </div>
        </div>
    </div>

    <br/>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateGoods">退货出库
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript">
    function count(){
        var amount,price,money;
        amount=document.test.num.value;
        amount=parseFloat(amount);
        price=document.test.price.value;
        price=parseFloat(price);
        money=amount*price;
        document.test.amountPaid.value=money;
        document.test.amountPayable.value=money;
    }
</script>


<script type="text/javascript" src="${ctx.contextPath}/js/return/return.update.js"></script>
</body>
</html>