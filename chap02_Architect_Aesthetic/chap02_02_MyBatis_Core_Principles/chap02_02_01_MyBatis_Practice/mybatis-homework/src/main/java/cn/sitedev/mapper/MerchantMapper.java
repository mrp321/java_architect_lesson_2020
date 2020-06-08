package cn.sitedev.mapper;

import cn.sitedev.domain.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户Mapper
 */
public interface MerchantMapper {
    /**
     * 增
     *
     * @param merchantList 商户实体list
     * @return
     */
    int insert(List<Merchant> merchantList);

    /**
     * 删
     *
     * @param merchant 商户实体
     * @return
     */
    int delete(Merchant merchant);

    /**
     * 改
     *
     * @param id       商户id
     * @param merchant 商户实体
     * @return
     */
    int update(@Param("id") Integer id, @Param("merchant") Merchant merchant);

    /**
     * 查
     *
     * @param merchant 商户实体
     * @return
     */
    List<Merchant> query(Merchant merchant);

    /**
     * 根据商户id查询商户经营范围
     *
     * @param id 商户id
     * @return
     */
    List<Integer> getBizScopeListById(Integer id);
}
