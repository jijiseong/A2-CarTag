package autoever2.cartag.repository;

import autoever2.cartag.domain.car.DefaultOptionDto;
import autoever2.cartag.domain.suboption.SubOptionMappedDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OptionRepository {

    private final NamedParameterJdbcTemplate template;

    public OptionRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<SubOptionMappedDto> findAllSubOptionWithCategoryNameByCarId(int carId) {
        String sql = "select o.option_id, o.option_name, oc.option_category_name, o.option_image, ob.option_bought_count, o.option_used_count, ob.option_price " +
                "from suboptiondata ob " +
                "inner join option o " +
                "on o.option_id = ob.option_id " +
                "inner join optioncategory oc " +
                "on oc.option_category_id = o.option_category_id " +
                "where ob.car_id = :carId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("carId", carId);

        return template.query(sql, param, subOptionMapper());
    }

    private RowMapper<SubOptionMappedDto> subOptionMapper() {
        return BeanPropertyRowMapper.newInstance(SubOptionMappedDto.class);
    }

    public List<String> findAllHashtagNameBySubOptionId(int subOptionId) {
        String sql = "select h.hashtag_name " +
                "from optionhashtag oh, hashtag h " +
                "where oh.hashtag_id = h.hashtag_id and oh.option_id = :subOptionId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("subOptionId", subOptionId);

        return template.queryForList(sql, param, String.class);
    }

    public List<DefaultOptionDto> findDefaultOptionByCarId(int carId) {
        String sql = "select option_name, option_image, option_description, option_used_count " +
                "from DefaultOptionData as data " +
                "inner join Option as options on data.option_id = options.option_id " +
                "where data.car_id = :carId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("carId", carId);
        return template.query(sql, param, defaultOptionRowMapper());

    }

    private RowMapper<DefaultOptionDto> defaultOptionRowMapper() {
        return BeanPropertyRowMapper.newInstance(DefaultOptionDto.class);
    }
}
