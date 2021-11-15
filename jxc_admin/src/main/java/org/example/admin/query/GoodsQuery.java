package org.example.admin.query;

import lombok.Data;

import java.util.List;

@Data
public class GoodsQuery extends BaseQuery {
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;
    private Integer type;

}
