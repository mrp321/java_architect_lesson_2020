package cn.sitedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    /**
     * 商户id
     */
    private Integer id;

    /**
     * 商户名称
     */
    private String name;
    /**
     * 经营范围
     */
    private List<Integer> bizScopeList;
}
