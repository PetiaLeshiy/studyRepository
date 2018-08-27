package example.dao;

import example.mapper.VsemPoShapkeMapper;
import example.model.VsemPoShapkeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class VsemPoShapkeDAO  extends JdbcDaoSupport {

@Autowired
    public VsemPoShapkeDAO(DataSource dataSource) {
    this.setDataSource(getDataSource());}

public VsemPoShapkeModel findVsemPoShapkeId (Long id) {
    String sql = VsemPoShapkeMapper.sql + " WHERE maintable.id = ?";
    Object[] params = new Object[]{id};
    VsemPoShapkeMapper mapper = new VsemPoShapkeMapper();
    VsemPoShapkeModel model = this.getJdbcTemplate().queryForObject(sql,params, mapper);
    return model;

}

public List<VsemPoShapkeModel> getVsemPoShapkeList(){
    String sql = VsemPoShapkeMapper.sql;
    Object[] params = new Object[] {};
    VsemPoShapkeMapper mapper = new VsemPoShapkeMapper();
    List<VsemPoShapkeModel> list = this.getJdbcTemplate().query(sql, params, mapper);
    return list;
}

}
